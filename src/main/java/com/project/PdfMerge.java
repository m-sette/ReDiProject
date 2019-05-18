package com.project;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PdfMerge {
    public static void mergePdfFiles(List<InputStream> inputPdfList, OutputStream outputStream) throws Exception{

        //First step is to create document and pdf reader object.
        Document document = new Document();
        List<PdfReader> reader = new ArrayList<PdfReader>();
        int totalPages = 0;

        //pdf Iterator object is created using inputPdfList
        Iterator<InputStream> pdfIterator = inputPdfList.iterator();

        //Create reader list for the input pdf files
        while (pdfIterator.hasNext()){
            InputStream pdf = pdfIterator.next();
            PdfReader pdfReader = new PdfReader(pdf);
            reader.add (pdfReader);
            totalPages += pdfReader.getNumberOfPages();
        }

        //Create writer for the outputStream
        PdfWriter writer = PdfWriter.getInstance(document, outputStream);

        //Open document.
        document.open();

        //Contain the pdf data.
        PdfContentByte pageContentByte = writer.getDirectContent();

        PdfImportedPage pdfImportedPage;
        int currentPdfReaderPage = 1;
        Iterator<PdfReader> iteratorPDFReader = reader.iterator();

        //Iterate and process the reader list.
        while (iteratorPDFReader.hasNext()){
            PdfReader pdfReader = iteratorPDFReader.next();
            //Create page and add content.
            while (currentPdfReaderPage <= pdfReader.getNumberOfPages()){
                document.newPage();
                pdfImportedPage = writer.getImportedPage(pdfReader, currentPdfReaderPage);
                pageContentByte.addTemplate(pdfImportedPage, 0, 0);
                currentPdfReaderPage++;
            }
            currentPdfReaderPage = 1;
        }

        //close the document and outputStream.
        outputStream.flush();
        document.close();
        outputStream.close();

        System.out.println("Pdf files merged successfully");

    }
}
