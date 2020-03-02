package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public interface Session {

    Long getChatId();

    void setEventLock(EventType eventType);

    List<BotApiMethod> getExecutes();

    BotApiMethod onUpdate(Update update);

    void close();
}
