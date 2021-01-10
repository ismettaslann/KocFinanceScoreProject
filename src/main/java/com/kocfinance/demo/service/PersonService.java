package com.kocfinance.demo.service;

import com.kocfinance.demo.dto.PersonDTO;
import com.kocfinance.demo.entity.Person;
import com.kocfinance.demo.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/score")
@CrossOrigin("*")
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ScoreService scoreService;

    @PostMapping(path = "/insert-new-person-score/")
    public PersonDTO insertNewPersonScore(@RequestBody Person request){
        if(request == null){
            return this.mapToDTO(Person.builder().build());
        }
        else{
            request.setScore(scoreService.calculateScore(request));
            Person person = Person.builder().id(request.getIdentityNumber())
                    .identityNumber(request.getIdentityNumber())
                    .nameAndSurname(request.getNameAndSurname())
                    .incomeTranche(request.getIncomeTranche())
                    .phoneNumber(request.getPhoneNumber())
                    .residence(request.getResidence())
                    .score(request.getScore())
                    .build();
            personRepository.save(person).subscribe();

            return this.mapToDTO(person);
        }

    }

    private PersonDTO mapToDTO(Person person) {
        if(person.getId().isEmpty()){
            return PersonDTO.builder()
                    .message("Error!")
                    .status(false)
                    .build();
        }
        return PersonDTO.builder()
                        .score(person.getScore())
                        .id(person.getId())
                        .identityNumber(person.getIdentityNumber())
                        .incomeTranche(person.getIncomeTranche())
                        .nameAndSurname(person.getNameAndSurname())
                        .phoneNumber(person.getPhoneNumber())
                        .residence(person.getResidence())
                        .message("Ok")
                        .status(true)
                        .build();
    }
}
