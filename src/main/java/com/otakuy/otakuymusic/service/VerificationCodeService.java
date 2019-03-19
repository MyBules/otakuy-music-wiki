package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.repository.VerificationCodeRepository;
import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VerificationCodeService {

    private final VerificationCodeRepository verificationCodeRepository;
    private final VerificationCodeUtil verificationCodeUtil;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Mono<VerificationCodeUtil.VerificationCode> getVerificationCode() {
        return verificationCodeRepository.save(verificationCodeUtil.creatVerificationCode());
    }

    public Mono<VerificationCodeUtil.VerificationCode> getPasswordVerificationCode(String email) {
        return verificationCodeRepository.save(verificationCodeUtil.creatVerificationCode(email));
    }

    public Mono<VerificationCodeUtil.VerificationCode> checkVerificationCode(VerificationCodeUtil.VerificationCode verificationCode) {
        Query query = new Query(Criteria.where("_id").is(verificationCode.getId()).and("code").regex(verificationCode.getCode(), "i"));
        return reactiveMongoTemplate.findAndRemove(query, VerificationCodeUtil.VerificationCode.class, "verificationCode");
    }

    public Mono<VerificationCodeUtil.VerificationCode> checkPasswordVerificationCode(VerificationCodeUtil.VerificationCode verificationCode) {
        Query query = new Query(Criteria.where("_id").is(verificationCode.getId()).and("code").is(verificationCode.getCode()).and("content").is(verificationCode.getContent()));
        return reactiveMongoTemplate.findAndRemove(query, VerificationCodeUtil.VerificationCode.class, "verificationCode");
    }
}
