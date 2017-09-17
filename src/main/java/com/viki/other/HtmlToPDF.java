package com.viki.other;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Viki on 2017/9/6.
 * Function: TODO
 */
public class HtmlToPDF {

    public static void main(String[] args) throws DocumentException, IOException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("E:/pdf.pdf"));
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper xmlWorkerHelper = XMLWorkerHelper.getInstance();
        xmlWorkerHelper.parseXHtml(writer, document,new FileInputStream("E://viki//Java Socket编程.htm"));
//        Jsoup.connect("https://mp.weixin.qq.com/s/frBZYX3j7vCEkxx334Vzww").get().

        //step 5
        document.close();
        System.out.println("PDF Created!");
    }

}
