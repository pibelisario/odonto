package br.caixa.odonto.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.caixa.odonto.models.Atendimento;
import br.caixa.odonto.repositories.AtendimentoRepository;

@Service
public class AtendimentoService {

    @Autowired
    AtendimentoRepository atendimentoRepository;

    @Autowired
    UsuarioService usuarioService;

    public void salvarPaciente(Atendimento atendimento, String userName) {
        atendimento.setUsuario(usuarioService.findByUser(userName));
        atendimentoRepository.save(atendimento);
    }

    public List<Atendimento> listAll(String userName) {
        Calendar dataInicial = Calendar.getInstance();
        Calendar dataFinal = Calendar.getInstance();
        dataInicial.set(Calendar.DAY_OF_MONTH, dataInicial.getMinimalDaysInFirstWeek());
        dataInicial.set(Calendar.DAY_OF_MONTH, dataFinal.getActualMinimum(Calendar.DAY_OF_MONTH));
        LocalDate dI = dataInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dF = LocalDate.now();
        List<Atendimento> atendimentos = atendimentoRepository.findAtendimentoBydataAtendimentoBetween(dI, dF);
        ordenaPorNome(atendimentos);
        List<Atendimento> at = atendimentos.stream()
                .filter(a -> a.getUsuario().getUsername().equalsIgnoreCase(userName)).toList();
        // Collections.sort(at, Comparator.comparing(Atendimento::getNome));
        // ordenaPorNome(at);
        return at;
    }

    public void ordenaPorNome(List<Atendimento> listaAtendimentos) {
        Collections.sort(listaAtendimentos, new Comparator<Atendimento>() {
            @Override
            public int compare(Atendimento a1, Atendimento a2) {

                return a1.getNome().compareTo(a2.getNome());
            }
        });
    }

    public List<Atendimento> findByData(String data, String userName) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
        Date dataInicial = formato.parse(data);
        Calendar dataFinal = Calendar.getInstance();
        dataFinal.setTime(dataInicial);
        dataFinal.set(Calendar.DAY_OF_MONTH,
                dataFinal.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDate dI = dataInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dF = dataFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<Atendimento> atendimentos = atendimentoRepository.findAtendimentoBydataAtendimentoBetween(dI, dF);
        ordenaPorNome(atendimentos);
        List<Atendimento> at = atendimentos.stream()
                .filter(a -> a.getUsuario().getUsername().equalsIgnoreCase(userName)).toList();
        return at;
    }

    public Atendimento findById(Long id) {
        return atendimentoRepository.findById(id).get();
    }

    public void excluirAtendimento(Long id) {
        atendimentoRepository.deleteById(id);
    }

}
