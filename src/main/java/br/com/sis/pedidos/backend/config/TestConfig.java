package br.com.sis.pedidos.backend.config;

import br.com.sis.pedidos.backend.services.DbService;
import br.com.sis.pedidos.backend.services.EmailService;
import br.com.sis.pedidos.backend.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    private final DbService dbService;

    @Autowired
    public TestConfig(DbService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        dbService.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }
}
