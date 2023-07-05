package br.caixa.odonto.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lowagie.text.DocumentException;

import br.caixa.odonto.services.RelatorioService;

@Controller
public class RelatorioController {

    @Autowired
    RelatorioService relatorioService;

    @GetMapping("relatorio")
    public String gerarRelatorio() {
        return "/relatorio";
    }

    @GetMapping("/downloadRelatorioPorData")
    public HttpEntity<byte[]> gerarRelatorioPorData(@RequestParam String data, @RequestParam String userName)
            throws IOException, DocumentException, ParseException {

        System.out.println(data);
        System.out.println(userName);

        relatorioService.gerarRelatorio(data, userName);

        byte[] arquivo = Files
                .readAllBytes(Paths.get("C:\\Workspace\\odonto\\src\\Relatorio\\RelatorioMensalPag.pdf"));

        org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();

        httpHeaders.add("Content-Disposition",
                "attachment;filename=\"relatorioMensal.pdf\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

        return entity;

    }

}
