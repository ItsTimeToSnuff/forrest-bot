package com.itstimetosnuff.forrest.bot.bot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForRestBotTest {

    private final ForRestBot bot = new ForRestBot();

    @Test
    void test() {
        assertEquals(bot.getBotUsername(), "test");
    }
}
