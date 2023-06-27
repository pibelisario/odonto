package br.caixa.odonto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class OdontoRelatorioApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(OdontoRelatorioApplication.class, args);

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(OdontoRelatorioApplication.class);
	}

}
