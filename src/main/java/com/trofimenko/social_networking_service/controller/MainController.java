package com.trofimenko.social_networking_service.controller;

import com.trofimenko.social_networking_service.domain.User;
import com.trofimenko.social_networking_service.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final MessageRepository messageRepository;

    @Autowired
    public MainController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile",user);
        data.put("messages",messageRepository.findAll());

        model.addAttribute("frontendData", data); //передаем на фронтэнд данные пользователя если он авторизован
        return "index";
    }
}
