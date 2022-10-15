package com.chat.services;

import com.chat.models.User;
import com.chat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.InstanceNotFoundException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        if((user.getUsername().equals("") || user.getUsername() == null)
           || (user.getEmail().equals("") || user.getEmail() == null)
           || (user.getPassword().equals("") || user.getPassword() == null)){
            throw new IllegalStateException("Username, password, and email are required");
        }

        return userRepository.save(user);
    }

    public User updateUser(String id, User user)  {
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

        return userRepository.save(fetchedUser);
    }
}
