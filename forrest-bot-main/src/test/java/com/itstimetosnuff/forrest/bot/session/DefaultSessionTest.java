package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.hendler.Handler;
import com.itstimetosnuff.forrest.bot.hendler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultSessionTest {

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

    @InjectMocks
    private DefaultSession session;

    @Test
    void whenDefaultSessionGetChatIdThenReturnIt() {
        //given
        Long chatId = 1L;
        session = new DefaultSession(chatId, mockHandlerRegistry, mockSessionStore);
        //when
        Long found = session.getChatId();
        //then
        assertEquals(chatId, found);
    }

    @Test
    void whenDefaultSessionOnUpdateReturnBotApiMethod() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn("/test");
        when(mockHandlerRegistry.getHandler(any(EventType.class))).thenReturn(mockHandler);
        when(mockHandler.handleEvent(mockUpdate)).thenReturn(mockBotApiMethod);
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
