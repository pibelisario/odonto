package br.caixa.odonto.services;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.DocumentException;

import br.caixa.odonto.Util.Relatorio;
import br.caixa.odonto.models.Atendimento;
import br.caixa.odonto.models.Usuario;

@Service
public class RelatorioService {

    @Autowired
    AtendimentoService atendimentoService;

    @Autowired
    UsuarioService usuarioService;

    public void gerarRelatorio(String data, String userName)
            throws DocumentException, FileNotFoundException, ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
        Date newDate = formato.parse(data);
        System.out.println(newDate);

        List<Atendimento> atendimentos = atendimentoService.findByData(data, userName);
        Usuario usuario = usuarioService.findByUser(userName);

        Relatorio relatorio = new Relatorio(atendimentos, usuario, newDate);
        relatorio.gerarCabecalho();
        relatorio.gerarCorpo();
        relatorio.gerarRodape();
        relatorio.gerarAssinatura();
        relatorio.imprimir();

    }

    public void ordenaPorNome(List<Atendimento> listaAtendimentos) {
        Collections.sort(listaAtendimentos, new Comparator<Atendimento>() {
            @Override
            public int compare(Atendimento a1, Atendimento a2) {

                return a1.getNome().compareTo(a2.getNome());
            }
        });
    }

}
