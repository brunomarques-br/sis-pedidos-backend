package br.com.sis.pedidos.backend.config;

import br.com.sis.pedidos.backend.services.DbService;
import br.com.sis.pedidos.backend.services.EmailService;
import br.com.sis.pedidos.backend.services.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DbService dbService;

    /*criando um pattern strategy para controle na criação da base de dados.*/
    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {

        if (!"create".equals(strategy))
            return false;

        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }
}
