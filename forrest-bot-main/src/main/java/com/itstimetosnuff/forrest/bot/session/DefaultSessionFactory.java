package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.hendler.ErrorHandler;
import com.itstimetosnuff.forrest.bot.hendler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.hendler.StartHandler;

public class DefaultSessionFactory implements SessionFactory{

    private final transient SessionStore sessionStore;

    public DefaultSessionFactory(SessionStore sessionStore) {
        this.sessionStore = sessionStore;
    }

    @Override
    public Session createSession(Long chatId) {
        HandlerRegistry handlerRegistry = new HandlerRegistry();
        Session session = new DefaultSession(chatId, handlerRegistry);
        registerHandlers(handlerRegistry);
        sessionStore.registerSession(session);
        return session;
    }

    private void registerHandlers(HandlerRegistry handlerRegistry) {
        handlerRegistry.register(EventType.COMMAND_START, new StartHandler());
        handlerRegistry.register(EventType.ERROR, new ErrorHandler());
    }
}
