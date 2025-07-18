package ru.aston.mailservice.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class MailServiceImplTest {


    @Autowired
    private MailServiceImpl mailService;

    @MockitoSpyBean
    private JavaMailSender mailSender;

    @Test
    public void sendEmailAboutAdd_ShouldSendCorrectEmail() {
        String testEmail = "test@example.com";

        mailService.sendEmailAboutAdd(testEmail);

        verify(mailSender).send(any(SimpleMailMessage.class));
    }

    @Test
    public void sendEmailAboutDelete_ShouldSendCorrectEmail() {
        String testEmail = "test@example.com";

        mailService.sendEmailAboutDelete(testEmail);

        verify(mailSender).send(any(SimpleMailMessage.class));
    }

}
