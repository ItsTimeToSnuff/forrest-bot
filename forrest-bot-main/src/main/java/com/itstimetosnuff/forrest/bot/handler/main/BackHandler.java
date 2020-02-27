package com.itstimetosnuff.forrest.bot.handler.main;

import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.MethodHelper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BackHandler implements Handler {

    @Override
    public SendMessage handleEvent(Update update, Session session) {
        Long chatId = update.getMessage().getChatId();
        return MethodHelper.backMainMenu(chatId);
    }
}
