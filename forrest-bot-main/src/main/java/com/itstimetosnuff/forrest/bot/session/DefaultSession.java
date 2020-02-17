package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.hendler.Handler;
import com.itstimetosnuff.forrest.bot.hendler.HandlerRegistry;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@AllArgsConstructor
public class DefaultSession implements Session {

    private Long chatId;

    private HandlerRegistry handlerRegistry;

    @Override
    public Long getChatId() {
        return this.chatId;
    }

    @Override
    public BotApiMethod onCommand(Update update) {
        String text = update.getMessage().getText();
        Handler handler = handlerRegistry.getHandler(EventType.byCommand(text));
        if (handler != null){
            return handler.handleEvent(update);
        }
        return null;
    }

    @Override
    public void onClose() {

    }

    @Override
    public void close() {

    }
}
