package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.hendler.Handler;
import com.itstimetosnuff.forrest.bot.hendler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@AllArgsConstructor
public class DefaultSession implements Session {

    private Long chatId;

    private HandlerRegistry handlerRegistry;

    private SessionStore sessionStore;

    @Override
    public Long getChatId() {
        return this.chatId;
    }

    @Override
    public BotApiMethod onUpdate(Update update) {
        String text = update.getMessage().getText();
        Handler handler = handlerRegistry.getHandler(EventType.byCommand(text));
        if (handler != null){
            log.info(handler.toString());
            return handler.handleEvent(update);
        }
        return null;
    }

    @Override
    public void close() {
        sessionStore.closeSession(this);
    }
}
