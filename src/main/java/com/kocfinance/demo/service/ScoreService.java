package com.kocfinance.demo.service;

import com.kocfinance.demo.entity.IncomeTrancheEnum;
import com.kocfinance.demo.entity.Person;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
public class ScoreService {

    /**
     * This method returns sum of digits in identity number over and over.
     * Until total is between 1-9
     *
     * @param identityNumber
     * @return Long
     */
    public Long getScoreFromPersonIdNumber(String identityNumber){
        Long idNumber = Long.valueOf(identityNumber);
        return idNumber % 9 == 0 ? 9 : idNumber % 9;
    }

    /**
     * This method returns random number between 0 - 20000
     *
     * @param
     * @return
     */
    public int getScoreFromResidenceNumber(){
        Random random = new Random();
        return random.ints(0,20000).findFirst().getAsInt();
    }

    /**
     * This method returns multiplier for income trench.
     * @param code
     * @return
     */
    public int getMultiplierFromIncomeTrench(String code){
        IncomeTrancheEnum incomeTrancheEnum = IncomeTrancheEnum.CODE_1;
        return incomeTrancheEnum.getIncomeValueByCode(code);
    }

    /**
     * This method calculates total score
     * @param person
     * @return
     */
    public int calculateScore(Person person){
        Long identityScore = getScoreFromPersonIdNumber(person.getIdentityNumber());
        int residenceScore = getScoreFromResidenceNumber();
        int incomeTrencMuliplier = getMultiplierFromIncomeTrench(person.getIncomeTranche());

        //toplam skor = skor segmenti * gelir dilim çarpanı + şehir skoru
        return (int) ((identityScore*incomeTrencMuliplier) + residenceScore);
    }
}
