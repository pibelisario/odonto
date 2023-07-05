package br.caixa.odonto.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
            throws DocumentException, ParseException, IOException {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
        Date newDate = formato.parse(data);
        System.out.println(newDate);

        List<Atendimento> atendimentos = atendimentoService.findByData(data, userName);
        Usuario usuario = usuarioService.findByUser(userName);

        Relatorio relatorio = new Relatorio(atendimentos, usuario, newDate);
        relatorio.adicionarImagemCabecalho();
        relatorio.gerarCabecalho();
        relatorio.gerarCorpo();
        relatorio.gerarRodape();
        relatorio.gerarAssinatura();
        relatorio.imprimir();

    }

}
