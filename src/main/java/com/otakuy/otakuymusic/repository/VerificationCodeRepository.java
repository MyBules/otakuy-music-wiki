package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationCodeRepository extends ReactiveMongoRepository<VerificationCodeUtil.VerificationCode, String> {
}
