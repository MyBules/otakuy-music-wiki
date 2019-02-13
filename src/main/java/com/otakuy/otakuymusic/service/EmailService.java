package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.util.EmailUtil;
import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailService {
    private final EmailUtil emailUtil;

    public Mono<MimeMessage> sendVerificationEmail(String emailAddr, VerificationCodeUtil.VerificationCode verificationCode) throws MessagingException {
        return emailUtil.sendVerificationEmail(emailAddr, verificationCode);
    }
}
