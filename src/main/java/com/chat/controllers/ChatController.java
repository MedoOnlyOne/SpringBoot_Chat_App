package com.chat.controllers;

import com.chat.models.Chat;
import com.chat.models.UsersToChatReq;
import com.chat.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping(path = "/create")
    public ResponseEntity<Chat> createChat(@RequestBody Chat chat){
        return new ResponseEntity<Chat>(chatService.createChat(chat), HttpStatus.CREATED);
    }

    @PostMapping(path = "/add-users/{chat-id}")
    public ResponseEntity<Chat> addUsers(@PathVariable("chat-id") String chatId, @RequestBody UsersToChatReq ids){
        return new ResponseEntity<>(chatService.addUsers(chatId, ids.getUserIds()), HttpStatus.OK);
    }
}
