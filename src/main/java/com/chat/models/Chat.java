package com.chat.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "Chats")
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "chat_id")
    UUID id;

    @Column(name = "chat_topic", length = 32)
    String topic;

    @Column(name = "chat_password", length = 64)
    String password;

    @ManyToMany
    @JoinTable(name = "User_Chat",
            joinColumns = {@JoinColumn(name = "user_chat_chat_id", referencedColumnName = "chat_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_chat_user_id", referencedColumnName = "user_id")}
    )
    List<User> users = new ArrayList<>();

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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", password='" + password + '\'' +
                ", users=" + users +
                '}';
    }
}
