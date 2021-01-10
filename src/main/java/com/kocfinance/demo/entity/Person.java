package com.kocfinance.demo.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "person")
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class Person {
    @Id
    private String id;
    private String identityNumber;
    private String nameAndSurname;
    private String phoneNumber;
    private String incomeTranche;
    private String residence;
    private int score;
}
