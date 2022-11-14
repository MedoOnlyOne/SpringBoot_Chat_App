package com.chat.controllers;

import com.chat.dto.MessageDto;
import com.chat.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class SendMessageController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/group-message") // /app/group-message
    public ResponseEntity<MessageDto> sendGroupMessage(@Payload MessageDto message){
        // /group/{id}/public
        simpMessagingTemplate.convertAndSend("/group" + message.getChat().toString() + "/public",message);
        return new ResponseEntity<>(messageService.sendMessage(message), HttpStatus.CREATED);
    }

    @MessageMapping("/private-message") // /app/private-message
    public ResponseEntity<MessageDto> sendPrivateMessage(@Payload MessageDto message){
        // /user/{id}/private
        simpMessagingTemplate.convertAndSendToUser(message.getUser().toString(),"/private",message);
        System.out.println(message.toString());
        return new ResponseEntity<>(messageService.sendMessage(message), HttpStatus.CREATED);
    }
}