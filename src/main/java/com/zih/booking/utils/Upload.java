package com.zih.booking.utils;

import com.zih.booking.vo.ProblemFileVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
public class Upload {

    public static final Logger log = LoggerFactory.getLogger(Upload.class);
    public static String server_ip;

    @Value("${file.server_ip}")
    public void setSystemUrl(String systemUrl) {
        server_ip = systemUrl;
    }

    public static String uploadFile(HttpServletRequest request, MultipartFile file, String dirName)
            throws IllegalStateException, IOException {
        String pathUrl = "E:/booking/" + dirName + "/";
        File f = new File(pathUrl);
        if (!f.exists()) {
            f.mkdirs();
        }
        // 存放的路径
        log.info(pathUrl);
        String url = "";
        try {
            String fileName = file.getOriginalFilename();//文件原名称包括后缀名
            String UUID = UUIDUtils.getStringRandom(11);
            fileName = UUID + file.getOriginalFilename().replace("#","");
            url = server_ip + "booking/" + dirName + "/" + fileName;
            file.transferTo(new File(pathUrl + fileName));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return url;
    }

    public static boolean deleteFile(String url) {
        try {
            String realPath = url.replace(server_ip, "E:/");
            File f = new File(realPath);
            if (f.exists()) {
                return f.delete();
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static ProblemFileVo[] filesUpload(MultipartFile[] files, String path, HttpServletRequest request) {
        String UUID = UUIDUtils.getStringRandom(11) + getRandom();
        String realPath = "E:/booking/" + path + "/" + UUID + "/";
        File f = new File(realPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        log.info(String.valueOf(files.length));
        //判断file数组不能为空并且长度大于0
        if (files != null && files.length > 0) {
            try {
                ProblemFileVo[] urls = new ProblemFileVo[files.length];
                //循环获取file数组中得文件
                for (int i = 0; i < files.length; i++) {
                    MultipartFile file = files[i];
                    //保存文件
                    String fileName = file.getOriginalFilename()
                            .replace("#","_")
                            .replace("[","")
                            .replace("]","")
                            .replace("(","")
                            .replace(")","")
                            .replace("/","")
                            .replace("&","")
                            .replace("%","")
                            .replace(";","")
                            .replace("{","")
                            .replace("}","")
                            .replace("`","_")
                            .trim()
                            .replace(" ","");//文件原名称包括后缀名
                    String fileName2 = UUID + "/" + URLEncoder.encode(fileName, "UTF-8");
                    ProblemFileVo problemFileVo = new ProblemFileVo();
                    //InetAddress addr = InetAddress.getLocalHost();//"http://" +addr.getHostAddress()
                    String url = server_ip + "booking/" + path + "/" + fileName2;
                    //保存文件
//                    String diskFileName = java.util.UUID.randomUUID().toString().replace("-","")
//                            + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//                    String url = server_ip + "booking/" + path + "/" + UUID + "/" + diskFileName;
                    problemFileVo.setUrl(url);
                    problemFileVo.setFileName(fileName);
                    problemFileVo.setFileName(file.getOriginalFilename());
                    urls[i] = problemFileVo;
                    file.transferTo(new File(realPath + fileName));
//                    file.transferTo(new File(realPath + diskFileName));
                }
                return urls;
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return null;
            }catch (ArithmeticException e){
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getRandom() {
        String times= String.valueOf(System.currentTimeMillis());
        return times.substring(times.length()-5);
    }

    public static void main(String[] args) {
        String str = "1R~R@KN6{S_`X9}H5@FK}8X";
        String regEx="[a-zA-Z0-9\\u4e00-\\u9fa5_~@]";
        Pattern p   =   Pattern.compile(regEx);
        Matcher m   =   p.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(m.find()){
            sb.append(m.group());
        }
        System.out.println(sb);
    }
}
