package com.doori.hackerthon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class KeywordDto {
    private int id;
    private String keyword;

    public KeywordDto(String keyword) {
        this.keyword = keyword;
    }

    public KeywordDto() {

    }
}
