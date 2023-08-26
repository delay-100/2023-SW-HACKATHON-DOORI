package com.doori.hackerthon.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamDto {
    private int id;
    private String question;
    private String answer;

    public ExamDto(int id, String question, String answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }
}
