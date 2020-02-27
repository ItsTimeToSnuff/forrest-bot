package com.itstimetosnuff.forrest.bot.handler.main;

import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.MethodHelper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ErrorHandler implements Handler {

    @Override
    public SendMessage handleEvent(Update update, Session session) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        return MethodHelper.sendMessage(
                chatId,
                String.format("комманда \"%s\" не поддерживается", text),
                null
        );
    }
}
