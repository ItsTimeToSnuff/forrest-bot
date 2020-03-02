package com.itstimetosnuff.forrest.bot.handler.games;

import com.itstimetosnuff.forrest.bot.dto.CreateGameDto;
import com.itstimetosnuff.forrest.bot.session.DefaultSession;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GamesCreateHandlerTest {

    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private CallbackQuery mockCallbackQuery;
    @Mock
    private CreateGameDto createGameDto;
    @Mock
    private static DefaultSession mockSession = mock(DefaultSession.class);

    @InjectMocks
    private TestHelper gamesCreateHandler = new TestHelper(mockSession);

    private String data = "test";

    static {
        when(mockSession.getChatId()).thenReturn(1L);
    }

    private class TestHelper extends GamesCreateHandler {

        private void setCase(int i) {
            CREATE_CASE.set(i);
        }

        private TestHelper(Session session) {
            super(session);
        }

        @Override
        public BotApiMethod handleEvent(Update update) {
            return super.handleEvent(update);
        }
    }

    @BeforeEach
    void init() {
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockMessage.getMessageId()).thenReturn(1);
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase0SendMessage() {
        //given
        gamesCreateHandler.setCase(0);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase1SendMessage() {
        //given
        gamesCreateHandler.setCase(1);
        doNothing().when(createGameDto).setGameType(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase2SendMessage() {
        //given
        gamesCreateHandler.setCase(2);
        doNothing().when(createGameDto).setDate(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase3SendMessage() {
        //given
        gamesCreateHandler.setCase(3);
        doNothing().when(createGameDto).setStartTime(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10:00");
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase4SendMessage() {
        //given
        gamesCreateHandler.setCase(4);
        doNothing().when(createGameDto).setPeople(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase5SendMessage() {
        //given
        gamesCreateHandler.setCase(5);
        doNothing().when(createGameDto).setPhone(any());
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase6SendMessage() {
        //given
        gamesCreateHandler.setCase(6);
        doNothing().when(createGameDto).setDescription(any());
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase6WithEmptyDataSendMessage() {
        //given
        gamesCreateHandler.setCase(6);
        doNothing().when(createGameDto).setDescription(any());
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(" ");
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase6WithSaveCallbackSendMessage() {
        //given
        gamesCreateHandler.setCase(6);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.SAVE_CALLBACK);
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventCase7ThenReturnNull() {
        //given
        gamesCreateHandler.setCase(7);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler.handleEvent(mockUpdate);
        //then
        assertNull(method);
    }
}