package com.itstimetosnuff.forrest.bot.hendler;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import lombok.extern.slf4j.Slf4j;

import java.util.EnumMap;
import java.util.Map;

@Slf4j
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
