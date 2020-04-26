package com.trofimenko.social_networking_service.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.trofimenko.social_networking_service.domain.Message;
import com.trofimenko.social_networking_service.domain.Views;
import com.trofimenko.social_networking_service.exeptions.NotFoundException;
import com.trofimenko.social_networking_service.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> list(){
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message){
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message){
        message.setCreationDate(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @PutMapping("{id}")
    public Message update(
            @PathVariable("id") Message messageFromDb,
            @RequestBody Message message
    ){
        BeanUtils.copyProperties(message,messageFromDb,"id"); //копируем даннные из одного объекта в другой игнорируя поле id
        return messageRepository.save(messageFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message){
       messageRepository.delete(message);
    }
}
