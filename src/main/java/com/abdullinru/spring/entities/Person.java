package com.abdullinru.spring.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class Person {
    private Long id;
    private String name;
    private Integer age;
}
