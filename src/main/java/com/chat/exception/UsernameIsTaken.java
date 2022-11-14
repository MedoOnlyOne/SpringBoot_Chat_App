package com.chat.exception;

public class UsernameIsTaken extends RuntimeException{
    public UsernameIsTaken() {
        super("Username is taken");
    }
}
