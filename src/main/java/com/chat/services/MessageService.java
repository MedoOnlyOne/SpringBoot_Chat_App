package com.chat.services;

import com.chat.exception.NotFound;
import com.chat.exception.UserIsNotInChat;
import com.chat.mapper.MyModelMapper;
import com.chat.models.Chat;
import com.chat.models.Message;
import com.chat.dto.MessageDto;
import com.chat.models.User;
import com.chat.repositories.ChatRepository;
import com.chat.repositories.MessageRepository;
import com.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Autowired
    MyModelMapper modelMapper;
    public MessageDto sendMessage(MessageDto message) {
        Optional<User> user = userRepository.findById(message.getUser());
        Optional<Chat> chat = chatRepository.findById(message.getChat());

        if (!user.isPresent())
            throw new NotFound("User not found");

        if (!chat.isPresent())
            throw new NotFound("Chat not found");

        if(!chat.get().getUsers().contains(user.get())){
            throw new UserIsNotInChat();
        }
        Message createdMessage = new Message();

        createdMessage.setMessageTime(LocalDateTime.now());
        createdMessage.setChat(chat.get());
        createdMessage.setUser(user.get());
        createdMessage.setText(message.getText());

        return this.messageToDto(messageRepository.save(createdMessage));
    }

    public MessageDto getMessage(String messageId) {
        Optional<Message> message = messageRepository.findById(UUID.fromString(messageId));
        if (message.isEmpty())
            throw new NotFound("Message not found");
        return this.messageToDto(message.get());
    }

    public MessageDto messageToDto(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto = modelMapper.mapper.map(message, MessageDto.class);
        return messageDto;
    }
}
