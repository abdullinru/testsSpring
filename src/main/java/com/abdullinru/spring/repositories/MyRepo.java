package com.abdullinru.spring.repositories;

import com.abdullinru.spring.entities.Person;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MyRepo {

    public Optional<Person> findPersonById(Long id) {
        return Optional.of(Person.builder()
                .withId(1L)
                .withAge(22)
                .withName("Anonym")
                .build());
    }

    public void addAges(Integer age1, Integer age2) {
        System.out.println("hnhnh");
    }
}
