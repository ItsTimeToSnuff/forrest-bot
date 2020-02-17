package com.itstimetosnuff.forrest.bot.hendler;

import com.itstimetosnuff.forrest.bot.request.ErrorRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ErrorHandler implements Handler<ErrorRequest, SendMessage>{

    private String message;

    @Override
    public SendMessage handleEvent(ErrorRequest request) {
        Long chatId = request.getUpdate().getMessage().getChatId();
        String text = request.getUpdate().getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(String.format("комманда \"%s\" не поддерживается %s", text, message));
        return sendMessage;
    }
}
