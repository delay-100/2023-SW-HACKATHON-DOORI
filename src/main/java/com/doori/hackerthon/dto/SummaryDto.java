package com.doori.hackerthon.dto;

import lombok.Data;

import java.util.List;

@Data
public class SummaryDto {
    private int id;
    private List<String> question;
    private List<String> answer;
    private List<String> keyword;
    private List<String> summary;

    public SummaryDto(int id, List<String> question, List<String> answer, List<String> keyword, List<String> summary) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.keyword = keyword;
        this.summary = summary;
    }
}

/*
 * 틀린문제 리스트: 3개(문제, 답변, 키워드, 개념)
 * 틀린 문제에 대한 개념 설명
 *
 * * */