package com.chat.dto;

import com.chat.models.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserLoginDto {
    UUID id;
    @NotBlank(message = "Username is required")
    String username;
    @NotBlank(message = "password is required")
    String password;
    @Email(message = "Email is not valid")
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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
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
