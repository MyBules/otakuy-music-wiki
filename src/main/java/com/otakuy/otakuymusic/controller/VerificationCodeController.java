package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.service.VerificationCodeService;
import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/verificationCode")
public class VerificationCodeController {
    private final VerificationCodeService verificationCodeService;

    @Autowired
    public VerificationCodeController(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }

    @GetMapping("")
    public Mono<ResponseEntity<Result<VerificationCodeUtil.VerificationCode>>> getVerificationCode() {
        Mono<VerificationCodeUtil.VerificationCode> verificationCode = verificationCodeService.getVerificationCode();
        return verificationCode.map(code -> ResponseEntity.ok(new Result<>("ok", code)));
    }
}
