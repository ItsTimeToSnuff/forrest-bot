package com.itstimetosnuff.forrest.bot.factory;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.cashbook.CashbookBalanceHandler;
import com.itstimetosnuff.forrest.bot.handler.cashbook.CashbookCreditDebitHandler;
import com.itstimetosnuff.forrest.bot.handler.cashbook.CashbookHandler;
import com.itstimetosnuff.forrest.bot.handler.games.GamesAfterHandler;
import com.itstimetosnuff.forrest.bot.handler.games.GamesCreateHandler;
import com.itstimetosnuff.forrest.bot.handler.games.GamesHandler;
import com.itstimetosnuff.forrest.bot.handler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.handler.main.BackHandler;
import com.itstimetosnuff.forrest.bot.handler.main.CalendarHandler;
import com.itstimetosnuff.forrest.bot.handler.main.CancelHandler;
import com.itstimetosnuff.forrest.bot.handler.main.ErrorHandler;
import com.itstimetosnuff.forrest.bot.handler.main.StartHandler;
import com.itstimetosnuff.forrest.bot.handler.statistics.PeriodStatisticsHandler;
import com.itstimetosnuff.forrest.bot.handler.statistics.StatisticsHandler;
import com.itstimetosnuff.forrest.bot.handler.warehouse.WarehouseBalanceHandler;
import com.itstimetosnuff.forrest.bot.handler.warehouse.WarehouseCreditHandler;
import com.itstimetosnuff.forrest.bot.handler.warehouse.WarehouseDebitHandler;
import com.itstimetosnuff.forrest.bot.handler.warehouse.WarehouseHandler;
import com.itstimetosnuff.forrest.bot.service.GoogleService;
import com.itstimetosnuff.forrest.bot.session.DefaultSession;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import lombok.AllArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.ArrayList;

@AllArgsConstructor
public class DefaultSessionFactory implements SessionFactory{

    private final SessionStore sessionStore;
    private final GoogleService googleService;

    @Override
    public Session createSession(Long chatId) {
        HandlerRegistry handlerRegistry = new HandlerRegistry();
        ArrayList<BotApiMethod> executes = new ArrayList<>();
        Session session = new DefaultSession(chatId, EventType.LOCK_FREE, executes, handlerRegistry, sessionStore, googleService);
        registerHandlers(handlerRegistry, session);
        sessionStore.registerSession(session);
        return session;
    }

    private void registerHandlers(HandlerRegistry handlerRegistry, Session session) {
        handlerRegistry.register(EventType.START, new StartHandler(session));
        handlerRegistry.register(EventType.ERROR, new ErrorHandler(session));
        handlerRegistry.register(EventType.CANCEL, new CancelHandler(session));
        handlerRegistry.register(EventType.CALENDAR, new CalendarHandler(session));
        handlerRegistry.register(EventType.BACK, new BackHandler(session));
        handlerRegistry.register(EventType.GAMES, new GamesHandler(session));
        handlerRegistry.register(EventType.GAMES_CREATE, new GamesCreateHandler(session));
        handlerRegistry.register(EventType.GAMES_AFTER, new GamesAfterHandler(session));
        handlerRegistry.register(EventType.WAREHOUSE, new WarehouseHandler(session));
        handlerRegistry.register(EventType.WAREHOUSE_BALANCE, new WarehouseBalanceHandler(session));
        handlerRegistry.register(EventType.WAREHOUSE_DEBIT, new WarehouseDebitHandler(session));
        handlerRegistry.register(EventType.WAREHOUSE_CREDIT, new WarehouseCreditHandler(session));
        handlerRegistry.register(EventType.CASHBOOK, new CashbookHandler(session));
        handlerRegistry.register(EventType.CASHBOOK_DEBIT, new CashbookCreditDebitHandler(session));
        handlerRegistry.register(EventType.CASHBOOK_CREDIT, new CashbookCreditDebitHandler(session));
        handlerRegistry.register(EventType.CASHBOOK_BALANCE, new CashbookBalanceHandler(session));
        handlerRegistry.register(EventType.STATISTICS, new StatisticsHandler(session));
        handlerRegistry.register(EventType.STATISTICS_MONTH, new PeriodStatisticsHandler(session));
        handlerRegistry.register(EventType.STATISTICS_YEAR, new PeriodStatisticsHandler(session));
    }
}
