package com.kocfinance.demo.repository;

import com.kocfinance.demo.entity.Person;
import com.kocfinance.demo.entity.SendSMS;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface SendSMSRepository extends ReactiveMongoRepository<SendSMS, String> {
}
