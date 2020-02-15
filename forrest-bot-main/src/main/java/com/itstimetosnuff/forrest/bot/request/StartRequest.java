package com.itstimetosnuff.forrest.bot.request;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.objects.Update;

@Getter
public class StartRequest extends Request {

    private Update update;

    public StartRequest(Integer id, Update update) {
        super(id, EventType.COMMAND_START);
        this.update = update;
    }
}
