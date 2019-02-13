package com.otakuy.otakuymusic.service;

import com.mongodb.client.result.UpdateResult;
import com.otakuy.otakuymusic.exception.StarException;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Star;
import com.otakuy.otakuymusic.repository.StarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StarService {
    private final StarRepository starRepository;
    private final UserService userService;

    @Transactional
    public Mono<Star> create(Star star) {
        return userService.updateStarById(star.getStarFrom(), -star.getNum()).then(userService.updateStarById(star.getStarTo(), star.getNum())).map(UpdateResult::wasAcknowledged).flatMap(result -> {
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
