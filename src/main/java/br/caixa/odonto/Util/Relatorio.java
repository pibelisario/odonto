package br.caixa.odonto.Util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

import br.caixa.odonto.models.Atendimento;
import br.caixa.odonto.models.Usuario;
import lombok.Data;

@Data
public class Relatorio implements RelatorioInterfece {

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

    private List<Atendimento> atendimento;
    private Usuario usuario;
    private Date data;
    private Document pdf;
    private PdfGState gstate;
    private PdfWriter pdfWriter;
    FileOutputStream pdfOutputFile;

    public Relatorio(List<Atendimento> atendimento, Usuario usuario, Date newDate)
            throws DocumentException, IOException {
        this.usuario = usuario;
        this.atendimento = atendimento;
        this.pdf = new Document();
        pdf.setPageSize(PageSize.A4.rotate());
        this.data = newDate;
        this.pdf.setMargins(20, 20, 20, 20);
        this.pdf.setMarginMirroring(true);
        this.pdfOutputFile = new FileOutputStream("C:\\Workspace\\odonto\\src\\Relatorio\\Relatorio1.pdf");
        this.pdf.open();
        pdfWriter = PdfWriter.getInstance(pdf, pdfOutputFile);
        // Inicializando contador de págs
        gstate = new PdfGState();
        gstate.setFillOpacity(0.3f);
        // gstate.setStrokeOpacity(0.3f);
    }

    @Override
    public void gerarCabecalho() throws BadElementException, IOException {
        // Paragrafo com titúlo do relatório e mes.
        // this.pdf.open();

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

    public void adicionarImagemCabecalho() throws BadElementException, IOException {
        this.pdf.open();
        Image image = Image.getInstance("https://i.imgur.com/MaQQcUN.png");
        image.scalePercent(80, 80);
        image.setAlignment(Image.MIDDLE);
        this.pdf.add(image);
    }

    @Override
    public void gerarCorpo() {

        // this.pdf.open();
        Paragraph paragrafoNome = new Paragraph();
        paragrafoNome.setAlignment(Element.ALIGN_LEFT);

        this.pdf.add(paragrafoNome);

        // Tabela com 3 colunas
        float[] pointColumnWidths1 = { 300f, 300f, 900f, 600f, 350f, 250f, 700f, 200f };
        PdfPTable table = new PdfPTable(pointColumnWidths1);
        table.setHorizontalAlignment(0);
        table.setWidthPercentage(100f);

        PdfPCell c1 = new PdfPCell(new Phrase("Prontú:", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Rg:", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Paciente:", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Origem:", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Status:", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Data:", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Obs:", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);
        c1 = new PdfPCell(new Phrase("T.C.:", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        for (int i = 0; i < atendimento.size(); i++) {
            LocalDate dt1 = atendimento.get(i).getDataAtendimento();
            String dat = dt1.format(formatter);
            table.addCell(atendimento.get(i).getProntuario());
            table.addCell(atendimento.get(i).getRg());
            table.addCell(atendimento.get(i).getNome());
            table.addCell(atendimento.get(i).getOrigem());
            table.addCell(atendimento.get(i).getStatus());
            table.addCell(dat);
            table.addCell(atendimento.get(i).getObservacoes());
            if (atendimento.get(i).getTratamentoConcluido() != null) {
                table.addCell("X");
            } else {
                table.addCell(" ");
            }
        }

        this.pdf.add(table);
    }

    @Override
    public void gerarRodape() {

        this.pdf.newPage();
        this.pdf.open();

        float[] pointColumnWidths1 = { 30f, 10f };
        PdfPTable table = new PdfPTable(pointColumnWidths1);

        PdfPCell c1 = new PdfPCell(new Phrase("Número pacientes atendidos", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(pacAtenditos().toString(), new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Número de exames clínicos", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(pcClinico().toString(), new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Número de avalição encaminhada Orto", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(pcOrto().toString(), new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Número de tratamento concluídos", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(tratamentoConcluido().toString(), new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Números de faltas", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(pcFalta().toString(), new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Número de emergência", new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(pcEmergencia().toString(), new Font(Font.BOLD, 14)));
        c1.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(c1);

        this.pdf.add(table);

    }

    public void gerarAssinatura() {

        // Paragrafo com titúlo do relatório e mes.

        Paragraph assinatura = new Paragraph();
        assinatura.add(new Paragraph());
        assinatura.add(new Paragraph());
        assinatura.add(new Paragraph());
        assinatura.add(new Paragraph());
        assinatura.add(new Paragraph());
        assinatura.add(new Paragraph());
        assinatura.add(new Paragraph());
        assinatura.add(new Paragraph());
        assinatura.add(new Paragraph());
        assinatura.setAlignment(Element.ALIGN_CENTER);
        assinatura.add(new Chunk("_________________________________",
                new Font(Font.BOLD, 16)));
        assinatura.add(new Paragraph());
        assinatura.add(new Paragraph());
        assinatura.add(new Chunk(usuario.getNome(),
                new Font(Font.BOLD, 16)));

        Paragraph carimbo = new Paragraph();
        carimbo.add(new Paragraph());
        carimbo.add(new Paragraph());
        carimbo.add(new Paragraph());
        carimbo.setAlignment(Element.ALIGN_CENTER);
        carimbo.add(new Chunk("_________________________________",
                new Font(Font.BOLD, 16)));
        carimbo.add(new Paragraph());
        carimbo.add(new Paragraph());
        carimbo.add(new Chunk("  Carimbo    ",
                new Font(Font.BOLD, 16)));

        this.pdf.add(assinatura);
        this.pdf.add(carimbo);

    }

    public Integer pacAtenditos() {
        return atendimento.size() - pcFalta();
    }

    public Integer pcFalta() {
        int n = 0;

        for (int i = 0; i < atendimento.size(); i++) {
            if (atendimento.get(i).getStatus().equals("Falta")) {
                n++;
            }
        }

        return n;

    }

    public Integer pcClinico() {

        int n = 0;

        for (int i = 0; i < atendimento.size(); i++) {
            if (atendimento.get(i).getOrigem().equals("Ex-Clínico/L. Espera")) {
                n++;
            }
        }
        return n;
    }

    public Integer pcOrto() {

        int n = 0;

        for (int i = 0; i < atendimento.size(); i++) {
            if (atendimento.get(i).getOrigem().equals("Ex-Clínico/Enca.Orto")) {
                n++;
            }
        }
        return n;

    }

    public Integer pcEmergencia() {

        int n = 0;

        for (int i = 0; i < atendimento.size(); i++) {
            if (atendimento.get(i).getStatus().equals("Emergência")) {
                n++;
            }
        }
        return n;

    }

    public Integer tratamentoConcluido() {

        int n = 0;

        for (int i = 0; i < atendimento.size(); i++) {
            if (atendimento.get(i).getTratamentoConcluido() != null) {
                n++;
            }
        }

        return n;
    }

    public void addPageNumber() throws IOException {
        PdfReader reader = new PdfReader("C:\\Workspace\\odonto\\src\\Relatorio\\Relatorio1.pdf");
        PdfStamper stamper = new PdfStamper(reader,
                new FileOutputStream("C:\\Workspace\\odonto\\src\\Relatorio\\RelatorioMensalPag.pdf"));
        stamper.setRotateContents(false);
        for (int i = 1; i <= reader.getNumberOfPages(); i++) {
            Phrase t = new Phrase(i + " de " + reader.getNumberOfPages(),
                    new Font(Font.HELVETICA, 14));

            float xt = reader.getPageSize(i).getWidth() - 50;
            float yt = reader.getPageSize(i).getBottom(5);
            ColumnText.showTextAligned(
                    stamper.getOverContent(i), Element.ALIGN_RIGHT,
                    t, xt, yt, 0);
        }
        stamper.close();
        reader.close();
    }

    @Override
    public void imprimir() {

        if (this.pdf != null || this.pdf.isOpen()) {
            this.pdf.close();
        }
    }

}
