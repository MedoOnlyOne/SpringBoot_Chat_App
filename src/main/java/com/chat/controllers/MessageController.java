package com.chat.controllers;

import com.chat.models.Message;
import com.chat.models.MessageSendReq;
import com.chat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping(path = "/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageSendReq message){
        return new ResponseEntity<>(messageService.sendMessage(message), HttpStatus.CREATED);
    }
}
