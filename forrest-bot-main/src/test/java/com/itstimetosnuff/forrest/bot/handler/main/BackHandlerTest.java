package com.itstimetosnuff.forrest.bot.handler.main;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BackHandlerTest {
    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private Session mockSession;

    @InjectMocks
    private BackHandler backHandler;

    @Test
    void whenErrorHandlerHandleEventThenReturnSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn("test");
        when(mockMessage.getMessageId()).thenReturn(1);

        //when
        BotApiMethod method = backHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }
}
