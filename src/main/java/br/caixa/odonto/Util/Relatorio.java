package br.caixa.odonto.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class Relatorio {

    public void primeiroPdf(String frase) throws DocumentException, FileNotFoundException {

        Document documentoPDF = new Document();

        PdfWriter.getInstance(documentoPDF,
                new FileOutputStream("C:\\Workspace\\odonto\\src\\Relatorio\\Relatorio1.pdf"));

        documentoPDF.open();

        Paragraph paragrafoTeste = new Paragraph(frase);

        documentoPDF.add(paragrafoTeste);

        documentoPDF.close();

    }

}
