package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.repository.VerificationCodeRepository;
import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final VerificationCodeUtil verificationCodeUtil;

    @Autowired
    public VerificationCodeService(VerificationCodeRepository verificationCodeRepository, VerificationCodeUtil verificationCodeUtil) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.verificationCodeUtil = verificationCodeUtil;
    }

    public Mono<VerificationCodeUtil.VerificationCode> getVerificationCode() {
        return verificationCodeRepository.save(verificationCodeUtil.creatVerificationCode());
    }

    public Mono<VerificationCodeUtil.VerificationCode> checkVerificationCode(VerificationCodeUtil.VerificationCode verificationCode) {
        return verificationCodeRepository.findByIdAndCode(verificationCode.getId(), verificationCode.getCode());
    }
}
