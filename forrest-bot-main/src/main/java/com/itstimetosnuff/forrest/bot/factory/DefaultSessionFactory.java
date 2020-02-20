package com.itstimetosnuff.forrest.bot.factory;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.hendler.ErrorHandler;
import com.itstimetosnuff.forrest.bot.hendler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.hendler.StartHandler;
import com.itstimetosnuff.forrest.bot.session.DefaultSession;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DefaultSessionFactory implements SessionFactory{

    private SessionStore sessionStore;

    @Override
    public Session createSession(Long chatId) {
        HandlerRegistry handlerRegistry = new HandlerRegistry();
        Session session = new DefaultSession(chatId, handlerRegistry, sessionStore);
        registerHandlers(handlerRegistry);
        sessionStore.registerSession(session);
        return session;
    }

    private void registerHandlers(HandlerRegistry handlerRegistry) {
        handlerRegistry.register(EventType.COMMAND_START, new StartHandler());
        handlerRegistry.register(EventType.ERROR, new ErrorHandler());
    }
}
