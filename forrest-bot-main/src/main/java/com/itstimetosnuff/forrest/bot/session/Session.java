package com.itstimetosnuff.forrest.bot.session;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Session {

    Long getChatId();

    BotApiMethod onUpdate(Update update);

    void close();
}
