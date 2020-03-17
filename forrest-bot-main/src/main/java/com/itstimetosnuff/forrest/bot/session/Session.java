package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.service.GoogleService;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Session {

    protected Long chatId;

    protected EventType eventLock;

    protected List<BotApiMethod> executes;

    protected HandlerRegistry handlerRegistry;

    protected SessionStore sessionStore;

    protected GoogleService googleService;


    public Long getChatId() {
        return this.chatId;
    }

    public EventType getEventLock() {
        return this.eventLock;
    }

    public List<BotApiMethod> getExecutes() {
        return executes;
    }

    public GoogleService getGoogleService() {
        return this.googleService;
    }

    public void setEventLock(EventType eventType) {
        eventLock = eventType;
    }

    public abstract BotApiMethod onUpdate(Update update);

    public abstract void close();
}
