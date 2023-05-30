package br.caixa.odonto.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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

    public void salvarPaciente(Atendimento atendimento) {
        atendimentoRepository.save(atendimento);
    }

    public List<Atendimento> listAll() {
        Calendar dataInicial = Calendar.getInstance();
        Calendar dataFinal = Calendar.getInstance();
        dataInicial.set(Calendar.DAY_OF_MONTH, dataInicial.getMinimalDaysInFirstWeek());
        dataInicial.set(Calendar.DAY_OF_MONTH, dataFinal.getActualMinimum(Calendar.DAY_OF_MONTH));
        LocalDate dI = dataInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dF = LocalDate.now();
        return atendimentoRepository.findAtendimentoBydataAtendimentoBetween(dI, dF);
    }

    public List<Atendimento> findByData(String data) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
        Date dataInicial = formato.parse(data);
        Calendar dataFinal = Calendar.getInstance();
        dataFinal.setTime(dataInicial);
        dataFinal.set(Calendar.DAY_OF_MONTH, dataFinal.getActualMaximum(Calendar.DAY_OF_MONTH));
        LocalDate dI = dataInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate dF = dataFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return atendimentoRepository.findAtendimentoBydataAtendimentoBetween(dI, dF);
    }

    public Atendimento findById(Long id) {
        return atendimentoRepository.findById(id).get();
    }

    public void excluirAtendimento(Long id) {
        atendimentoRepository.deleteById(id);
    }

}
