package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.example.demo.domain.ChatRoom;

import org.springframework.stereotype.Repository;

// 실제 서비스 시 DB를 사용해 저장해야 함
@Repository
public class ChatRoomRepository {
    private Map<String, ChatRoom> chatRoomMap;

    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRooms() {
        List<ChatRoom> result = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(result);

        return result;
    }

    public ChatRoom findRoomById(String id) {
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name) {
        ChatRoom room = ChatRoom.create(name);
        chatRoomMap.put(room.getRoomId(), room);

        return room;
    }
}
