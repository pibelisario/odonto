package br.caixa.odonto.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public HttpEntity<byte[]> gerarRelatorioPorData() throws IOException {

        relatorioService.gerarRelatorio();

        byte[] arquivo = Files
                .readAllBytes(Paths.get("C:\\Workspace\\odonto\\src\\Relatorio\\Relatorio1.pdf"));

        org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();

        httpHeaders.add("Content-Disposition",
                "attachment;filename=\"relatorioMensal.pdf\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);

        return entity;

    }

}
