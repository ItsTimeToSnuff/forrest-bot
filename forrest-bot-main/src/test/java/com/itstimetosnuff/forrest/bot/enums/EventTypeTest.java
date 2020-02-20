package com.itstimetosnuff.forrest.bot.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTypeTest {

    @Test
    void whenEventTypeGetCommandThenReturnIt() {
        assertEquals(EventType.COMMAND_START.getCommand(), "/start");
    }

    @Test
    void whenEventTypeByCommandThenReturnIt() {
        assertEquals(EventType.byCommand("/start"), EventType.COMMAND_START);
    }

    @Test
    void whenEventTypeByCommandThenReturnError() {
        assertEquals(EventType.byCommand("/test"), EventType.ERROR);
    }
}
