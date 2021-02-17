package com.zih.booking.utils;

import com.alibaba.fastjson.JSONObject;
import com.zih.booking.request.SettlementRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.*;
import org.apache.http.protocol.HttpContext;
import org.springframework.http.ResponseEntity;


import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

@Slf4j
public class HttpClientUtil {
    private static Logger logger = LogManager.getLogger(HttpClientUtil.class);

    /**
     * 获取带ssl的httpclient对象
     * 忽略服务器证书，采用信任机制
     * @return
     */
    public static CloseableHttpClient getSSLHttpClient() throws Exception {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager x509TrustManager = new X509TrustManager() {

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }
        };
        // 初始化SSL上下文
        sslContext.init(null, new TrustManager[] { x509TrustManager }, null);
        // SSL套接字连接工厂,NoopHostnameVerifier为信任所有服务器
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
        // 注册http套接字工厂和https套接字工厂
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslsf).build();

        // 连接池管理器
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(registry);
        connManager.setMaxTotal(1000);// 连接池最大连接数
        connManager.setDefaultMaxPerRoute(20);// 每个路由最大连接数

        // 配置超时回调机制
        HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                if (executionCount >= 3) {// 如果已经重试了3次，就放弃
                    return false;
                }
                if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                    return true;
                }
                if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                    return false;
                }
                if (exception instanceof InterruptedIOException) {// 超时
                    return true;
                }
                if (exception instanceof UnknownHostException) {// 目标服务器不可达
                    return false;
                }
                if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
                    return false;
                }
                if (exception instanceof SSLException) {// ssl握手异常
                    return false;
                }
                HttpClientContext clientContext = HttpClientContext.adapt(context);
                HttpRequest request = clientContext.getRequest();
                // 如果请求是幂等的，就再次尝试
                if (!(request instanceof HttpEntityEnclosingRequest)) {
                    return true;
                }
                return false;
            }
        };

        // 配置请求参数
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(4000)
                .setConnectTimeout(2000)
                .setSocketTimeout(5000)
                .build();

        // 构建https客户端
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connManager)
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler(retryHandler)
                .build();

        return httpClient;
    }

    /**
     * HTTP GET请求
     * @param baseUrl 请求基本路径，即不带参数的路径
     * @param paramMap 参数集合
     */
    public static JSONObject getRequest(String baseUrl, Map<String, Object> paramMap, String cookieStr) throws Exception {
        //创建httpclient实例，用于发送请求
//        CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpClient client = null;
        try {
            client = HttpClientUtil.getSSLHttpClient();
        }catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        HttpGet httpGet = new HttpGet(baseUrl);
        //设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();
        httpGet.setConfig(requestConfig);
        try {
            //封装参数列表
            if(paramMap!=null && paramMap.size()>0) {
                URIBuilder builder = new URIBuilder().setPath(baseUrl);
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                NameValuePair nameValuePair = null;
                Iterator ite = paramMap.entrySet().iterator();
                while (ite.hasNext()) {
                    Map.Entry<String,String> entry = (Map.Entry<String,String>)ite.next();
                    nameValuePair = new BasicNameValuePair(entry.getKey(),entry.getValue() instanceof String ? entry.getValue() : String.valueOf(entry.getValue()));
                    paramList.add(nameValuePair);
                }
                //追加参数
                builder.addParameters(paramList);
                //设置GET请求URL
                httpGet.setURI(builder.build());
            }
            //设置header
            httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");

            if(StringUtils.isNotEmpty(cookieStr)) {
                httpGet.setHeader("Cookie", cookieStr);
            }
            //执行请求返回结果集
            CloseableHttpResponse response = client.execute(httpGet);
            // 服务器返回码
            int status_code = response.getStatusLine().getStatusCode();
            logger.info("==========================调用状态码：{}==========================",status_code);
            // 服务器返回内容
            String respStr = null;
            HttpEntity responseEntity = response.getEntity();
            if(responseEntity != null) {
                respStr = EntityUtils.toString(responseEntity, "UTF-8");
            }
             logger.debug("==========================结果集：{}==========================",respStr);
            EntityUtils.consume(responseEntity);
            JSONObject obj = JSONObject.parseObject(respStr);
            return obj;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }



    /**
     * HTTP POST请求
     * @param baseUrl 请求基本路径，即不带参数的路径
     * @param paramMap 参数集合
     */
    public static JSONObject postRequest(String baseUrl,Map<String, Object> paramMap,String cookieStr) {
        //创建httpclient实例，用于发送请求
        // CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpClient client = null;
        try {
            client = HttpClientUtil.getSSLHttpClient();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        //创建post请求
        HttpPost httpPost = new HttpPost(baseUrl);
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(4000).setConnectionRequestTimeout(2000).setSocketTimeout(5000).build();
        httpPost.setConfig(requestConfig);
        try {
            //封装参数列表
            if(paramMap!=null && paramMap.size()>0) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                NameValuePair nameValuePair = null;
                Iterator ite = paramMap.entrySet().iterator();
                while (ite.hasNext()) {
                    Map.Entry<String,String> entry = (Map.Entry<String,String>)ite.next();
                    nameValuePair = new BasicNameValuePair(entry.getKey(),entry.getValue() instanceof String ? entry.getValue() : String.valueOf(entry.getValue()));
                    paramList.add(nameValuePair);
                }
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList, "UTF-8");
                httpPost.setEntity(entity);
            }
            //设置header
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");


            if(StringUtils.isNotEmpty(cookieStr)) {
                httpPost.setHeader("Cookie", cookieStr);
            }
            //执行请求返回结果集
            CloseableHttpResponse response = client.execute(httpPost);
            // 服务器返回码
            int status_code = response.getStatusLine().getStatusCode();
            logger.info("==========================调用状态码：{}==========================",status_code);
            //服务器返回内容
            String respStr = null;
            HttpEntity responseEntity = response.getEntity();
            if(responseEntity != null) {
                respStr = EntityUtils.toString(responseEntity, "UTF-8");
            }
            logger.debug("==========================结果集：{}==========================",respStr);
            // 释放资源
            EntityUtils.consume(responseEntity);
            JSONObject obj = JSONObject.parseObject(respStr);
            return obj;
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }




    /**
     * HTTP POST并写参数请求
     * @param baseUrl 请求基本路径，即不带参数的路径
     * @param paramMap 参数集合
     */
    public static JSONObject postJsonRequest(String baseUrl,Map<String, Object> paramMap,String jsonParamStr,String cookieStr) {
        //创建httpclient实例，用于发送请求
        // CloseableHttpClient client = HttpClients.createDefault();
        CloseableHttpClient client = null;
        try {
            client = HttpClientUtil.getSSLHttpClient();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        //创建post请求
        HttpPost httpPost = new HttpPost(baseUrl);
        //设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(3000).setConnectionRequestTimeout(1000).setSocketTimeout(5000).build();
        httpPost.setConfig(requestConfig);
        try {
            //封装参数列表
            if(paramMap!=null && paramMap.size()>0) {
                Iterator ite = paramMap.entrySet().iterator();
                while (ite.hasNext()) {
                    Map.Entry<String,String> entry = (Map.Entry<String,String>)ite.next();
                    httpPost.addHeader(entry.getKey(),entry.getValue() instanceof String ? entry.getValue() : String.valueOf(entry.getValue()));
                }
            }
            StringEntity entity = null;
            if (StringUtils.isNotEmpty(jsonParamStr)) {
                entity = new StringEntity(jsonParamStr, Consts.UTF_8);
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            httpPost.setEntity(entity);
            //设置header
            httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36");
            if(StringUtils.isNotEmpty(cookieStr)) {
                httpPost.setHeader("Cookie", cookieStr);
            }
            //执行请求返回结果集
            CloseableHttpResponse response = client.execute(httpPost);
            // 服务器返回码
            int status_code = response.getStatusLine().getStatusCode();
            logger.info("==========================调用状态码：{}==========================",status_code);
            // 服务器返回内容
            String respStr = null;
            HttpEntity responseEntity = response.getEntity();
            if(responseEntity != null) {
                respStr = EntityUtils.toString(responseEntity, "UTF-8");
            }
            logger.debug("=============结果集：{}=============",respStr);
            // 释放资源
            EntityUtils.consume(responseEntity);
            JSONObject obj = JSONObject.parseObject(respStr);
            return obj;
        } catch(Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 传送json类型的post请求
     * @param url
     * @param json
     * @return String
     */
    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            log.error("不带参数的post请求失败：{}",e.toString(),e.getStackTrace());
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error("不带参数的post请求--response.close()失败：{}",e.toString(),e.getStackTrace());
            }
        }
        return resultString;
    }


    public static void main(String[] args) {

//        JSONObject jsonObject = HttpClientUtil.postRequest("http://www.zih718.com/Ajax/CostList.ashx",new HashMap<>(),null);
//        log.info(jsonObject.toJSONString());
        String result = HttpClientUtil.doPostJson("http://www.zih718.com/Ajax/CostList.ashx","{}");
        System.out.println(result);
    }


}
