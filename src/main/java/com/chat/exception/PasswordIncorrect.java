package com.chat.exception;

public class PasswordIncorrect extends RuntimeException{
    public PasswordIncorrect(){
        super("Password Incorrect");
    }
}