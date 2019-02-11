package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.repository.VerificationCodeRepository;
import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final VerificationCodeUtil verificationCodeUtil;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public VerificationCodeService(VerificationCodeRepository verificationCodeRepository, VerificationCodeUtil verificationCodeUtil, ReactiveMongoTemplate reactiveMongoTemplate) {
        this.verificationCodeRepository = verificationCodeRepository;
        this.verificationCodeUtil = verificationCodeUtil;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    public Mono<VerificationCodeUtil.VerificationCode> getVerificationCode() {
        return verificationCodeRepository.save(verificationCodeUtil.creatVerificationCode());
    }

    public Mono<VerificationCodeUtil.VerificationCode> getPasswordVerificationCode(String email) {
        return verificationCodeRepository.save(verificationCodeUtil.creatVerificationCode(email));
    }

    public Mono<VerificationCodeUtil.VerificationCode> checkVerificationCode(VerificationCodeUtil.VerificationCode verificationCode) {
        Query query = new Query(Criteria.where("_id").is(verificationCode.getId()).and("code").is(verificationCode.getCode()));
        return reactiveMongoTemplate.findAndRemove(query, VerificationCodeUtil.VerificationCode.class, "verificationCode");
    }

    public Mono<VerificationCodeUtil.VerificationCode> checkPasswordVerificationCode(VerificationCodeUtil.VerificationCode verificationCode) {
        Query query = new Query(Criteria.where("_id").is(verificationCode.getId()).and("code").is(verificationCode.getCode()).and("content").is(verificationCode.getContent()));
        return reactiveMongoTemplate.findAndRemove(query, VerificationCodeUtil.VerificationCode.class, "verificationCode");
    }
}
