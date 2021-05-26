package com.itstimetosnuff.forrest.bot.handler.statistics;

import com.itstimetosnuff.forrest.bot.dto.StatisticsDto;
import com.itstimetosnuff.forrest.bot.entity.User;
import com.itstimetosnuff.forrest.bot.service.DefaultGoogleService;
import com.itstimetosnuff.forrest.bot.session.DefaultSession;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeriodStatisticsHandlerTest {
    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private DefaultGoogleService mockGoogleService;
    @Mock
    private StatisticsDto mockStatisticsDto;

    @InjectMocks
    private PeriodStatisticsHandler periodStatisticsHandler;

    @InjectMocks
    private static final Session mockSession = mock(DefaultSession.class);
    private static final User mockUser = mock(User.class);

    static {
        when(mockSession.getUser()).thenReturn(mockUser);
        when(mockUser.getChatId()).thenReturn(1L);
    }

    @Test
    void whenPeriodStatisticsHandlerHandleEventMonthThenReturnSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.STATISTICS_MONTH);
        when(mockMessage.getMessageId()).thenReturn(1);
        when(mockSession.getGoogleService()).thenReturn(mockGoogleService);
        when(mockGoogleService.statisticsGetMonth(any())).thenReturn(mockStatisticsDto);
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
        when(mockSession.getGoogleService()).thenReturn(mockGoogleService);
        when(mockGoogleService.statisticsGetYear(any())).thenReturn(mockStatisticsDto);
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
