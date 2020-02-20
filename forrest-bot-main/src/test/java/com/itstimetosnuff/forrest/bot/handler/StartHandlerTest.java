package com.itstimetosnuff.forrest.bot.handler;

import com.itstimetosnuff.forrest.bot.hendler.StartHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StartHandlerTest {
    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private User mockUser;

    @InjectMocks
    private StartHandler startHandler;

    @Test
    void whenStartHandlerHandleEventThenReturnSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(1L);
        when(mockMessage.getFrom()).thenReturn(mockUser);
        when(mockUser.getFirstName()).thenReturn("test name");
        //when
        SendMessage sendMessage = startHandler.handleEvent(mockUpdate);
        //then
        assertTrue(sendMessage.getText().contains("test name"));
        assertEquals(sendMessage.getChatId(), "1");
    }
}
