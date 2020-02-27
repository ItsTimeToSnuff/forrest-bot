package com.itstimetosnuff.forrest.bot.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTypeTest {

    @Test
    void whenEventTypeGetCommandThenReturnIt() {
        assertEquals(EventType.START.getValue(), "/start");
    }

    @Test
    void whenEventTypeByCommandThenReturnIt() {
        assertEquals(EventType.byType("/start"), EventType.START);
    }

    @Test
    void whenEventTypeByCommandThenReturnError() {
        assertEquals(EventType.byType("/test"), EventType.ERROR);
    }
}
