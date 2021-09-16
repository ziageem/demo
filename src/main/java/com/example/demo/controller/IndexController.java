package com.example.demo.controller;

import com.example.demo.service.ChatRoomRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final ChatRoomRepository repository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/chatlist")
    public ModelAndView rooms() {
        System.out.println("#채팅방 목록 조회");
        ModelAndView mv = new ModelAndView("chat/rooms");
        mv.addObject("list", repository.findAllRooms());
        return mv;
    }

    @PostMapping("/chat")
    public String create(String name, RedirectAttributes rttr) {
        System.out.println("#채팅방 개설 방: " + name);
        rttr.addFlashAttribute("roomName", repository.createChatRoom(name));
        return "redirect:/chatlist";
    }

    @GetMapping("/chat")
    public void getRoom(String roomId, Model model) {
        System.out.println("#채팅방 입장: " + roomId);
        System.out.println("#정보: " + repository.findRoomById(roomId));
        model.addAttribute("room", repository.findRoomById(roomId));
    }
    // public String getRoom(String roomId, Model model) {
    // System.out.println("#채팅방 입장: " + roomId);
    // System.out.println("#정보: " + repository.findRoomById(roomId));
    // model.addAttribute("room", repository.findRoomById(roomId));
    // return "chat/room";
    // }
}
