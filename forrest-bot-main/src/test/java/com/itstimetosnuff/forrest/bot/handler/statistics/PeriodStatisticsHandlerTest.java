package com.itstimetosnuff.forrest.bot.handler.statistics;

import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeriodStatisticsHandlerTest {
    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private Session mockSession;

    @InjectMocks
    private PeriodStatisticsHandler periodStatisticsHandler;

    @Test
    void whenPeriodStatisticsHandlerHandleEventMonthThenReturnSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.STATISTICS_MONTH);
        when(mockMessage.getMessageId()).thenReturn(1);
        //when
        BotApiMethod method = periodStatisticsHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenPeriodStatisticsHandlerHandleEventYearThenReturnSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.STATISTICS_YEAR);
        when(mockMessage.getMessageId()).thenReturn(1);
        //when
        BotApiMethod method = periodStatisticsHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenPeriodStatisticsHandlerHandleEventThenReturnNull() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(" ");
        when(mockMessage.getMessageId()).thenReturn(1);
        //when
        BotApiMethod method = periodStatisticsHandler.handleEvent(mockUpdate);
        //then
        assertNull(method);
    }
}
