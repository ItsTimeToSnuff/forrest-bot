package com.itstimetosnuff.forrest.bot.handler;

import com.itstimetosnuff.forrest.bot.enums.EventType;

import java.util.EnumMap;
import java.util.Map;

public class HandlerRegistry {

    private transient Map<EventType, Handler> handlers;

    public HandlerRegistry() {
        this.handlers = new EnumMap<>(EventType.class);
    }

    public Handler getHandler(EventType eventType){
        return handlers.get(eventType);
    }

    public void register(EventType type, Handler handler) {
        handlers.put(type, handler);
    }
}
