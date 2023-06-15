package br.caixa.odonto.services;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;

import br.caixa.odonto.Util.Relatorio;

@Service
public class RelatorioService {

    public void gerarRelatorio() throws DocumentException, FileNotFoundException {
        Relatorio relatorio = new Relatorio();

        relatorio.primeiroPdf("aasdfasdf");
    }

}
