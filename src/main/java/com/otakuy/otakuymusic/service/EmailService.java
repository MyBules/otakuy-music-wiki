package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.util.EmailUtil;
import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final EmailUtil emailUtil;

    public EmailService(EmailUtil emailUtil) {
        this.emailUtil = emailUtil;
    }

    public Mono<MimeMessage> sendVerificationEmail(String emailAddr, VerificationCodeUtil.VerificationCode verificationCode) throws MessagingException {
        return emailUtil.sendVerificationEmail(emailAddr,verificationCode);
    }
}
