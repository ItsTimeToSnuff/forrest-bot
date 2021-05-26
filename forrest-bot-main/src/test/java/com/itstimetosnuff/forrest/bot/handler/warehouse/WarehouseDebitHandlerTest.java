package com.itstimetosnuff.forrest.bot.handler.warehouse;

import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;
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

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WarehouseDebitHandlerTest {

    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private CallbackQuery mockCallbackQuery;
    @Mock
    private WarehouseDto mockWarehouseDto;
    @Mock
    private DefaultGoogleService mockGoogleService;
    @Mock
    private User mockUser;

    private static final com.itstimetosnuff.forrest.bot.entity.User mockBotUser = mock(com.itstimetosnuff.forrest.bot.entity.User.class);
    private static final DialogueInfo mockDialogueInfo = mock(DialogueInfo.class);
    private static final AtomicInteger mockPosition = mock(AtomicInteger.class);
    private static final DefaultSession mockSession = mock(DefaultSession.class);

    @InjectMocks
    private final TestHelper warehouseDebitHandler = new TestHelper(mockSession);

    private final String data = "test";

    static {
        when(mockSession.getDialogueInfo()).thenReturn(mockDialogueInfo);
        when(mockDialogueInfo.getPosition()).thenReturn(mockPosition);
        when(mockSession.getUser()).thenReturn(mockBotUser);
        when(mockBotUser.getChatId()).thenReturn(1L);
    }

    private class TestHelper extends WarehouseDebitHandler {

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
    void whenWarehouseDebitHandlerHandleEventCase0ThenReturnSendMessage() {
        //given
        when(mockMessage.getFrom()).thenReturn(mockUser);
        when(mockUser.getFirstName()).thenReturn("author");
        warehouseDebitHandler.setCase(0);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase1ThenReturnEditMessage() {
        //given
        warehouseDebitHandler.setCase(1);
        doNothing().when(mockWarehouseDto).setBalls(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase2ThenReturnEditMessage() {
        //given
        warehouseDebitHandler.setCase(2);
        doNothing().when(mockWarehouseDto).setNaples(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase3ThenReturnEditMessage() {
        //given
        warehouseDebitHandler.setCase(3);
        doNothing().when(mockWarehouseDto).setClean(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase3AndSkipThenReturnSendMessage() {
        //given
        warehouseDebitHandler.setCase(3);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.NO_CALLBACK);
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase4ThenReturnEditMessage() {
        //given
        warehouseDebitHandler.setCase(4);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.YES_CALLBACK);
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase5ThenReturnEditMessage() {
        //given
        warehouseDebitHandler.setCase(5);
        doNothing().when(mockWarehouseDto).setGrenades(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase6ThenReturnEditMessage() {
        //given
        warehouseDebitHandler.setCase(6);
        doNothing().when(mockWarehouseDto).setFlashS(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase7ThenReturnEditMessage() {
        //given
        warehouseDebitHandler.setCase(7);
        doNothing().when(mockWarehouseDto).setFlashM(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase8NotNullSmokeThenReturnSendMessage() {
        //given
        warehouseDebitHandler.setCase(8);
        doNothing().when(mockWarehouseDto).setSmokeL(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase8NullSmokeThenReturnSendMessage() {
        //given
        warehouseDebitHandler.setCase(8);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.NO_CALLBACK);
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase8SaveThenReturnSendMessage() {
        //given
        warehouseDebitHandler.setCase(8);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.SAVE_CALLBACK);
        when(mockSession.getGoogleService()).thenReturn(mockGoogleService);
        doNothing().when(mockGoogleService).warehouseWriteDebit(mockWarehouseDto);
        when(mockBotUser.getRole()).thenReturn(Role.ADMIN);
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitHandlerHandleEventCase13ThenReturnNull() {
        //given
        warehouseDebitHandler.setCase(9);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = warehouseDebitHandler.handleEvent(mockUpdate);
        //then
        assertNull(method);
    }
}
