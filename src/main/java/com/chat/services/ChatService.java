package com.chat.services;

import com.chat.dto.ChatDto;
import com.chat.models.Chat;
import com.chat.models.User;
import com.chat.repositories.ChatRepository;
import com.chat.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChatService {
    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ChatDto createChat(ChatDto chat) {
        if ((chat.getTopic().equals("") || chat.getTopic() == null)
            || (chat.getPassword().equals("") || chat.getPassword() == null)) {
            throw new IllegalStateException("Topic and password are required");
        }
        return this.chatToDto(chatRepository.save(this.dtoToChat(chat)));
    }

    public ChatDto addUsers(String id, String[] userIds) {
        UUID chatId = UUID.fromString(id);

        Optional<Chat> requiredChat = chatRepository.findById(chatId);
        if (requiredChat.isEmpty()){
            throw new IllegalStateException("Chat not found");
        }

        Chat chat = requiredChat.get();

        for (String userId: userIds){
            Optional<User> user = userRepository.findById(UUID.fromString(userId));

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

    private ChatDto chatToDto(Chat chat){
        ChatDto chatDto = new ChatDto();
        chatDto = modelMapper.map(chat, ChatDto.class);
        return chatDto;
    }

    private Chat dtoToChat(ChatDto chatDto){
        Chat chat = new Chat();
        chat = modelMapper.map(chatDto, Chat.class);
        return chat;
    }
}
