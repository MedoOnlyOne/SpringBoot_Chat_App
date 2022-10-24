package com.chat.controllers;

import com.chat.dto.MessageDto;
import com.chat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public ResponseEntity<MessageDto> sendMessage(@Valid @RequestBody MessageDto message){
        return new ResponseEntity<>(messageService.sendMessage(message), HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<MessageDto> getMessage (@PathVariable("id") String messageId){
        return new ResponseEntity<>(messageService.getMessage(messageId), HttpStatus.OK);
    }
}
