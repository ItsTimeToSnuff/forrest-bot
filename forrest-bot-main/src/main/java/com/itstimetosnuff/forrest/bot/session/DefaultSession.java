package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.handler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@AllArgsConstructor
public class DefaultSession implements Session {

    private Long chatId;

    private EventType eventLock;

    private List<BotApiMethod> executes;

    private HandlerRegistry handlerRegistry;

    private SessionStore sessionStore;

    @Override
    public Long getChatId() {
        return this.chatId;
    }

    @Override
    public void setEventLock(EventType eventType) {
        this.eventLock = eventType;
    }

    @Override
    public List<BotApiMethod> getExecutes() {
        return this.executes;
    }

    @Override
    public void setExecutes(List<BotApiMethod> executes) {
        this.executes = executes;
    }

    @Override
    public BotApiMethod onUpdate(Update update) {
        Handler handler;
        if (!eventLock.equals(EventType.LOCK_FREE)){
             handler = handlerRegistry.getHandler(EventType.byType(eventLock.getValue()));
        }else {
            String text = update.getMessage().getText();
            handler = handlerRegistry.getHandler(EventType.byType(text));
        }
        if (handler != null) {
            log.info(handler.toString());
            return handler.handleEvent(update, this);
        }
        return null;
    }

    @Override
    public void close() {
        sessionStore.closeSession(this);
    }
}
