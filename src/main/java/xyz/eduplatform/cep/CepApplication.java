package xyz.eduplatform.cep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CepApplication {

    public static void main(String[] args) {
        SpringApplication.run(CepApplication.class, args);
    }

}
