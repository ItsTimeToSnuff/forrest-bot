package com.itstimetosnuff.forrest.bot.hendler;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.request.Request;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
public class HandlerRegistry {

    private static Map<EventType,
                Handler<? extends Request, ? extends BotApiMethod>> handlers = new EnumMap<>(EventType.class);

    public Handler<Request, BotApiMethod> getHandler(EventType eventType){
        return (Handler<Request, BotApiMethod>) handlers.get(eventType);
    }

    public void register(EventType type, Handler<? extends Request, ? extends BotApiMethod> handler) {
        log.error("before length->"+handler.toString());
        handlers.put(type, handler);
        log.error("after length->"+handlers.toString() + handlers.getClass().getCanonicalName());
    }
}
