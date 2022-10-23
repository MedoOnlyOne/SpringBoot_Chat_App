package com.chat.services;

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
        if ((message.getText().equals("") || message.getText() == null)
            || (message.getChat().equals("") || message.getChat() == null)
            || (message.getUser().equals("") || message.getUser() == null)) {
            throw new IllegalStateException("Message text, time, sending user, destination chat are required");
        }
        Optional<User> user = userRepository.findById(message.getUser());
        if (user.isEmpty())
            throw new IllegalStateException("User is not found");

        Optional<Chat> chat = chatRepository.findById(message.getChat());
        if (chat.isEmpty())
            throw new IllegalStateException("Chat is not found");
        if(!chat.get().getUsers().contains(user.get())){
            throw new IllegalStateException("This user does not join this chat.");
        }
        Message createdMessage = new Message();

        createdMessage.setMessageTime(LocalDateTime.now());
        createdMessage.setChat(chat.get());
        createdMessage.setUser(user.get());
        createdMessage.setText(message.getText());

        return this.messageToDto(messageRepository.save(createdMessage));
    }

    private MessageDto messageToDto(Message message){
        MessageDto messageDto = new MessageDto();
        messageDto = modelMapper.mapper.map(message, MessageDto.class);
        return messageDto;
    }
}
