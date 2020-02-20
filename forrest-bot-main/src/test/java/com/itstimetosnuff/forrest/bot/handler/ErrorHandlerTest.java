package com.itstimetosnuff.forrest.bot.handler;

import com.itstimetosnuff.forrest.bot.hendler.ErrorHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ErrorHandlerTest {

    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;

    @InjectMocks
    private ErrorHandler errorHandler;

    @Test
    void whenErrorHandlerHandleEventThenReturnSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(1L);
        when(mockMessage.getText()).thenReturn("test");
        //when
        SendMessage sendMessage = errorHandler.handleEvent(mockUpdate);
        //then
        assertTrue(sendMessage.getText().contains("test"));
        assertEquals(sendMessage.getChatId(), "1");
    }
}
