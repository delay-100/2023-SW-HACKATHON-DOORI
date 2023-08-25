package com.doori.hackerthon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Chat {
    private String userId;
    private String role;
    private String message;

}
