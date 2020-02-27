package com.itstimetosnuff.forrest.bot.handler;

import com.itstimetosnuff.forrest.bot.session.Session;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Handler {

    BotApiMethod handleEvent (Update update, Session session);
}
