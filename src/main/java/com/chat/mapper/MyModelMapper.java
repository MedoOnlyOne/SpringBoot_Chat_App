package com.chat.mapper;

import com.chat.dto.ChatDto;
import com.chat.dto.MessageDto;
import com.chat.models.Chat;
import com.chat.models.Message;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import  org.modelmapper.ModelMapper;


@Component
public class MyModelMapper {
    public final ModelMapper mapper= new ModelMapper();
    public MyModelMapper(){

        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        mapper.typeMap(Message.class, MessageDto.class)
            .addMappings(m -> {
                m.map(src -> src.getUser().getId(), MessageDto::setUser);
                m.map(src -> src.getChat().getId(), MessageDto::setChat);
            });
    }
}
