package com.chat.controllers;

import com.chat.dto.ChatDto;
import com.chat.dto.UsersToChatReq;
import com.chat.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatService chatService;

    @PostMapping(path = "/create")
    public ResponseEntity<ChatDto> createChat(@Valid @RequestBody ChatDto chat){
        return new ResponseEntity<>(chatService.createChat(chat), HttpStatus.CREATED);
    }

    @PostMapping(path = "/add-users/{chat-id}")
    public ResponseEntity<ChatDto> addUsers(@Valid @PathVariable("chat-id") String chatId, @RequestBody UsersToChatReq ids){
        return new ResponseEntity<>(chatService.addUsers(chatId, ids.getUserIds()), HttpStatus.OK);
    }
}
