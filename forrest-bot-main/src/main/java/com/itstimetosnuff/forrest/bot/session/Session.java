package com.itstimetosnuff.forrest.bot.session;

import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Session {

    Long getChatId();



    BotApiMethod onCommand(Update update);

    void onClose();

    void close();
}
