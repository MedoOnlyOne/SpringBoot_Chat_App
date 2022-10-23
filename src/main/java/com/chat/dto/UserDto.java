package com.chat.dto;

import com.chat.models.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDto {
    @JsonIgnore
    UUID id;
    String username;
    @JsonIgnore
    String password;
    String email;
    @JsonIgnore
    List<MessageDto> userMessages = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<MessageDto> getUserMessages() {
        return userMessages;
    }

    public void setUserMessages(List<MessageDto> userMessages) {
        this.userMessages = userMessages;
    }
}