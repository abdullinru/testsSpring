package com.abdullinru.spring.services;

import com.abdullinru.spring.entities.Person;
import com.abdullinru.spring.repositories.MyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyService {

    private final MyRepo repo;

    public Person getPersonById(Long id) {
        id = id*2;
        // todo какая-то логика

//        repo.findPersonById(id++);
        return repo.findPersonById(id).orElse(defaultPerson());
    }

    private Person defaultPerson() {
        return Person.builder()
                .withId(1L)
                .withAge(2)
                .withName("default")
                .build();
    }

    public void addAges(Integer age1, Integer age2) {
        repo.addAges(age1, age2);
    }

}
