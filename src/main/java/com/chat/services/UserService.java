package com.chat.services;

import com.chat.dto.UserDto;
import com.chat.mapper.MyModelMapper;
import com.chat.models.User;
import com.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MyModelMapper modelMapper;

    public UserDto createUser(UserDto user) {
        if((user.getUsername().equals("") || user.getUsername() == null)
           || (user.getEmail().equals("") || user.getEmail() == null)
           || (user.getPassword().equals("") || user.getPassword() == null)){
            throw new IllegalStateException("Username, password, and email are required");
        }

        return this.userToDto(userRepository.save(this.dtoToUser(user)));
    }

    public UserDto updateUser(String id, UserDto user)  {
        UUID userId = UUID.fromString(id);
        Optional<User> fetchedUserOptional = userRepository.findById(userId);

        if(fetchedUserOptional.isEmpty()){
            throw new IllegalStateException("No such user");
        }

        User fetchedUser = fetchedUserOptional.get();

        if (!user.getUsername().equals("") || user.getUsername() != null)
            fetchedUser.setUsername(user.getUsername());
        if (!user.getPassword().equals("") || user.getPassword() != null)
            fetchedUser.setPassword(user.getPassword());
        if (!user.getEmail().equals("") || user.getEmail() != null)
            fetchedUser.setEmail(user.getEmail());

        return userToDto(userRepository.save(fetchedUser));
    }

    private UserDto userToDto (User user){
        UserDto userDto = new UserDto();
        userDto = modelMapper.mapper.map(user, UserDto.class);
        return userDto;
    }

    private User dtoToUser(UserDto userDto){
        User user = new User();
        user = modelMapper.mapper.map(userDto, User.class);
        return user;
    }
}
