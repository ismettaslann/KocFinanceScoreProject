package com.kocfinance.demo.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private String id;
    private String identityNumber;
    private String nameAndSurname;
    private String phoneNumber;
    private String incomeTranche;
    private String residence;
    private int score;
    private boolean status;
    private String message;
}
