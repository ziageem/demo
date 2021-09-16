package com.example.demo.controller;

import com.example.demo.domain.ChatMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StompChatController {
    private final SimpMessagingTemplate template;

    // StompWebSocketConfig.java에서 설정한 prefix와 @Message 경로가 병합됨.
    // pub/chat/enter
    @MessageMapping("/chat/enter")
    public void enter(ChatMessage message) {
        message.setMessage(message.getWriter() + "님이 채팅방에 참여하였습니다.");
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        // System.out.println("#입장 메시지: " + message);
        // '/sub/chat/room/[roomId]'->채팅방 구분
    }

    @MessageMapping("/chat/message")
    public void message(ChatMessage message) {
        // message.setMessage(message.getMessage());
        template.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
        System.out.println("#들어온 메시지: " + message);
    }
}
