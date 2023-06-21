package br.caixa.odonto.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.lowagie.text.Anchor;
import com.lowagie.text.Chapter;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Section;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import br.caixa.odonto.models.Atendimento;
import br.caixa.odonto.models.Usuario;
import lombok.Data;

@Data
public class Relatorio implements RelatorioInterfece {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private List<Atendimento> atendimento;
    private Usuario usuario;
    private Date data;
    private Document pdf;

    public Relatorio(List<Atendimento> atendimento, Usuario usuario, Date newDate)
            throws DocumentException, FileNotFoundException {
        this.usuario = usuario;
        this.atendimento = atendimento;
        this.pdf = new Document();
        pdf.setPageSize(PageSize.A4.rotate());
        this.data = newDate;
        this.pdf.open();
        PdfWriter.getInstance(pdf,
                new FileOutputStream("C:\\Workspace\\odonto\\src\\Relatorio\\Relatorio1.pdf"));
    }

    // public void primeiroPdf(String frase) throws DocumentException,
    // FileNotFoundException {

    // Document documentoPDF = new Document();

    // PdfWriter.getInstance(documentoPDF,
    // new
    // FileOutputStream("C:\\Workspace\\odonto\\src\\Relatorio\\Relatorio1.pdf"));

    // documentoPDF.open();

    // Paragraph paragrafoTeste = new Paragraph(frase);

    // documentoPDF.add(paragrafoTeste);

    // documentoPDF.close();

    // }

    @Override
    public void gerarCabecalho() {
        // Paragrafo com titúlo do relatório e mes.
        this.pdf.open();

        Paragraph paragrafoTitulo = new Paragraph();
        paragrafoTitulo.setAlignment(Element.ALIGN_CENTER);
        paragrafoTitulo.add(new Chunk("RELATÓRIO DE ATENDIMENTO DIÁRIO " + new FormatDate().formatarData(data),
                new Font(Font.BOLD, 16)));
        paragrafoTitulo.add(new Paragraph());
        paragrafoTitulo.add(new Paragraph());
        paragrafoTitulo.add(new Paragraph());
        this.pdf.add(paragrafoTitulo);

        // Paragrafo com informações do dentista
        Paragraph paragrafoNome = new Paragraph();
        paragrafoNome.setAlignment(Element.ALIGN_LEFT);
        paragrafoNome.add(new Chunk("Dr: " + usuario.getNome(), new Font(Font.BOLD, 24)));
        paragrafoNome.add(new Paragraph());
        paragrafoNome.add(new Paragraph());
        this.pdf.add(paragrafoNome);

    }

    @Override
    public void gerarCorpo() {

        this.pdf.open();
        Paragraph paragrafoNome = new Paragraph();
        paragrafoNome.add(new Paragraph());
        paragrafoNome.add(new Paragraph());
        paragrafoNome.add(new Paragraph());
        paragrafoNome.add(new Paragraph());
        paragrafoNome.add(new Paragraph());
        paragrafoNome.add(new Paragraph());
        paragrafoNome.add(new Paragraph());
        paragrafoNome.setAlignment(Element.ALIGN_LEFT);

        this.pdf.add(paragrafoNome);

        // Tabela com 3 colunas
        float[] pointColumnWidths1 = { 30f, 30f, 90f, 60f, 35f, 70f };
        PdfPTable table = new PdfPTable(pointColumnWidths1);

        PdfPCell c1 = new PdfPCell(new Phrase("Prontú.:", new Font(Font.NORMAL, 12)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Rg: ", new Font(Font.NORMAL, 12)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Paciente: ", new Font(Font.NORMAL, 12)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Origem: ", new Font(Font.NORMAL, 12)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Data: ", new Font(Font.NORMAL, 12)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Obervações: ", new Font(Font.NORMAL, 12)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        for (int i = 0; i < atendimento.size(); i++) {
            LocalDate dt1 = atendimento.get(i).getDataAtendimento();
            String dat = dt1.format(formatter);
            table.addCell(atendimento.get(i).getProntuario());
            table.addCell(atendimento.get(i).getRg());
            table.addCell(atendimento.get(i).getNome());
            table.addCell(atendimento.get(i).getOrigem());
            table.addCell(dat);
            table.addCell(atendimento.get(i).getObservacoes());
        }

        this.pdf.add(table);
        this.pdf.close();
    }

    @Override
    public void gerarRodape() {
    }

    @Override
    public void imprimir() {

        if (this.pdf != null || this.pdf.isOpen()) {
            this.pdf.close();
        }
    }

}
