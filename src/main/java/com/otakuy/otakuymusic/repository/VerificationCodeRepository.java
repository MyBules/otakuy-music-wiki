package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VerificationCodeRepository extends ReactiveMongoRepository<VerificationCodeUtil.VerificationCode, String> {
    Mono<VerificationCodeUtil.VerificationCode> findByIdAndCode(String id, String code);
}
