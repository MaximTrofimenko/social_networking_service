package com.trofimenko.social_networking_service.controller;

import com.trofimenko.social_networking_service.exeptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int count = 4;

    private List<Map<String,String>> messages = new ArrayList<Map<String,String>>(){{
        add(new HashMap<String, String>(){{put("id","1");put("text","First message");}});
        add(new HashMap<String, String>(){{put("id","2");put("text","Second message");}});
        add(new HashMap<String, String>(){{put("id","3");put("text","Third message");}});
    }};

    @GetMapping
    public List<Map<String,String>> list(){
        return messages;
    }

    @GetMapping("{id}")
    public Map<String,String> getOne(@PathVariable String id){
        return getMessage(id);
    }

    private Map<String, String> getMessage(@PathVariable String id) {
        return messages.stream()
                .filter(messages -> messages.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String,String> create(@RequestBody Map<String,String> message){
        message.put("id",String.valueOf(count));
        messages.add(message);
        return message;

    }

    @PutMapping("{id}")
    public Map<String,String> update(@PathVariable String id, @RequestBody Map<String,String> message){
        Map<String, String> messageFromDb = getMessage(message.get("id")); //получаем сообщение из базы

        messageFromDb.putAll(message);//объединяем две мапы в одну
        messageFromDb.put("id",id);   //устанавливаем тот id по которому был запрос

        return messageFromDb;
    }


}
