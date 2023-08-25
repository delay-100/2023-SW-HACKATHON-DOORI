package com.doori.hackerthon.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class CourseProgress {

    @Id
    @GeneratedValue
    private Long id;
    private Long studentId;
    private String college;
    private String department;
    private Integer subjectId;
    private BigDecimal progress;

}
