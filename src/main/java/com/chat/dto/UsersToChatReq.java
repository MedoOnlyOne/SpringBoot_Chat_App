package com.chat.dto;

import java.util.UUID;

public class UsersToChatReq {
    UUID[] userIds;

    public UUID[] getUserIds() {
        return userIds;
    }

    public void setUserIds(UUID[] userIds) {
        this.userIds = userIds;
    }
}
