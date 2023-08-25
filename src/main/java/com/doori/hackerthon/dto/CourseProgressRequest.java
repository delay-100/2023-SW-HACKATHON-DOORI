package com.doori.hackerthon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CourseProgressRequest {
    private Long id;
}
