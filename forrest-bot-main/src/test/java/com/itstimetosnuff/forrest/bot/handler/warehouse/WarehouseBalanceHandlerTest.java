package com.itstimetosnuff.forrest.bot.handler.warehouse;

import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;
import com.itstimetosnuff.forrest.bot.entity.User;
import com.itstimetosnuff.forrest.bot.service.DefaultGoogleService;
import com.itstimetosnuff.forrest.bot.session.DefaultSession;
import com.itstimetosnuff.forrest.bot.session.Session;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WarehouseBalanceHandlerTest {

    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private DefaultGoogleService mockGoogleService;
    @Mock
    private WarehouseDto mockWarehouseDto;

    @InjectMocks
    private static final Session mockSession = mock(DefaultSession.class);
    private static final User mockUser = mock(User.class);

    @InjectMocks
    private WarehouseBalanceHandler warehouseBalanceHandler;

    static {
        when(mockSession.getUser()).thenReturn(mockUser);
        when(mockUser.getChatId()).thenReturn(1L);
    }

    @Test
    void whenWarehouseBalanceHandlerHandleEventThenReturnSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn("test");
        when(mockMessage.getMessageId()).thenReturn(1);
        when(mockSession.getGoogleService()).thenReturn(mockGoogleService);
        when(mockGoogleService.warehouseGetBalance()).thenReturn(mockWarehouseDto);
        //when
        BotApiMethod method = warehouseBalanceHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }
}
