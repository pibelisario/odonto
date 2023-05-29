package br.caixa.odonto.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
        List<Atendimento> pacientes = atendimentoRepository.findAll();
        return pacientes;
    }

    public List<Atendimento> findByData(String data) throws ParseException {
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM");
        Date dataInifical = formato.parse(data);
        Date dataFinal = formato.parse(data);

        Calendar instance = dataFinal;
        instance.set(Calendar.MONTH, 8);
        instance.set(Calendar.DAY_OF_MONTH, instance.getActualMaximum(Calendar.DAY_OF_MONTH));

        System.out.println("Data Inicial: " + dataInifical + " Data Final: " + dataFinal);
        // List<Atendimento> atendimento =
        // atendimentoRepository.findAtendimentoByDataBetween(dataInifical, dataFinal);
        return null;
    }

    public Atendimento findById(Long id) {
        return atendimentoRepository.findById(id).get();
    }

    public void excluirPaciente(Long id) {
        atendimentoRepository.deleteById(id);
    }

}
