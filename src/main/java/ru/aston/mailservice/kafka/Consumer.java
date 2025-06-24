package ru.aston.mailservice.kafka;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.aston.mailservice.service.MailServiceImpl;

@Service
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final MailServiceImpl mailService;

    public Consumer(MailServiceImpl mailService) {
        this.mailService = mailService;
    }


    @KafkaListener(topics = "USERS")
    public void consume(final @Payload String message,
                        final @Header(KafkaHeaders.RECEIVED_KEY) String key,
                        final Acknowledgment acknowledgment
    ) {
        logger.info("consume() key = {}, message = {}", key, message);
        if (Command.CREATE.is(key)) {
            mailService.sendEmailAboutAdd(message);
        } else if (Command.DELETE.is(key)) {
            mailService.sendEmailAboutDelete(message);
        }

        acknowledgment.acknowledge();
    }
}
