package ru.aston.mailservice.kafka;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.DirtiesContext;
import ru.aston.mailservice.service.MailServiceImpl;

import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = { "listeners=PLAINTEXT://localhost:9092", "port=9092" })
public class KafkaConsumerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @MockBean
    private MailServiceImpl mailService;

    @MockBean
    private JavaMailSender mailSender;

    @Test
    public void whenCreateMessageReceived_thenSendAddEmail() throws Exception {
        String email = "test@example.com";

        kafkaTemplate.send("USERS", "create", email);

        verify(mailService, timeout(5000)).sendEmailAboutAdd(email);
    }

    @Test
    public void whenRemoveMessageReceived_thenSendDeleteEmail() throws Exception {
        String email = "test@example.com";

        kafkaTemplate.send("USERS", "remove", email);

        verify(mailService, timeout(5000)).sendEmailAboutDelete(email);
    }
}