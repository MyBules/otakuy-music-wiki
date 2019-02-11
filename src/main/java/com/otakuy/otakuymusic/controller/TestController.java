package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.util.EmailUtil;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@RestController
@Log4j2
public class TestController {
    private final EmailUtil emailUtil;

    @Autowired
    public TestController(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }
/*
    @GetMapping("/test")
    public Mono<ResponseEntity<Result<String>>> sendEmailTest(@RequestParam("emial") String email) throws MessagingException {
        log.info("start");
        Mono<MimeMessage> stringMono = emailUtil.sendVerificationEmail(email,"");
        log.info("over");
        return stringMono.map(s->ResponseEntity.ok().body(new Result<>("ok", "发送邮件成功")));
    }*/
}
