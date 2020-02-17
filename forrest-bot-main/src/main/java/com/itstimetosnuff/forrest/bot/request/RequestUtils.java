package com.itstimetosnuff.forrest.bot.request;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RequestUtils {

    static public Request create (Update update) {
        Message message = update.getMessage();
        switch (EventType.byCommand(message.getText())) {
            case COMMAND_START: return new StartRequest(message.getMessageId(), update);
        }
        return new ErrorRequest(message.getMessageId(), update);
    }
}
