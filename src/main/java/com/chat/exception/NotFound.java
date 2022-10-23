package com.chat.exception;

public class NotFound extends RuntimeException{
    public NotFound(String text){
        super(text);
    }
}
