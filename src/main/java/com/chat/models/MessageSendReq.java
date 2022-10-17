package com.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageSendReq {
    private String user;
    private String chat;
    private String text;
//    private LocalDateTime messageTime;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
