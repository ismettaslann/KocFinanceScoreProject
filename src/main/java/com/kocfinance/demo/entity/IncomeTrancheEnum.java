package com.kocfinance.demo.entity;

import lombok.Getter;

@Getter
public enum IncomeTrancheEnum {

    CODE_1 ("1", 800),
    CODE_2 ("2", 1000),
    CODE_3 ("3", 1200),
    CODE_4 ("4", 1500),
    CODE_5 ("5", 2000);

    private String code;
    private int value;

    IncomeTrancheEnum(String code, int value){
        this.code = code;
        this.value = value;
    }

    public int getIncomeValueByCode(String code){
        for (IncomeTrancheEnum s : values()){
            if(s.getCode().equals(code)){
                return s.getValue();
            }
        }
        //return  minimum value
        return 800;
    }

}
