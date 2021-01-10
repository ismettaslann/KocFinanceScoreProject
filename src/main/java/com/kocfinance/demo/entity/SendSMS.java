package com.kocfinance.demo.entity;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "sending_sms")
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class SendSMS {
    @Id
    private String id;
    private String phoneNumber;
    private String smsContent;
    private boolean status;
}
