package com.chat.exception;

public class UserIsNotInChat extends RuntimeException{
    public UserIsNotInChat(){
        super("This user does not join this chat.");
    }
}
