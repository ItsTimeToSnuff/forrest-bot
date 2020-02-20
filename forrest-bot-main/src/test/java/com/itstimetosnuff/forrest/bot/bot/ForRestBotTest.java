package com.itstimetosnuff.forrest.bot.bot;

import com.itstimetosnuff.forrest.bot.configuration.BotConfiguration;
import com.itstimetosnuff.forrest.bot.factory.SessionFactory;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ForRestBotTest {

    @Mock
    private Session mockSession;
    @Mock
    private SessionStore mockSessionStore;
    @Mock
    private SessionFactory mockSessionFactory;
    @Mock
    private BotConfiguration mockBotConfiguration;
    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private BotApiMethod mockBotApiMethod;

    @InjectMocks
    private ForRestBot bot;

    @Test
    void whenForRestBotOnWebhookWithNewSessionThenReturnBotApiMethod() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(1L);
        when(mockSessionStore.findSession(1L)).thenReturn(Optional.empty());
        when(mockSessionFactory.createSession(1L)).thenReturn(mockSession);
        when(mockSession.onUpdate(mockUpdate)).thenReturn(mockBotApiMethod);
        //when
        BotApiMethod method = bot.onWebhookUpdateReceived(mockUpdate);
        //then
        assertEquals(mockBotApiMethod, method);
    }

    @Test
    void whenForRestBotOnWebhookWithExistingSessionThenReturnBotApiMethod() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(1L);
        when(mockSessionStore.findSession(1L)).thenReturn(Optional.of(mockSession));
        when(mockSession.onUpdate(mockUpdate)).thenReturn(mockBotApiMethod);
        //when
        BotApiMethod method = bot.onWebhookUpdateReceived(mockUpdate);
        //then
        assertEquals(mockBotApiMethod, method);
    }

    @Test
    void whenForRestBotOnWebhookThenReturnNull() {
        //given
        when(mockUpdate.getMessage()).thenThrow(RuntimeException.class);
        //when
        BotApiMethod method = bot.onWebhookUpdateReceived(mockUpdate);
        //then
        assertNull(method);
    }

    @Test
    void whenForRestBotGetBotUsernameThenReturnUsername() {
        //given
        when(mockBotConfiguration.getBotUsername()).thenReturn("test username");
        //then
        assertEquals(bot.getBotUsername(), "test username");
    }

    @Test
    void whenForRestBotGetBotTokenThenReturnToken() {
        //given
        when(mockBotConfiguration.getBotToken()).thenReturn("test token");
        //then
        assertEquals(bot.getBotToken(), "test token");
    }

    @Test
    void whenForRestBotGetBotPathThenReturnPath() {
        //given
        when(mockBotConfiguration.getBotPath()).thenReturn("test path");
        //then
        assertEquals(bot.getBotPath(), "test path");
    }
}
