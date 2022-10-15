package com.chat.services;

import com.chat.models.Chat;
import com.chat.models.Message;
import com.chat.models.MessageSendReq;
import com.chat.models.User;
import com.chat.repositories.ChatRepository;
import com.chat.repositories.MessageRepository;
import com.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChatRepository chatRepository;

    public Message sendMessage(MessageSendReq message) {
        if ((message.getMessageTime().equals("") || message.getMessageTime() == null)
            || (message.getText().equals("") || message.getText() == null)
            || (message.getChat().equals("") || message.getChat() == null)
            || (message.getUser().equals("") || message.getUser() == null)) {
            throw new IllegalStateException("Message text, time, sending user, destination chat are required");
        }
        Optional<User> user = userRepository.findById(UUID.fromString(message.getUser()));
        if (user.isEmpty())
            throw new IllegalStateException("User is not found");

        Optional<Chat> chat = chatRepository.findById(UUID.fromString(message.getChat()));
        if (chat.isEmpty())
            throw new IllegalStateException("Chat is not found");

        Message createdMessage = new Message();
//        createdMessage.setMessageTime(LocalDateTime.parse(
//                message.getMessageTime().toString(),
//                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//        ));
        createdMessage.setMessageTime(message.getMessageTime());


        createdMessage.setChat(chat.get());
        createdMessage.setUser(user.get());
        createdMessage.setText(message.getText());

        return messageRepository.save(createdMessage);
    }
}