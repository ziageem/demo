package com.example.demo.domain;

import lombok.Data;

@Data
public class ChatMessage {
    private String roomId;
    private String writer;
    private String message;
}
