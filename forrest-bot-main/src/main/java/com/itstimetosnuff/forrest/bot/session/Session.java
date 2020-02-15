package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.request.Request;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface Session {

    Long getChatId();

    BotApiMethod onMessage(Request request);

    void onClose();

    void close();
}
