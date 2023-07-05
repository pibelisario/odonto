package br.caixa.odonto.Util;

import java.io.IOException;

import com.lowagie.text.BadElementException;

public interface RelatorioInterfece {

    public void gerarCabecalho() throws BadElementException, IOException;

    public void gerarCorpo();

    public void gerarRodape();

    public void imprimir();

}
