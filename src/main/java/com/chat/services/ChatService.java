package com.chat.services;

import com.chat.dto.ChatDto;
import com.chat.dto.MessageDto;
import com.chat.exception.NotFound;
import com.chat.mapper.MyModelMapper;
import com.chat.models.Chat;
import com.chat.models.Message;
import com.chat.models.User;
import com.chat.repositories.ChatRepository;
import com.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyModelMapper modelMapper;

    @Autowired
    private MessageService messageService;

    public ChatDto createChat(ChatDto chat) {
        return this.chatToDto(chatRepository.save(this.dtoToChat(chat)));
    }

    public ChatDto addUsers(String id, UUID[] userIds) {
        UUID chatId = UUID.fromString(id);

        Optional<Chat> requiredChat = chatRepository.findById(chatId);
        if (requiredChat.isEmpty()){
            throw new NotFound("Chat not found");
        }

        Chat chat = requiredChat.get();
        System.out.println(chat.getChatMessages());

        for (UUID userId: userIds){
            Optional<User> user = userRepository.findById(userId);

            // If no such user, or user is already in the chat
            // got to the next user to add
            if (user.isEmpty())
                continue;
            if (chat.getUsers().contains(user.get()))
                continue;
            chat.getUsers().add(user.get());
        }
        return this.chatToDto(chatRepository.save(chat));
    }

    public ChatDto getChat(String chatId) {
        Optional<Chat> chat = chatRepository.findById(UUID.fromString(chatId));
        if (chat.isEmpty())
            throw new NotFound("Chat not found");
        return this.chatToDto(chat.get());
    }

    public List<MessageDto> getChatMessages(String chatId) {
        Optional<Chat> chat = chatRepository.findById(UUID.fromString(chatId));
        if (chat.isEmpty())
            throw new NotFound("Chat not found");

        List<MessageDto> messageDtos= new ArrayList<>();

        chat.get().getChatMessages().forEach((message) -> {
            messageDtos.add(messageService.messageToDto(message));
        });
        return messageDtos;
    }

    public ChatDto chatToDto(Chat chat){
        ChatDto chatDto = new ChatDto();
        chatDto = modelMapper.mapper.map(chat, ChatDto.class);
        return chatDto;
    }

    public Chat dtoToChat(ChatDto chatDto){
        Chat chat = new Chat();
        chat = modelMapper.mapper.map(chatDto, Chat.class);
        return chat;
    }
}
