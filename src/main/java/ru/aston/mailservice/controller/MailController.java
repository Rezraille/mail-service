package ru.aston.mailservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.mailservice.service.MailServiceImpl;


@RestController
@RequestMapping("api/mail")
public class MailController
{
    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    private MailServiceImpl mailService;

    public MailController(MailServiceImpl mailService)
    {
        this.mailService = mailService;
    }

    @GetMapping("sendAdd/{mail}")
    public ResponseEntity<Void> sendAdd(@PathVariable String mail) {
        logger.info("sendAdd() mail = {}", mail);
        mailService.sendEmailAboutAdd(mail);
        return ResponseEntity.ok().build();
    }


    @GetMapping("sendRemove/{mail}")
    public ResponseEntity<Void> sendRemove(@PathVariable String mail) {
        logger.info("sendRemove() mail = {}", mail);
        mailService.sendEmailAboutDelete(mail);
        return ResponseEntity.ok().build();
    }
}
