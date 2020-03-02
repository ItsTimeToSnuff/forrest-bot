package com.itstimetosnuff.forrest.bot.handler.main;

import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CalendarHandlerTest {

    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private Session mockSession;
    @Mock
    private CallbackQuery mockCallbackQuery;

    @InjectMocks
    private CalendarHandler calendarHandler;

    @Test
    void whenCalendarHandlerHandleEventScrollBackwardThenReturnEditMessage() {
        //given
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.CALENDAR_SCROLL_BACKWARD_CALLBACK + ":5");
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getMessageId()).thenReturn(1);
        //when
        BotApiMethod method = calendarHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenCalendarHandlerHandleEventScrollForwardThenReturnEditMessage() {
        //given
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.CALENDAR_SCROLL_FORWARD_CALLBACK + ":5");
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getMessageId()).thenReturn(1);
        //when
        BotApiMethod method = calendarHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }
}
