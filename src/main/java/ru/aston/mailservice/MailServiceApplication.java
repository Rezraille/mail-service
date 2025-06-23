package ru.aston.mailservice;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.aston.mailservice.service.MailServiceImpl;


@SpringBootApplication
public class MailServiceApplication {
    private final MailServiceImpl emailService;

    public MailServiceApplication(MailServiceImpl emailService) {
        this.emailService = emailService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }
}
