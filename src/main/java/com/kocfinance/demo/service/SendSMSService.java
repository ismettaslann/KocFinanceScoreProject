package com.kocfinance.demo.service;

import com.kocfinance.demo.dto.PersonDTO;
import com.kocfinance.demo.dto.SendSMSDTO;
import com.kocfinance.demo.entity.SendSMS;
import com.kocfinance.demo.repository.SendSMSRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/send-sms")
@CrossOrigin("*")
@Service
public class SendSMSService {

    @Autowired
    SendSMSRepository sendSMSRepository;

    @PostMapping("/new-sms")
    public SendSMSDTO insertSendSMS(@RequestBody PersonDTO person){
        if(person == null){
            this.mapToDTO(SendSMS.builder().build());
        }
        SendSMS sendSMS = SendSMS.builder()
                                 .id(person.getId())
                                 .phoneNumber(person.getPhoneNumber())
                                 .smsContent("Hesaplanan skor puanınız : " + person.getScore()+ ". İyi günler.")
                                 .status(true)
                                 .build();

        sendSMSRepository.save(sendSMS).subscribe();

        return this.mapToDTO(sendSMS);

    }

    private SendSMSDTO mapToDTO(SendSMS sendSMS) {
        if(sendSMS.getId().isEmpty()){
            return SendSMSDTO.builder()
                             .sent(false)
                             .message("Hata!")
                             .build();
        }
        return SendSMSDTO.builder()
                         .sent(sendSMS.isStatus())
                         .phoneNumber(sendSMS.getPhoneNumber())
                         .smsContent(sendSMS.getSmsContent())
                         .message("Ok!")
                         .build();
    }
}
