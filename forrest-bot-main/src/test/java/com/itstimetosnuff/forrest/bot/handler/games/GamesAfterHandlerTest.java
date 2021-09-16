package com.itstimetosnuff.forrest.bot.handler.games;

import com.itstimetosnuff.forrest.bot.dto.AfterGameDto;
import com.itstimetosnuff.forrest.bot.enums.Role;
import com.itstimetosnuff.forrest.bot.handler.DialogueInfo;
import com.itstimetosnuff.forrest.bot.service.DefaultGoogleService;
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
import org.telegram.telegrambots.meta.api.objects.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

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
    private AfterGameDto mockAfterGameDto;
    @Mock
    private User mockUser;
    @Mock
    private DefaultGoogleService mockGoogleService;

    private static final com.itstimetosnuff.forrest.bot.entity.User mockBotUser = mock(com.itstimetosnuff.forrest.bot.entity.User.class);
    private static final DialogueInfo mockDialogueInfo = mock(DialogueInfo.class);
    private static final AtomicInteger mockPosition = mock(AtomicInteger.class);
    private static final DefaultSession mockSession = mock(DefaultSession.class);

    @InjectMocks
    private final TestHelper gamesAfterHandler = new TestHelper(mockSession);

    private final String data = "test";

    static {
        when(mockSession.getDialogueInfo()).thenReturn(mockDialogueInfo);
        when(mockDialogueInfo.getPosition()).thenReturn(mockPosition);
        when(mockSession.getUser()).thenReturn(mockBotUser);
        when(mockBotUser.getChatId()).thenReturn(1L);
    }

    private class TestHelper extends GamesAfterHandler {

        private void setCase(int i) {
            session.getDialogueInfo().getPosition().set(i);
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
        when(mockMessage.getFrom()).thenReturn(mockUser);
        when(mockUser.getFirstName()).thenReturn("author");
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
        doNothing().when(mockAfterGameDto).setDate(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(LocalDate.now().toString());
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventCase2ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(2);
        doNothing().when(mockAfterGameDto).setStartTime(any());
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
        doNothing().when(mockAfterGameDto).setGameType(any());
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
        doNothing().when(mockAfterGameDto).setPeople(any());
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
        doNothing().when(mockAfterGameDto).setBalls(any());
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
        doNothing().when(mockAfterGameDto).setGrenades(any());
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
        doNothing().when(mockAfterGameDto).setGrenadesPlastic(any());
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
        doNothing().when(mockAfterGameDto).setFlashS(any());
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
        doNothing().when(mockAfterGameDto).setFlashM(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase11ThenReturnSendMessage() {
        //given
        gamesAfterHandler.setCase(11);
        doNothing().when(mockAfterGameDto).setSmokeS(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase12ThenReturnSendMessage() {
        //given
        gamesAfterHandler.setCase(12);
        doNothing().when(mockAfterGameDto).setSmokeM(any());
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
        doNothing().when(mockAfterGameDto).setSmokeXL(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase14ThenReturnSendMessage() {
        //given
        gamesAfterHandler.setCase(14);
        doNothing().when(mockAfterGameDto).setGazebo(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("10");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase15Repair0ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(15);
        doNothing().when(mockAfterGameDto).setRepair(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.EMPTY);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase15RepairNotNullThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(15);
        doNothing().when(mockAfterGameDto).setRepair(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn("100");
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase16Repair0ThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(16);
        doNothing().when(mockAfterGameDto).setPrepayment(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.EMPTY);
        when(mockAfterGameDto.getStartTime()).thenReturn(LocalTime.now());
        when(mockAfterGameDto.getDate()).thenReturn(LocalDate.now());
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase16RepairNotNullThenReturnEditMessage() {
        //given
        gamesAfterHandler.setCase(16);
        doNothing().when(mockAfterGameDto).setPrepayment(any());
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn("100");
        when(mockAfterGameDto.getStartTime()).thenReturn(LocalTime.now());
        when(mockAfterGameDto.getDate()).thenReturn(LocalDate.now());
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }


    @Test
    void whenGamesAfterHandlerHandleEventCase16SaveThenReturnSendMessage() {
        //given
        gamesAfterHandler.setCase(16);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.SAVE_CALLBACK);
        when(mockSession.getGoogleService()).thenReturn(mockGoogleService);
        doNothing().when(mockGoogleService).gameRecordAfter(mockAfterGameDto);
        when(mockAfterGameDto.getStartTime()).thenReturn(LocalTime.now());
        when(mockAfterGameDto.getDate()).thenReturn(LocalDate.now());
        when(mockBotUser.getRole()).thenReturn(Role.USER);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesAfterHandlerHandleEventCase16ThenReturnNull() {
        //given
        gamesAfterHandler.setCase(17);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesAfterHandler.handleEvent(mockUpdate);
        //then
        assertNull(method);
    }

}
