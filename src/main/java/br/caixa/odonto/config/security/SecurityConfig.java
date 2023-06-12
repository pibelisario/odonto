package br.caixa.odonto.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(
                authorizeConfig -> {
                    authorizeConfig.requestMatchers("/logout").permitAll();
                    authorizeConfig.requestMatchers("/login").permitAll();
                    authorizeConfig.requestMatchers("../../../../../../resources/static/assets/css/**").permitAll();
                    authorizeConfig.requestMatchers("../../../../../../resources/static/assets/img/**").permitAll();
                    authorizeConfig.requestMatchers("../../../../../../resources/static/assets/js/**").permitAll();
                    authorizeConfig.requestMatchers("../../../../../../resources/static/assets/scss/**").permitAll();
                    authorizeConfig.requestMatchers("../../../../../../resources/static/assets/vendor/**").permitAll();
                    authorizeConfig.requestMatchers("/**").permitAll();
                    // authorizeConfig.anyRequest().authenticated();
                });
        http.csrf().disable();

        http.formLogin((form) -> form
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/cadAtendimento"));

        http.logout((logout) -> logout.permitAll());

        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
