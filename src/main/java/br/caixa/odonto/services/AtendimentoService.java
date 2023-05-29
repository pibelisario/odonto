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
        Calendar dataFinal = Calendar.getInstance();
        dataFinal.set(dataInifical.toInstant()., dataFinal.getActualMaximum(Calendar.DAY_OF_MONTH));
        // Date dataFinal = formato.parse(data);

        System.out.println("Data Inicial: " + dataInifical + " Data Final: " + dataFinal);
        return null;
    }

    public Atendimento findById(Long id) {
        return atendimentoRepository.findById(id).get();
    }

    public void excluirPaciente(Long id) {
        atendimentoRepository.deleteById(id);
    }

}
