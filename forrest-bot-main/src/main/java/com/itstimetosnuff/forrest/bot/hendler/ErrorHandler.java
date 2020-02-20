package com.itstimetosnuff.forrest.bot.hendler;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ErrorHandler implements Handler{

    @Override
    public SendMessage handleEvent(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(String.format("комманда \"%s\" не поддерживается", text));
        return sendMessage;
    }
}
