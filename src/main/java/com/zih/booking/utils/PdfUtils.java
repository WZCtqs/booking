package com.zih.booking.utils;


import java.io.*;

import java.util.Map;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;

/**
 * Created by wangpeng on 2018/02/01.
 */
@Slf4j
public class PdfUtils {
    // 利用模板生成pdf
    public static void pdfout(ResourceLoader resourceLoader, Map<String, Object> datemap, Map<String, byte[]> imgmap, String templatePath,int page) {
        // 生成的新文件路径
        String newPDFPath = "C:/pdf/testout1.pdf";
        File f = new File("C:/pdf/");
        if (!f.exists()) {
            f.mkdirs();
        }
        org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:" + templatePath);
        InputStream inputStream = null;
        Document doc = new Document();
        PdfReader reader;
        FileOutputStream out = null;
        ByteArrayOutputStream bos = null;
        PdfStamper stamper;
        try {
            inputStream = resource.getInputStream();
            BaseFont bf = BaseFont.createFont("c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font FontChinese = new Font(bf, 5, Font.NORMAL);
            out = new FileOutputStream(newPDFPath);// 输出流
            reader = new PdfReader(inputStream);// 读取pdf模板
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();
            //文字类的内容处理
            //   Map<String, String> datemap = (Map<String, String>) o.get("datemap");
            form.addSubstitutionFont(bf);
            for (String key : datemap.keySet()) {

                if (null != datemap.get(key)) {
                    String value = datemap.get(key).toString();
                    form.setField(key, value);
                }
            }
            //图片类的内容处理
            // Map<String, byte[]> imgmap = (Map<String, byte[]>) o.get("imgmap");
            for (String key : imgmap.keySet()) {
                byte[] value = imgmap.get(key);
                byte[] imgpath = value;
                int pageNo = form.getFieldPositions(key).get(0).page;
                Rectangle signRect = form.getFieldPositions(key).get(0).position;
                float x = signRect.getLeft();
                float y = signRect.getBottom();
                //根据路径读取图片
                Image image = Image.getInstance(imgpath);
                //获取图片页面
                PdfContentByte under = stamper.getOverContent(pageNo);
                //图片大小自适应
                image.scaleToFit(signRect.getWidth(), signRect.getHeight());
                //添加图片
                image.setAbsolutePosition(x, y);
                under.addImage(image);
            }
            stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
            stamper.close();

            //  Font font = new Font(bf, 32);
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);
            copy.addPage(importPage);
            if(page==3){
            PdfImportedPage importPage2 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 2);
            PdfImportedPage importPage3 = copy.getImportedPage(new PdfReader(bos.toByteArray()), 3);
            copy.addPage(importPage2);
            copy.addPage(importPage3);}
        } catch (IOException e) {
            log.info(e.getMessage(),e);
        } catch (DocumentException e) {
            log.info(e.getMessage(),e);
        } finally {
            try {
                doc.close();
                if (out != null) {
                    out.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                System.gc();
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public static void pdfout2(ResourceLoader resourceLoader, Map<String, Object> datemap,   String templatePath) {
            // 生成的新文件路径
            String newPDFPath = "C:/pdf/testout1.pdf";
            File f = new File("C:/pdf/");
            if (!f.exists()) {
                f.mkdirs();
            }
            org.springframework.core.io.Resource resource = resourceLoader.getResource("classpath:" + templatePath);
            InputStream inputStream = null;
            Document doc = new Document();
            PdfReader reader;
            FileOutputStream out = null;
            ByteArrayOutputStream bos = null;
            PdfStamper stamper;
            try {
                inputStream = resource.getInputStream();
                BaseFont bf = BaseFont.createFont("c://windows//fonts//simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
                Font FontChinese = new Font(bf, 5, Font.NORMAL);
                out = new FileOutputStream(newPDFPath);// 输出流
                reader = new PdfReader(inputStream);// 读取pdf模板
                bos = new ByteArrayOutputStream();
                stamper = new PdfStamper(reader, bos);
                AcroFields form = stamper.getAcroFields();
                //文字类的内容处理
                //   Map<String, String> datemap = (Map<String, String>) o.get("datemap");
                form.addSubstitutionFont(bf);
                for (String key : datemap.keySet()) {

                    if (null != datemap.get(key)) {
                        String value = datemap.get(key).toString();
                        form.setField(key, value);
                    }
                }

                stamper.setFormFlattening(true);// 如果为false，生成的PDF文件可以编辑，如果为true，生成的PDF文件不可以编辑
                stamper.close();

                //  Font font = new Font(bf, 32);
                PdfCopy copy = new PdfCopy(doc, out);
                doc.open();
                PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), 1);

                copy.addPage(importPage);


            } catch (IOException e) {
                log.info(e.getMessage(),e);
            } catch (DocumentException e) {
                log.info(e.getMessage(),e);
            } finally {
                try {
                    doc.close();
                    if (out != null) {
                        out.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    System.gc();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }

        }
//    public static void main(String[] args) {
//        Map<String,String> map = new HashMap();
//        map.put("text","sdfffffffffff");
//        map.put("text1","晴朗");
//        map.put("text2",new Date().toString());
//
//        Map<String,String> map2 = new HashMap();
//        map2.put("img","C:\\Users\\wsl96\\Desktop\\【1.10】LOL最新换肤助手\\使用图解.png");
//        Map<String,Object> o=new HashMap();
//        o.put("datemap",map);
//        o.put("imgmap",map2);
//       pdfout(o);
//    }
}
