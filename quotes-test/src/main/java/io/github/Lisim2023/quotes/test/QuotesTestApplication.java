package io.github.Lisim2023.quotes.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(exclude = {JacksonAutoConfiguration.class})
@SpringBootApplication
public class QuotesTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuotesTestApplication.class, args);
    }

}
