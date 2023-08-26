package com.doori.hackerthon.entity;

import com.doori.hackerthon.dto.ExamDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class AdminGptEntity {
    @Id
    @GeneratedValue
    private Long id;
    private List<String> keyword;
    private List<String> question;
    private List<String> answer;
    private List<String> retestList;
    private String summary;
}
