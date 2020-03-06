package com.itstimetosnuff.forrest.bot.handler.warehouse;

import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;
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

import static com.itstimetosnuff.forrest.bot.utils.Buttons.EMPTY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WarehouseCreditHandlerTest {

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

    private static DefaultSession mockSession = mock(DefaultSession.class);

    @InjectMocks
    private TestHelper warehouseCreditHandler = new TestHelper(mockSession);

    private String data = "test";

    static {
        when(mockSession.getChatId()).thenReturn(1L);
    }

    private class TestHelper extends WarehouseCreditHandler {

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
    void whenWarehouseCreditHandlerHandleEventCase0ThenReturnSendMessage() {
        //given
        when(mockMessage.getFrom()).thenReturn(mockUser);
        when(mockUser.getFirstName()).thenReturn("author");
        warehouseCreditHandler.setCase(0);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase1ThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(1);
        doNothing().when(mockWarehouseDto).setBalls(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase2ThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(2);
        doNothing().when(mockWarehouseDto).setNaples(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase2WithEmptyDataThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(2);
        doNothing().when(mockWarehouseDto).setNaples(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(EMPTY);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase3ThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(3);
        doNothing().when(mockWarehouseDto).setClean(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase3WithEmptyDataThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(3);
        doNothing().when(mockWarehouseDto).setClean(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(EMPTY);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase3AndSkipThenReturnSendMessage() {
        //given
        warehouseCreditHandler.setCase(3);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.NO_CALLBACK);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase4ThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(4);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.YES_CALLBACK);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase5ThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(5);
        doNothing().when(mockWarehouseDto).setGrenades(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase5WithEmptyDataThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(5);
        doNothing().when(mockWarehouseDto).setGrenades(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(EMPTY);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase6ThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(6);
        doNothing().when(mockWarehouseDto).setFlashS(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase6WithEmptyDataThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(6);
        doNothing().when(mockWarehouseDto).setFlashS(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(EMPTY);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase7ThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(7);
        doNothing().when(mockWarehouseDto).setFlashM(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase7WithEmptyDataThenReturnEditMessage() {
        //given
        warehouseCreditHandler.setCase(7);
        doNothing().when(mockWarehouseDto).setFlashM(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(EMPTY);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase8NotNullSmokeThenReturnSendMessage() {
        //given
        warehouseCreditHandler.setCase(8);
        doNothing().when(mockWarehouseDto).setSmokeL(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase8NullSmokeThenReturnSendMessage() {
        //given
        warehouseCreditHandler.setCase(8);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.NO_CALLBACK);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase8EmptyDataSmokeThenReturnSendMessage() {
        //given
        warehouseCreditHandler.setCase(8);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(EMPTY);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase8SaveThenReturnSendMessage() {
        //given
        warehouseCreditHandler.setCase(8);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.SAVE_CALLBACK);
        when(mockSession.getGoogleService()).thenReturn(mockGoogleService);
        doNothing().when(mockGoogleService).warehouseWriteCredit(mockWarehouseDto);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseCreditHandlerHandleEventCase13ThenReturnNull() {
        //given
        warehouseCreditHandler.setCase(9);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = warehouseCreditHandler.handleEvent(mockUpdate);
        //then
        assertNull(method);
    }
}
