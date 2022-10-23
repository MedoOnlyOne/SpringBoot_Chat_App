package com.chat.dto;

import com.chat.models.Message;
import com.chat.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChatDto {
    @JsonIgnore
    UUID id;
    @NotBlank(message = "Message's topic is required")
    String topic;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Message's password is required")
    String password;
    List<MessageDto> chatMessages = new ArrayList<>();
    List<UserDto> users = new ArrayList<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MessageDto> getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(List<MessageDto> chatMessages) {
        this.chatMessages = chatMessages;
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
