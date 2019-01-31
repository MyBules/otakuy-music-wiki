package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.model.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person,String> {

}
