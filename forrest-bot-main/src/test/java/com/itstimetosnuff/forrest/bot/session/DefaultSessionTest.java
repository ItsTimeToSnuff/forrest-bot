package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.entity.User;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.DialogueInfo;
import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.handler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.service.DefaultGoogleService;
import com.itstimetosnuff.forrest.bot.service.GoogleService;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    @Mock
    private List<BotApiMethod> mockExecutes;
    @Mock
    private CallbackQuery mockCallbackQuery;
    @Mock
    private DefaultGoogleService mockDefaultGoogleService;
    @Mock
    private User mockUser;
    @Mock
    private DialogueInfo mockDialogueInfo;

    @InjectMocks
    private DefaultSession session;

    @BeforeEach
    void init() {
        session = new DefaultSession(mockUser, mockDialogueInfo, mockExecutes, mockHandlerRegistry, mockSessionStore, mockDefaultGoogleService);
    }

    @Test
    void whenDefaultSessionGetUserThenReturnIt() {
        //when
        User found = session.getUser();
        //then
        assertEquals(mockUser, found);
    }

    @Test
    void whenDefaultSessionGetExecutesThenReturnIt() {
        //when
        List<BotApiMethod> found = session.getExecutes();
        //then
        assertEquals(mockExecutes, found);
    }

    @Test
    void whenGetGoogleServiceThenReturnIt() {
        //when
        GoogleService found = session.getGoogleService();
        //then
        assertEquals(mockDefaultGoogleService, found);
    }

    @Test
    void whenGetDialogueInfoThenReturnIt() {
        //when
        DialogueInfo found = session.getDialogueInfo();
        //then
        assertEquals(mockDialogueInfo, found);
    }

    @Test
    void whenDefaultSessionOnUpdateReturnBotApiMethod() {
        //given
        when(mockDialogueInfo.getEventLock()).thenReturn(EventType.LOCK_FREE);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
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
    void whenDefaultSessionOnUpdateEventLockReturnBotApiMethod() {
        //given
        when(mockDialogueInfo.getEventLock()).thenReturn(EventType.LOCK_FREE);
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getData()).thenReturn("test");
        session.getDialogueInfo().setEventLock(EventType.GAMES_CREATE);
        when(mockHandlerRegistry.getHandler(any(EventType.class))).thenReturn(mockHandler);
        when(mockHandler.handleEvent(mockUpdate)).thenReturn(mockBotApiMethod);
        //when
        BotApiMethod method = session.onUpdate(mockUpdate);
        //then
        assertEquals(mockBotApiMethod, method);
    }

    @Test
    void whenDefaultSessionOnUpdateCancelReturnBotApiMethod() {
        //given
        when(mockDialogueInfo.getEventLock()).thenReturn(EventType.LOCK_FREE);
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.CANCEL);
        session.getDialogueInfo().setEventLock(EventType.GAMES_CREATE);
        when(mockHandlerRegistry.getHandler(any(EventType.class))).thenReturn(mockHandler);
        when(mockHandler.handleEvent(mockUpdate)).thenReturn(mockBotApiMethod);
        //when
        BotApiMethod method = session.onUpdate(mockUpdate);
        //then
        assertEquals(mockBotApiMethod, method);
    }

    @Test
    void whenDefaultSessionOnUpdateCalendarForwardReturnBotApiMethod() {
        //given
        when(mockDialogueInfo.getEventLock()).thenReturn(EventType.LOCK_FREE);
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.CALENDAR_SCROLL_FORWARD_CALLBACK);
        session.getDialogueInfo().setEventLock(EventType.GAMES_CREATE);
        when(mockHandlerRegistry.getHandler(any(EventType.class))).thenReturn(mockHandler);
        when(mockHandler.handleEvent(mockUpdate)).thenReturn(mockBotApiMethod);
        //when
        BotApiMethod method = session.onUpdate(mockUpdate);
        //then
        assertEquals(mockBotApiMethod, method);
    }

    @Test
    void whenDefaultSessionOnUpdateCalendarBackwardReturnBotApiMethod() {
        //given
        when(mockDialogueInfo.getEventLock()).thenReturn(EventType.LOCK_FREE);
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.CALENDAR_SCROLL_BACKWARD_CALLBACK);
        session.getDialogueInfo().setEventLock(EventType.GAMES_CREATE);
        when(mockHandlerRegistry.getHandler(any(EventType.class))).thenReturn(mockHandler);
        when(mockHandler.handleEvent(mockUpdate)).thenReturn(mockBotApiMethod);
        //when
        BotApiMethod method = session.onUpdate(mockUpdate);
        //then
        assertEquals(mockBotApiMethod, method);
    }

    @Test
    void whenDefaultSessionCloseThenCloseIt(){
        //when
        session.close();
        //then
        verify(mockSessionStore, times(1)).closeSession(session);
    }
}
