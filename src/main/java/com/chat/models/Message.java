package com.chat.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "message_id")
    UUID id;

    @Column(name = "message_datetime", columnDefinition = "TIMESTAMP")
    LocalDateTime messageTime;

    @Column(name = "message_text", columnDefinition = "TEXT")
    String text;

    @ManyToOne
    @JoinColumn(name = "message_user_id", referencedColumnName = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "message_chat_id", referencedColumnName = "chat_id")
    Chat chat;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", messageTime=" + messageTime +
                ", text='" + text + '\'' +
                ", user=" + user +
                ", chat=" + chat +
                '}';
    }
}
