package com.chat.services;

import com.chat.models.Chat;
import com.chat.models.User;
import com.chat.repositories.ChatRepository;
import com.chat.repositories.UserRepository;
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

    public Chat createChat(Chat chat) {
        if ((chat.getTopic().equals("") || chat.getTopic() == null)
            || (chat.getPassword().equals("") || chat.getPassword() == null)) {
            throw new IllegalStateException("Topic and password are required");
        }
        return chatRepository.save(chat);
    }

    public Chat addUsers(String id, String[] userIds) {
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
        return chatRepository.save(chat);
    }
}
