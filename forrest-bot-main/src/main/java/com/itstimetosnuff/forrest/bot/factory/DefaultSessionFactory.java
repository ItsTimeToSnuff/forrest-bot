package com.itstimetosnuff.forrest.bot.factory;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.main.BackHandler;
import com.itstimetosnuff.forrest.bot.handler.main.ErrorHandler;
import com.itstimetosnuff.forrest.bot.handler.games.GamesCreateHandler;
import com.itstimetosnuff.forrest.bot.handler.games.GamesHandler;
import com.itstimetosnuff.forrest.bot.handler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.handler.main.StartHandler;
import com.itstimetosnuff.forrest.bot.handler.statistics.StatisticsHandler;
import com.itstimetosnuff.forrest.bot.handler.warehouse.WarehouseHandler;
import com.itstimetosnuff.forrest.bot.session.DefaultSession;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.ArrayList;

@AllArgsConstructor
public class DefaultSessionFactory implements SessionFactory{

    private SessionStore sessionStore;

    @Override
    public Session createSession(Long chatId) {
        HandlerRegistry handlerRegistry = new HandlerRegistry();
        ArrayList<BotApiMethod> executes = new ArrayList<>();
        Session session = new DefaultSession(chatId, EventType.LOCK_FREE, executes, handlerRegistry, sessionStore);
        registerHandlers(handlerRegistry);
        sessionStore.registerSession(session);
        return session;
    }

    private void registerHandlers(HandlerRegistry handlerRegistry) {
        handlerRegistry.register(EventType.START, new StartHandler());
        handlerRegistry.register(EventType.ERROR, new ErrorHandler());
        handlerRegistry.register(EventType.GAMES, new GamesHandler());
        handlerRegistry.register(EventType.GAMES_CREATE, new GamesCreateHandler(0));
        handlerRegistry.register(EventType.WAREHOUSE, new WarehouseHandler());
        handlerRegistry.register(EventType.STATISTICS, new StatisticsHandler());
        handlerRegistry.register(EventType.BACK, new BackHandler());
    }
}
