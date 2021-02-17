package com.zih.booking.utils;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.Image;
import com.lowagie.text.rtf.RtfWriter2;
import com.zih.booking.model.LadingBill;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description :
 * @Author : wangzhichao
 * @Date: 2020-11-18 15:52
 */
public class WordExportUtil {

    public static String TidanDraftWordExport(LadingBill ladingBill) {
        Document document = new Document(PageSize.A4);
        try {
            // 创建文件夹
            String path = "C:/booking/tidanDraft";
            File paths = new File(path);
            if (!paths.exists()) {
                paths.mkdirs();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HHmmss");
            String fileName = format.format(new Date()) + "tidandraft.doc";
            path = path + "/" + fileName;
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            // 写入文件信息
            RtfWriter2.getInstance(document, new FileOutputStream(path));
            document.open();
            /*页眉处理--开始*/
            Table headerTable = getHeaderTable(5);
            Cell h1 = new Cell();
            Image image = Image.getInstance("C:\\Users\\HP\\Pictures\\图片1.png");
            image.scalePercent(35);
            h1.add(image);
            h1.setBorder(0);
            headerTable.addCell(h1);
            Cell h2 = new Cell();
            h2.setColspan(4);
            h2.setBorder(0);
            Paragraph headerText1 = new Paragraph("\n 郑州国际陆港开发建设有限公司", getPageHeaderFont1());
            h2.add(headerText1);
            Paragraph headerText2 = new Paragraph(
                    " Zhengzhou International Hub Development and Construction Co., Ltd.", getPageHeaderFont2());
            h2.add(headerText2);
            headerTable.addCell(h2);
            Paragraph pageHeader = new Paragraph();
            pageHeader.add(headerTable);
            HeaderFooter headers = new HeaderFooter(pageHeader, false);
            headers.setBottom(0);
            document.setHeader(headers);
            /*页眉处理--结束*/
            /*第一段表格*/
            Table table = getTable(4);
            table.setTop(0);
            /*lb1*/
            Cell lb1 = new Cell();
            lb1.setColspan(2);
            lb1.setRowspan(2);
            lb1.setBorder(1);
            lb1.setBorderWidthLeft(0);
            lb1.setBorderWidthRight(1);
            Paragraph bl1Title = new Paragraph("1 SHIPPER/EXPORTER", getTitleFont());
            lb1.add(bl1Title);
            Paragraph bl1V = new Paragraph(ladingBill.getLb1(), getContentFont());
            lb1.add(bl1V);
            table.addCell(lb1);
            /*lbno*/
            Cell lbno = new Cell();
            lbno.setColspan(2);
            lbno.setBorder(1);
            lbno.setBorderWidthLeft(1);
            lbno.setBorderWidthRight(0);
            Paragraph lbnoTitle = new Paragraph("B/L No.    " + ladingBill.getLbNumber(), getContentFont());
            lbno.add(lbnoTitle);
            table.addCell(lbno);
            /*lbtext*/
            Cell lbtext = new Cell();
            lbtext.setColspan(2);
            lbtext.setRowspan(3);
            lbtext.setBorder(1);
            lbtext.setBorderWidthRight(0);
            lbtext.setBorderWidthLeft(1);
            Paragraph lbtextP1 = new Paragraph("BILL OF LADING", new Font(Font.BOLD, (float) 14, Font.NORMAL, new Color(0, 0, 0)));
            lbtextP1.setAlignment(1);
            lbtext.add(lbtextP1);
            Paragraph lbtextP2 = new Paragraph("For multimodal transport", new Font(Font.BOLD, (float) 12, Font.NORMAL, new Color(0, 0, 0)));
            lbtextP2.setAlignment(1);
            lbtextP2.setSpacingAfter(2);
            lbtext.add(lbtextP2);
            Paragraph lbtextP = new Paragraph(TEXT, getContentFont3());
            lbtext.add(lbtextP);
            table.addCell(lbtext);
            /*lb2*/
            Cell lb2 = new Cell();
            lb2.setColspan(2);
            lb2.setBorder(1);
            lb2.setBorderWidthLeft(0);
            lb2.setBorderWidthRight(1);
            Paragraph lb2Title = new Paragraph("2 CONSIGNEE", getTitleFont());
            lb2.add(lb2Title);
            Paragraph lb2V = new Paragraph(ladingBill.getLb2(), getContentFont());
            lb2.add(lb2V);
            table.addCell(lb2);
            /*lb3*/
            Cell lb3 = new Cell();
            lb3.setColspan(2);
            lb3.setBorder(1);
            lb3.setBorderWidthLeft(0);
            lb3.setBorderWidthRight(1);
            Paragraph lb3Title = new Paragraph("3 NOTIFY PARTY", getTitleFont());
            lb3.add(lb3Title);
            Paragraph bl3V = new Paragraph(ladingBill.getLb3(), getContentFont());
            lb3.add(bl3V);
            table.addCell(lb3);
            /*lb4*/
            Cell lb4 = new Cell();
            lb4.setBorder(1);
            lb4.setBorderWidthLeft(0);
            lb4.setBorderWidthRight(1);
            Paragraph lb4Title = new Paragraph("4 TRAIN NO./SHIPPING TYPE ", getTitleFont());
            lb4.add(lb4Title);
            Paragraph bl4V = new Paragraph(ladingBill.getLb4(), getContentFont());
            lb4.add(bl4V);
            table.addCell(lb4);
            /*lb5*/
            Cell lb5 = new Cell();
            lb5.setBorder(1);
            lb5.setBorderWidthRight(1);
            Paragraph lb5Title = new Paragraph("5 PORT OF LOADING", getTitleFont());
            lb5.add(lb5Title);
            Paragraph bl5V = new Paragraph(ladingBill.getLb5(), getContentFont());
            lb5.add(bl5V);
            table.addCell(lb5);
            /*lb8*/
            Cell lb8 = new Cell();
            lb8.setBorder(1);
            lb8.setBorderWidthLeft(1);
            Paragraph lb8Title = new Paragraph("8 PRE-CARRIAGE BY ", getTitleFont2());
            lb8.add(lb8Title);
            Paragraph bl8V = new Paragraph(ladingBill.getLb8(), getContentFont());
            lb8.add(bl8V);
            table.addCell(lb8);
            /*lb8*/
            Cell lb9 = new Cell();
            lb9.setBorder(1);
            lb9.setBorderWidthRight(0);
            lb9.setBorderWidthLeft(1);
            Paragraph lb9Title = new Paragraph("9 PLACE OF RECEIPT ", getTitleFont2());
            lb9.add(lb9Title);
            Paragraph bl9V = new Paragraph(ladingBill.getLb9(), getContentFont());
            lb9.add(bl9V);
            table.addCell(lb9);
            /*lb6*/
            Cell lb6 = new Cell();
            lb6.setBorder(1);
            lb6.setBorderWidthLeft(0);
            lb6.setBorderWidthRight(1);
            lb6.setBorderWidthBottom(1);
            Paragraph lb6Title = new Paragraph("6 PORT OF DISCHARGE ", getTitleFont());
            lb6.add(lb6Title);
            Paragraph bl6V = new Paragraph(ladingBill.getLb6(), getContentFont());
            lb6.add(bl6V);
            table.addCell(lb6);
            /*lb7*/
            Cell lb7 = new Cell();
            lb7.setBorder(1);
            lb7.setBorderWidthBottom(1);
            Paragraph lb7Title = new Paragraph("7 PLACE OF DELIVERY ", getTitleFont());
            lb7.add(lb7Title);
            Paragraph bl7V = new Paragraph(ladingBill.getLb7(), getContentFont());
            lb7.add(bl7V);
            table.addCell(lb7);
            /*lb10*/
            Cell lb10 = new Cell();
            lb10.setBorder(1);
            lb10.setBorderWidthRight(0);
            lb10.setBorderWidthLeft(1);
            lb10.setBorderWidthBottom(1);
            lb10.setColspan(2);
            Paragraph lb10Title = new Paragraph("10 FINAL DESTINATION (FOR THE MERCHANT’S REFERENCE ONLY)\n ", getTitleFont2());
            lb10.add(lb10Title);
            Paragraph bl10V = new Paragraph(ladingBill.getLb10(), getContentFont());
            lb10.add(bl10V);
            table.addCell(lb10);

            document.add(table);
            Paragraph paragraph1 = new Paragraph("PARICULARS FURNISHED BY SHIPPER", getTitleFont2());
            paragraph1.setAlignment(1);
            document.add(paragraph1);

            Table table3 = getTable(6);
            Cell lb11 = new Cell();
            lb11.setBorder(1);
            lb11.setBorderWidthLeft(0);
            lb11.setBorderWidthRight(1);
            Paragraph lb11Title = new Paragraph("11 MARKS & NOS.", getTitleFont());
            lb11.add(lb11Title);
            table3.addCell(lb11);

            Cell lb12 = new Cell();
            lb12.setBorder(1);
            Paragraph lb12Title = new Paragraph("12 NO.OF PACKAGES\n OR CONTAINERS", getTitleFont());
            lb12.add(lb12Title);
            table3.addCell(lb12);

            Cell lb13 = new Cell();
            lb13.setBorder(1);
            lb13.setBorderWidthRight(1);
            lb13.setBorderWidthLeft(1);
            lb13.setColspan(2);
            Paragraph lb13Title = new Paragraph("13 DESCRIPTION OF PACKAGES AND GOODS", getTitleFont());
            lb13.add(lb13Title);
            table3.addCell(lb13);

            Cell lb14 = new Cell();
            lb14.setBorder(1);
            Paragraph lb14Title = new Paragraph("14 GROSS WEIGHT\n(KGS)", getTitleFont());
            lb14.add(lb14Title);
            table3.addCell(lb14);

            Cell lb15 = new Cell();
            lb15.setBorder(1);
            lb15.setBorderWidthRight(0);
            lb15.setBorderWidthLeft(1);
            Paragraph lb15Title = new Paragraph("15 MEASURED\n VOLUME(CBM)", getTitleFont());
            lb15.add(lb15Title);
            table3.addCell(lb15);

            Cell lb11t = new Cell();
            lb11t.setRowspan(2);
            lb11t.setBorder(1);
            lb11t.setBorderWidthLeft(0);
            lb11t.setBorderWidthRight(1);
            lb11t.setBorderWidthBottom(1);
            Paragraph lb11tTitle = new Paragraph(ladingBill.getLb11(), getContentFont());
            lb11t.add(lb11tTitle);
            table3.addCell(lb11t);

            Cell lb12t = new Cell();
            lb12t.setRowspan(2);
            lb12t.setBorder(1);
            lb12t.setBorderWidthBottom(1);
            Paragraph lb12tTitle = new Paragraph(ladingBill.getLb12(), getContentFont());
            lb12t.add(lb12tTitle);
            table3.addCell(lb12t);

            Cell lb13t = new Cell();
            lb13t.setRowspan(2);
            lb13t.setColspan(2);
            lb13t.setBorder(1);
            lb13t.setBorderWidthLeft(1);
            lb13t.setBorderWidthRight(1);
            lb13t.setBorderWidthBottom(1);
            Paragraph lb13tTitle = new Paragraph(ladingBill.getLb131() + "\n\n" + ladingBill.getLb132(), getContentFont());
            lb13t.add(lb13tTitle);
            table3.addCell(lb13t);

            Cell lb14t = new Cell();
            lb14t.setRowspan(2);
            lb14t.setBorder(1);
            lb14t.setBorderWidthBottom(1);
            Paragraph lb14ttTitle = new Paragraph(ladingBill.getLb14(), getContentFont());
            lb14t.add(lb14ttTitle);
            table3.addCell(lb14t);

            Cell lb151t = new Cell();
            lb151t.setBorder(1);
            lb151t.setBorderWidthRight(0);
            lb151t.setBorderWidthLeft(1);
            lb151t.setBorderWidthBottom(1);
            Paragraph lb151ttTitle = new Paragraph(ladingBill.getLb151(), getContentFont());
            lb151t.add(lb151ttTitle);
            table3.addCell(lb151t);

            Cell lb152t = new Cell();
            lb152t.setBorder(1);
            lb152t.setBorderWidthRight(0);
            lb152t.setBorderWidthLeft(1);
            lb152t.setBorderWidthBottom(1);
            Paragraph lb152ttTitle = new Paragraph("CHARGING\nVOLUME(CBM)", getTitleFont());
            lb152t.add(lb152ttTitle);

            Paragraph lb152tValue = new Paragraph(ladingBill.getLb152(), getContentFont());
            lb152t.add(lb152tValue);

            Paragraph lb153ttTitle = new Paragraph("ON BOARD DATE", getTitleFont());
            lb152t.add(lb153ttTitle);

            Paragraph lb153tValue = new Paragraph(ladingBill.getLb153(), getContentFont());
            lb152t.add(lb153tValue);
            table3.addCell(lb152t);

            document.add(table3);

            Paragraph paragraph2 = new Paragraph("ABOVE PARTICULARS DECLARED BY SHIPPER.CARRIER NOT RESPONSIBLE.", getTitleFont2());
            paragraph2.setAlignment(1);
            document.add(paragraph2);

            Table table4 = getTable(2);
            Cell lb16 = new Cell();
            lb16.setBorder(1);
            lb16.setBorderWidthLeft(0);
            lb16.setBorderWidthRight(1);
            Paragraph lb16Title = new Paragraph("16 CHARGE COLLECT ( AS PER AGREEMENT WITH CONSIGNER )", getTitleFont());
            lb16.add(lb16Title);
            Paragraph lb16V = new Paragraph(ladingBill.getLb16(), getContentFont2());
            lb16.add(lb16V);
            table4.addCell(lb16);

            Cell lb20 = new Cell();
            lb20.setRowspan(3);
            lb20.setBorder(1);
            lb20.setBorderWidthRight(0);
            lb20.setBorderWidthLeft(1);
            Paragraph lb20Title = new Paragraph("20 FOR DELIVERY OF GOODS PLEASE APPLY TO:", getTitleFont2());
            lb20.add(lb20Title);
            Paragraph lb20V = new Paragraph(ladingBill.getLb20(), getTitleFont2());
            lb20.add(lb20V);
            table4.addCell(lb20);

            Cell lb17 = new Cell();
            lb17.setBorder(1);
            lb17.setBorderWidthLeft(0);
            lb17.setBorderWidthRight(1);
            Paragraph lb17Title = new Paragraph("17 FREIGHT AND CHARGES REVENUE TONS RATE PER:  TRAIN FREIGHT AS ARRANGED", getTitleFont2());
            lb17.add(lb17Title);
            table4.addCell(lb17);

            Cell lb18 = new Cell();
            lb18.setBorder(1);
            lb18.setBorderWidthLeft(0);
            lb18.setBorderWidthRight(1);
            Paragraph lb18Title = new Paragraph("18 NO. OF ORIGINAL B(S)/L ", getContentFont2());
            lb18.add(lb18Title);
            table4.addCell(lb18);

            Cell lb19 = new Cell();
            lb19.setBorder(1);
            lb19.setBorderWidthLeft(0);
            lb19.setBorderWidthRight(1);
            lb19.setBorderWidthBottom(1);
            Paragraph lb19Title = new Paragraph("19 PLACE AND DATE OF ISSUE", getContentFont2());
            lb19.add(lb19Title);

            Paragraph lb19Value = new Paragraph(ladingBill.getLb19(), getContentFont2());
            lb19.add(lb19Value);
            table4.addCell(lb19);

            Cell lb21 = new Cell();
            lb21.setBorder(1);
            lb21.setBorderWidthRight(0);
            lb21.setBorderWidthBottom(1);
            Paragraph lb21Value = new Paragraph("Signed for the Carrier,  \n" +
                    "Zhengzhou International Hub Development and Construction Co.,Ltd.", getContentFont2());
            lb21.add(lb21Value);
            table4.addCell(lb21);

            document.add(table4);
            Paragraph paragraph3 = new Paragraph("★APPLICABLE ONLY WHEN DOCUMENT USED AS A COMBINED TRANSPORT B/L", getTitleFont2());
            document.add(paragraph3);
            Paragraph paragraph4 = new Paragraph(ladingBill.getLbMark(), getContentFont());
            document.add(paragraph4);
            document.close();
            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadElementException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Table getTable(int column) throws BadElementException {
        Table table = new Table(column);
        table.setWidth(100);
        table.setBorder(0);
        table.setPadding(8);
        table.setSpacing(0);
        return table;
    }

    public static Table getHeaderTable(int column) throws BadElementException {
        Table table = new Table(column);
        table.setWidth(100);
        table.setBorder(0);
        table.setPadding(0);
        table.setSpacing(0);
        return table;
    }

    public static Font getTitleFont() {
        return new Font(Font.BOLD, (float) 7.5, Font.BOLD, new Color(0, 121, 169, 255));
    }

    public static Font getTitleFont2() {
        return new Font(Font.BOLD, (float) 7.5, Font.BOLD, new Color(108, 108, 108));
    }

    public static Font getContentFont2() {
        return new Font(Font.BOLD, (float) 7.5, Font.BOLD, new Color(0, 0, 0));
    }

    public static Font getContentFont3() {
        return new Font(Font.NORMAL, (float) 8, Font.BOLD, new Color(0, 0, 0));
    }

    public static Font getContentFont() {
        return new Font(Font.NORMAL, (float) 10.5, Font.BOLD, new Color(0, 0, 0));
    }

    public static Font getPageHeaderFont1() {
        return new Font(Font.BOLD, (float) 12, Font.BOLD, new Color(0, 0, 0));
    }

    public static Font getPageHeaderFont2() {
        return new Font(Font.BOLD, (float) 10.5, Font.BOLD, new Color(116, 74, 7));
    }

    public static final String TEXT =
            "   RECEIVED by the carrier from the shipper in apparent well-packed goods, containers, or the parcels that have been listed on the bill of lading, otherwise it will be separately prescribed. Goods will be transportation in the combined transport or alternative methods listed on the bill of lading, according to all the terms and regulations listed on the positive and negative of the bill of lading, goods should be transported from the recept place or pick-up port to delivery place or destination port listed on the bill of lading to the consignee or rear-carrier.\n" +
                    "   In the acceptance of this bill of lading, the merchant has expressly accepted and agreed to all the provisions, disclaimers and ordinances printed, written or stamped on the reverse side of this bill of lading.\n" +
                    "   In witness whereof the original quantity of the original bill of lading has been signed. When one of the bills of lading has been used, the other bills of lading are invalid.\n" +
                    "   Copy invalid.";

}
