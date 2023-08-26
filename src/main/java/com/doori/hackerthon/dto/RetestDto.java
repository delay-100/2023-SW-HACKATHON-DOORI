package com.doori.hackerthon.dto;

import lombok.Data;

@Data
public class RetestDto {
    private int id;
    private String question;
    private String answer;

    public RetestDto(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

}