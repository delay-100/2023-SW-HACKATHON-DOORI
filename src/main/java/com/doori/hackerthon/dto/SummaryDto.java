package com.doori.hackerthon.dto;

import lombok.Data;

import java.util.List;

@Data
public class SummaryDto {
    private int id;
    private String question;
    private String answer;
    private String keyword;
    private String summary;

    public SummaryDto(int id, String question, String answer, String keyword, String summary) {
        this.id = id;
        this.question = question;
        this.answer = answer;
        this.keyword = keyword;
        this.summary = summary;
    }

    public SummaryDto() {

    }
}
