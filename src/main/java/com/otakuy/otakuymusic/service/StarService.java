package com.otakuy.otakuymusic.service;

import com.mongodb.client.result.UpdateResult;
import com.otakuy.otakuymusic.exception.StarException;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Star;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.repository.StarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StarService {
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final StarRepository starRepository;

    @Transactional
    public Mono<Star> create(Star star) {
        return reactiveMongoTemplate.updateFirst(new Query(where("_id").is(star.getStarFrom())),
                new Update().inc("star", -star.getNum()), User.class).then(reactiveMongoTemplate.updateFirst(new Query(where("_id").is(star.getStarTo())),
                new Update().inc("star", star.getNum()), User.class)).map(UpdateResult::wasAcknowledged).flatMap(result -> {
            if (result) {
                return starRepository.save(star);
            }
            throw new StarException(new Result(HttpStatus.BAD_REQUEST, "打赏逻辑遇到未知异常,请重新提交"));
        });
    }

    public Flux<Star> findAllByStarAt(String album_id) {
        return starRepository.findAllByStarAt(album_id);
    }
}
