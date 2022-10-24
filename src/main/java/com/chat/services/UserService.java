package com.chat.services;

import com.chat.dto.ChatDto;
import com.chat.dto.UserDto;
import com.chat.exception.NotFound;
import com.chat.mapper.MyModelMapper;
import com.chat.models.User;
import com.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    ChatService chatService;

    @Autowired
    private MyModelMapper modelMapper;

    public UserDto createUser(UserDto user) {
        return this.userToDto(userRepository.save(this.dtoToUser(user)));
    }

    public UserDto updateUser(String id, UserDto user)  {
        UUID userId = UUID.fromString(id);
        Optional<User> fetchedUserOptional = userRepository.findById(userId);

        if(fetchedUserOptional.isEmpty()){
            throw new NotFound("User not found");
        }

        User fetchedUser = fetchedUserOptional.get();
        fetchedUser.setUsername(user.getUsername());
        fetchedUser.setPassword(user.getPassword());
        fetchedUser.setEmail(user.getEmail());

        return userToDto(userRepository.save(fetchedUser));
    }

    public List<UserDto> getUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();

        users.forEach((user) -> {
            userDtos.add(this.userToDto(user));
        });
        return userDtos;
    }

    public UserDto getUser(String userId) {
        Optional<User> user = userRepository.findById(UUID.fromString(userId));
        if (user.isEmpty())
            throw new NotFound("User not found");
        return this.userToDto(user.get());
    }

    public List<ChatDto> getUserChats(String userId) {
        Optional<User> user = userRepository.findById(UUID.fromString(userId));
        if (user.isEmpty())
            throw new NotFound("User not found");
        List<ChatDto> userChats = new ArrayList<>();

        user.get().getUserChats().forEach((chat) -> {
            userChats.add(chatService.chatToDto(chat));
        });
        return userChats;
    }
    public UserDto userToDto (User user){
        UserDto userDto = new UserDto();
        userDto = modelMapper.mapper.map(user, UserDto.class);
        return userDto;
    }

    public User dtoToUser(UserDto userDto){
        User user = new User();
        user = modelMapper.mapper.map(userDto, User.class);
        return user;
    }
}
