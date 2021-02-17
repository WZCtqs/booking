package com.zih.booking.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ResourceLoader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;



import javax.servlet.ServletOutputStream;


@Slf4j
public class ExcelUtil {

    //doc docx 下载
    public static void downloadThymeleaf(ResourceLoader resourceLoader, String filename, String path, HttpServletRequest request, HttpServletResponse response){
        InputStream inputStream = null;
        ServletOutputStream servletOutputStream = null;
        try {
          org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:"+path);
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            response.setContentType("application/msword");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode(filename, StandardCharsets.UTF_8.toString());
            String fileTyle=path.substring(path.lastIndexOf("."),path.length());
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("content-disposition", "attachment;filename=" + new String(encodeName.getBytes(), "ISO8859-1") + fileTyle );
            } else {
                response.setHeader("content-disposition", "attachment;filename=" + encodeName + fileTyle);
            }

            inputStream = resource.getInputStream();
            servletOutputStream = response.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
            response.flushBuffer();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                    servletOutputStream = null;
                }
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
                // 召唤jvm的垃圾回收器
                System.gc();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

    }

}



