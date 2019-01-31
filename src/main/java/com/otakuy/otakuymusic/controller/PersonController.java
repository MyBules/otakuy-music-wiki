package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Person;
import com.otakuy.otakuymusic.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/person")
public class PersonController {
    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping("/")
    public Flux<Person> getAllPerson() {
        return personRepository.findAll();
    }
    @PostMapping("/")
    public Mono<Person> add(@RequestBody Person person) {
        person.setId(null);
        return this.personRepository.save(person);
    }
}
