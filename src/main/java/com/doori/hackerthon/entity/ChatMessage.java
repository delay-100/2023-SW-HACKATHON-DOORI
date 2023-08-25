package com.doori.hackerthon.entity;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "messages")
@Data
public class ChatMessage {
    @Id
    private String id;
    private String userId;
    private String user;
    private String ai;

}
