package com.itstimetosnuff.forrest.bot.handler.games;

import com.itstimetosnuff.forrest.bot.dto.AfterGameDto;
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
public class GamesAfterHandlerTest {

    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private CallbackQuery mockCallbackQuery;
    @Mock
    private AfterGameDto afterGameDto;
    @Mock
    private static DefaultSession mockSession = mock(DefaultSession.class);

    @InjectMocks
    private TestHelper gamesAfterHandler = new TestHelper(mockSession);

    private String data = "test";

    static {
        when(mockSession.getChatId()).thenReturn(1L);
    }

    private class TestHelper extends GamesAfterHandler {

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
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockMessage.getMessageId()).thenReturn(1);
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase0ThenReturnSendMessage() {
        //given
        gamesAfterHandler.setCase(0);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase1ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(1);
        doNothing().when(afterGameDto).setDate(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(data);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventCase2ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(2);
        doNothing().when(afterGameDto).setStartTime(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10:00");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase3ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(3);
        doNothing().when(afterGameDto).setGameType(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(data);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase4ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(4);
        doNothing().when(afterGameDto).setPeople(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase5ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(5);
        doNothing().when(afterGameDto).setBalls(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase6ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(6);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(data);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase6AndSkipThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(6);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.NO_CALLBACK);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase7ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(7);
        doNothing().when(afterGameDto).setGrenades(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase8ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(8);
        doNothing().when(afterGameDto).setFlashM(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase9ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(9);
        doNothing().when(afterGameDto).setFlashL(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase10ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(10);
        doNothing().when(afterGameDto).setSmokeS(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase11ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(11);
        doNothing().when(afterGameDto).setSmokeM(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase12ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(12);
        doNothing().when(afterGameDto).setSmokeL(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase13ThenReturnSendMessage() {
        //given
        gamesAfterHandler.setCase(13);
        doNothing().when(afterGameDto).setGazebo(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase14Repair0ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(14);
        doNothing().when(afterGameDto).setRepair(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(" ");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase14RepairNotNullThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(14);
        doNothing().when(afterGameDto).setRepair(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn("100");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }


    @Test
    void whenGamesAfterHandlerHandleEventCase14SaveThenReturnSendMessage() {
        //given
        gamesAfterHandler.setCase(14);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.SAVE_CALLBACK);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase15ThenReturnNull() {
        //given
        gamesAfterHandler.setCase(15);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertNull(method);
    }

}
