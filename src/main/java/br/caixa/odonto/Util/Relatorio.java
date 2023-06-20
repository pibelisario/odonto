package br.caixa.odonto.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
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
        paragrafoNome.add(new Chunk("Nome: " + usuario.getNome(), new Font(Font.COURIER, 16)));
        paragrafoNome.add(new Paragraph());
        paragrafoNome.add(new Paragraph());
        this.pdf.add(paragrafoNome);

        // Paragraph paragrafoPaciente = new Paragraph();
        // paragrafoNome.setAlignment(Element.ALIGN_LEFT);
        // for (int i = 0; i < atendimento.size(); i++) {
        // paragrafoPaciente.add(new Chunk("Paciente: " +
        // atendimento.get(i).getNome()));
        // paragrafoPaciente.add(new Paragraph());
        // this.pdf.add(paragrafoPaciente);
        // }

        // this.pdf.close();

    }

    @Override
    public void gerarCorpo() {
        this.pdf.open();
        Paragraph paragrafoNome = new Paragraph();
        paragrafoNome.setAlignment(Element.ALIGN_LEFT);
        for (int i = 0; i < atendimento.size(); i++) {
            LocalDate dt1 = atendimento.get(i).getDataAtendimento();
            String dat = dt1.format(formatter);
            paragrafoNome.add(new Chunk(
                    "Prontúario: " + atendimento.get(i).getProntuario()
                            + " Rg: " + atendimento.get(i).getRg()
                            + " Paciente: " + atendimento.get(i).getNome())
                    + " Origem: " + atendimento.get(i).getOrigem()
                    + " Data: " + dat
                    + " Obs: " + atendimento.get(i).getObservacoes());
            paragrafoNome.add(new Paragraph());
            this.pdf.add(paragrafoNome);
        }
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
