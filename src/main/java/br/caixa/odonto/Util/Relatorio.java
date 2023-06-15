package br.caixa.odonto.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import br.caixa.odonto.models.Atendimento;
import br.caixa.odonto.models.Usuario;

public class Relatorio implements RelatorioInterfece {

    private List<Atendimento> atendimento;
    private Usuario usuario;

    public void primeiroPdf(String frase) throws DocumentException, FileNotFoundException {

        Document documentoPDF = new Document();

        PdfWriter.getInstance(documentoPDF,
                new FileOutputStream("C:\\Workspace\\odonto\\src\\Relatorio\\Relatorio1.pdf"));

        documentoPDF.open();

        Paragraph paragrafoTeste = new Paragraph(frase);

        documentoPDF.add(paragrafoTeste);

        documentoPDF.close();

    }

    @Override
    public void gerarCabecalho() {
    }

    @Override
    public void gerarCorpo() {
    }

    @Override
    public void gerarRodape() {
    }

    @Override
    public void imprimir() {
    }

}
