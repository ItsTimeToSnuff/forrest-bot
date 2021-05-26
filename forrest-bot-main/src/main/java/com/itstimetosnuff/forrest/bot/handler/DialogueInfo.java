package com.itstimetosnuff.forrest.bot.handler;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
public class DialogueInfo {

    private final AtomicInteger position;

    private final List<Integer> msgToDelete;

    private EventType eventLock;

    public DialogueInfo() {
        position = new AtomicInteger();
        msgToDelete = new ArrayList<>();
        eventLock = EventType.LOCK_FREE;
    }

    public void setEventLock(EventType event) {
        eventLock = event;
    }

}
