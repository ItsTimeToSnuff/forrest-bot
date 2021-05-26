package com.itstimetosnuff.forrest.bot.handler.main;

import com.itstimetosnuff.forrest.bot.enums.Role;
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
import org.telegram.telegrambots.meta.api.objects.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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

    @InjectMocks
    private static final Session mockSession = mock(DefaultSession.class);
    private static final com.itstimetosnuff.forrest.bot.entity.User mockBotUser = mock(com.itstimetosnuff.forrest.bot.entity.User.class);

    static {
        when(mockSession.getUser()).thenReturn(mockBotUser);
        when(mockBotUser.getChatId()).thenReturn(1L);
    }

    @Test
    void whenStartHandlerHandleEventThenReturnSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn("test");
        when(mockMessage.getMessageId()).thenReturn(1);
        when(mockMessage.getFrom()).thenReturn(mockUser);
        when(mockUser.getFirstName()).thenReturn("test name");
        when(mockBotUser.getRole()).thenReturn(Role.ADMIN);
        //when
        BotApiMethod method = startHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }
}
