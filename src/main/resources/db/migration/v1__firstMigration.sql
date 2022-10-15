CREATE TABLE Users
(
    user_id uuid,
    username VARCHAR(255),
    password CHAR(64),
    email VARCHAR(400),
    PRIMARY KEY (user_id)
);

CREATE TABLE Messages
(
    message_id uuid,
    message_datetime timestamp,
    message_text TEXT,
    message_chat_id uuid,
    message_user_id uuid,
    PRIMARY KEY (message_id)
);

CREATE TABLE Chats
(
    chat_id uuid,
    chat_topic VARCHAR(32),
    chat_password CHAR(64),
    PRIMARY KEY (chat_id)
);

CREATE TABLE User_Chat
(
    user_chat_chat_id uuid,
    user_chat_user_id uuid,
    PRIMARY KEY (user_chat_user_id, user_chat_chat_id)
);

CREATE INDEX user_login_idx ON Users (username);
ALTER TABLE Messages ADD FOREIGN KEY (message_chat_id) REFERENCES Chats (chat_id);

ALTER TABLE Messages ADD FOREIGN KEY (message_user_id) REFERENCES Users (user_id);

ALTER TABLE User_Chat ADD FOREIGN KEY (user_chat_chat_id) REFERENCES Chats (chat_id);
ALTER TABLE User_Chat ADD FOREIGN KEY (user_chat_user_id) REFERENCES Users (user_id);