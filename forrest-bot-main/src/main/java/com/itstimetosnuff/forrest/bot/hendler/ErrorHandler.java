package com.itstimetosnuff.forrest.bot.hendler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorHandler implements Handler{

    private String message;

    @Override
    public SendMessage handleEvent(Update update) {
        Long chatId = update.getMessage().getChatId();
        String text = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(String.format("комманда \"%s\" не поддерживается %s", text, message));
        return sendMessage;
    }
}
