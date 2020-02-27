package com.itstimetosnuff.forrest.bot.handler.games;

import com.itstimetosnuff.forrest.bot.dto.GameDto;
import com.itstimetosnuff.forrest.bot.handler.Handler;
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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GamesCreateHandlerTest {

    private String data = "test";
    private Integer msgId = 1;
    private Long chatId = 1L;
    @Mock
    private Update mockUpdate;
    @Mock
    Session mockSession;
    @Mock
    Message mockMessage;
    @Mock
    CallbackQuery mockCallbackQuery;
    @Mock
    private GameDto gameCreate;
    @Mock
    private List<Integer> msgIdDelete;
    @Mock
    private Iterator mockIterator;
    @Mock
    private List<BotApiMethod> mockExecutes;

    @InjectMocks
    Handler gamesCreateHandler0 = new GamesCreateHandler(0);
    @InjectMocks
    Handler gamesCreateHandler1 = new GamesCreateHandler(1);
    @InjectMocks
    Handler gamesCreateHandler2 = new GamesCreateHandler(2);
    @InjectMocks
    Handler gamesCreateHandler3 = new GamesCreateHandler(3);
    @InjectMocks
    Handler gamesCreateHandler4 = new GamesCreateHandler(4);
    @InjectMocks
    Handler gamesCreateHandler5 = new GamesCreateHandler(5);
    @InjectMocks
    Handler gamesCreateHandler6 = new GamesCreateHandler(6);
    @InjectMocks
    Handler gamesCreateHandler7 = new GamesCreateHandler(7);

    @BeforeEach
    void init() {
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockMessage.getMessageId()).thenReturn(msgId);
    }

    @Test
    void whenGamesCreateHandlerHandleEventCancelShouldCancelIt() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn(Buttons.CANCEL);
        doCallRealMethod().when(msgIdDelete).forEach(any(Consumer.class));
        when(msgIdDelete.iterator()).thenReturn(mockIterator);
        when(mockIterator.hasNext()).thenReturn(true, false);
        when(mockIterator.next()).thenReturn(1);
        when(mockSession.getExecutes()).thenReturn(mockExecutes);
        //when
        BotApiMethod method = gamesCreateHandler2.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventCalendarScrollForwardThenReturnSendMessage() {
        //given
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.CALENDAR_SCROLL_FORWARD_CALLBACK+ ":1");
        //when
        BotApiMethod method = gamesCreateHandler1.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventCalendarScrollBackwardThenReturnSendMessage() {
        //given
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.CALENDAR_SCROLL_BACKWARD_CALLBACK+ ":2");
        //when
        BotApiMethod method = gamesCreateHandler1.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase0SendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn(data);
        when(mockSession.getExecutes()).thenReturn(new ArrayList<>());
        //when
        BotApiMethod method = gamesCreateHandler0.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase1SendMessage() {
        //given
        when(mockUpdate.hasCallbackQuery()).thenReturn(true);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler1.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase2SendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler2.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase3SendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn("10:00");
        //when
        BotApiMethod method = gamesCreateHandler3.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase4SendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn("1");
        //when
        BotApiMethod method = gamesCreateHandler4.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase5SendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler5.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase6SendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler6.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase6WithEmptyDataSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn(" ");
        //when
        BotApiMethod method = gamesCreateHandler6.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventThenReturnCase6WithSaveCallbackSendMessage() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn(Buttons.SAVE_CALLBACK);
        doCallRealMethod().when(msgIdDelete).forEach(any(Consumer.class));
        when(msgIdDelete.iterator()).thenReturn(mockIterator);
        when(mockIterator.hasNext()).thenReturn(true, false);
        when(mockIterator.next()).thenReturn(1);
        when(mockSession.getExecutes()).thenReturn(mockExecutes);

        //when
        BotApiMethod method = gamesCreateHandler6.handleEvent(mockUpdate, mockSession);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenGamesCreateHandlerHandleEventCase7ThenReturnNull() {
        //given
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getChatId()).thenReturn(chatId);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = gamesCreateHandler7.handleEvent(mockUpdate, mockSession);
        //then
        assertNull(method);
    }
}
