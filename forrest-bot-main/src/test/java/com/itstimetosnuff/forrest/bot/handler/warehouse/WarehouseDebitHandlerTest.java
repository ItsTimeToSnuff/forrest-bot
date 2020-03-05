package com.itstimetosnuff.forrest.bot.handler.warehouse;

import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;
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
public class WarehouseDebitHandlerTest {

    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private CallbackQuery mockCallbackQuery;
    @Mock
    private WarehouseDto warehouseDto;
    @Mock
    private static DefaultSession mockSession = mock(DefaultSession.class);

    @InjectMocks
    private TestHelper warehouseDebitCreditHandler = new TestHelper(mockSession);

    private String data = "test";

    static {
        when(mockSession.getChatId()).thenReturn(1L);
    }

    private class TestHelper extends WarehouseDebitHandler {

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
    void whenWarehouseDebitCreditHandlerHandleEventCase0ThenReturnSendMessage() {
        //given
        warehouseDebitCreditHandler.setCase(0);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase1ThenReturnEditMessage() {
        //given
        warehouseDebitCreditHandler.setCase(1);
        doNothing().when(warehouseDto).setBalls(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase2ThenReturnEditMessage() {
        //given
        warehouseDebitCreditHandler.setCase(2);
        doNothing().when(warehouseDto).setNaples(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase3ThenReturnEditMessage() {
        //given
        warehouseDebitCreditHandler.setCase(3);
        doNothing().when(warehouseDto).setClean(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase3AndSkipThenReturnSendMessage() {
        //given
        warehouseDebitCreditHandler.setCase(3);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.NO_CALLBACK);
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase4ThenReturnEditMessage() {
        //given
        warehouseDebitCreditHandler.setCase(4);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.YES_CALLBACK);
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase5ThenReturnEditMessage() {
        //given
        warehouseDebitCreditHandler.setCase(5);
        doNothing().when(warehouseDto).setGrenades(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase6ThenReturnEditMessage() {
        //given
        warehouseDebitCreditHandler.setCase(6);
        doNothing().when(warehouseDto).setFlashS(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase7ThenReturnEditMessage() {
        //given
        warehouseDebitCreditHandler.setCase(7);
        doNothing().when(warehouseDto).setFlashM(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(EditMessageText.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase8NotNullSmokeThenReturnSendMessage() {
        //given
        warehouseDebitCreditHandler.setCase(8);
        doNothing().when(warehouseDto).setSmokeL(any());
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn("1");
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase8NullSmokeThenReturnSendMessage() {
        //given
        warehouseDebitCreditHandler.setCase(8);
        when(mockUpdate.getCallbackQuery()).thenReturn(mockCallbackQuery);
        when(mockCallbackQuery.getMessage()).thenReturn(mockMessage);
        when(mockCallbackQuery.getData()).thenReturn(Buttons.NO_CALLBACK);
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase8SaveThenReturnSendMessage() {
        //given
        warehouseDebitCreditHandler.setCase(8);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(Buttons.SAVE_CALLBACK);
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenWarehouseDebitCreditHandlerHandleEventCase13ThenReturnNull() {
        //given
        warehouseDebitCreditHandler.setCase(9);
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
        //when
        BotApiMethod method = warehouseDebitCreditHandler.handleEvent(mockUpdate);
        //then
        assertNull(method);
    }
}
