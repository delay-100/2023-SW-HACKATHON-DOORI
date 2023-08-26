package com.doori.hackerthon.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExamDto {
    private int id;
    private String question;
    private String answer;
    private boolean bool;
    public ExamDto(int id, String question, String answer, boolean bool) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.bool = bool;
    }
}

/*
* 틀린문제 리스트: 3개(문제, 답변, 키워드, 개념)
* 틀린 문제에 대한 개념 설명
*
* * */