package com.itstimetosnuff.forrest.bot.request;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ErrorRequest extends Request {

    public ErrorRequest(Integer id, Update update) {
        super(id, EventType.ERROR, update);
    }
}
