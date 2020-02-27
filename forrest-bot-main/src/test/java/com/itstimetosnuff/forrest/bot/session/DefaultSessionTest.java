package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.handler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultSessionTest {

    private Long chatId = 1L;
    @Mock
    private HandlerRegistry mockHandlerRegistry;
    @Mock
    private SessionStore mockSessionStore;
    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private Handler mockHandler;
    @Mock
    private BotApiMethod mockBotApiMethod;
    @Mock
    private List<BotApiMethod> mockExecutes;


    @InjectMocks
    private DefaultSession session;

    @BeforeEach
    void init() {
        session = new DefaultSession(chatId, EventType.LOCK_FREE, mockExecutes, mockHandlerRegistry, mockSessionStore);
    }

    @Test
    void whenDefaultSessionGetChatIdThenReturnIt() {
        //when
        Long found = session.getChatId();
        //then
        assertEquals(chatId, found);
    }

    @Test
    void whenDefaultSessionSetExecutesThenSetIt(){
        //given
        List<BotApiMethod> executes = new ArrayList<>();
        //when
        session.setExecutes(executes);
        //then
        assertEquals(executes, session.getExecutes());
    }

    @Test
    void whenDefaultSessionOnUpdateReturnBotApiMethod() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn("/test");
        when(mockHandlerRegistry.getHandler(any(EventType.class))).thenReturn(mockHandler);
        when(mockHandler.handleEvent(mockUpdate, session)).thenReturn(mockBotApiMethod);
        //when
        BotApiMethod method = session.onUpdate(mockUpdate);
        //then
        assertEquals(mockBotApiMethod, method);
    }

    @Test
    void whenDefaultSessionOnUpdateEventLockReturnBotApiMethod() {
        //given
        session.setEventLock(EventType.GAMES_CREATE);
        when(mockHandlerRegistry.getHandler(any(EventType.class))).thenReturn(mockHandler);
        when(mockHandler.handleEvent(mockUpdate, session)).thenReturn(mockBotApiMethod);
        //when
        BotApiMethod method = session.onUpdate(mockUpdate);
        //then
        assertEquals(mockBotApiMethod, method);
    }

    @Test
    void whenDefaultSessionOnUpdateReturnNull() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn("/test");
        when(mockHandlerRegistry.getHandler(any(EventType.class))).thenReturn(null);
        //when
        BotApiMethod method = session.onUpdate(mockUpdate);
        //then
        assertNull(method);
    }

    @Test
    void whenDefaultSessionCloseThenCloseIt(){
        //when
        session.close();
        //then
        verify(mockSessionStore, times(1)).closeSession(session);
    }
}
