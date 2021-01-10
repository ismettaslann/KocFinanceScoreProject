package com.kocfinance.demo.dto;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SendSMSDTO {
    private String phoneNumber;
    private String smsContent;
    private boolean sent;
    private String message;
}
