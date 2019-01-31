package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VerificationCodeRepository extends ReactiveMongoRepository<VerificationCodeUtil.VerificationCode,String> {
    @Query("{ '_id' : ?0 , 'code':?1 }")
    Mono<VerificationCodeUtil.VerificationCode> findByCodeAndId(String id, String code);
}
