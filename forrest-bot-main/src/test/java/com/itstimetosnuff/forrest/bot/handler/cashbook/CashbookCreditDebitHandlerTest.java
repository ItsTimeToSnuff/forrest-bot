package com.itstimetosnuff.forrest.bot.handler.cashbook;

import com.itstimetosnuff.forrest.bot.dto.CashbookDto;
import com.itstimetosnuff.forrest.bot.enums.EventType;
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
public class CashbookCreditDebitHandlerTest {

    @Mock
    private Update mockUpdate;
    @Mock
    private Message mockMessage;
    @Mock
    private CashbookDto cashbookDto;
    @Mock
    private DefaultGoogleService mockDefaultGoogleService;
    @Mock
    private User mockUser;

    private static final com.itstimetosnuff.forrest.bot.entity.User mockBotUser = mock(com.itstimetosnuff.forrest.bot.entity.User.class);
    private static final DialogueInfo mockDialogueInfo = mock(DialogueInfo.class);
    private static final AtomicInteger mockPosition = mock(AtomicInteger.class);
    private static final DefaultSession mockSession = mock(DefaultSession.class);

    @InjectMocks
    private final TestHelper cashbookCreditDebitHandler = new TestHelper(mockSession);

    private final String data = "test";

    static {
        when(mockSession.getDialogueInfo()).thenReturn(mockDialogueInfo);
        when(mockDialogueInfo.getPosition()).thenReturn(mockPosition);
        when(mockSession.getUser()).thenReturn(mockBotUser);
        when(mockBotUser.getChatId()).thenReturn(1L);
    }

    private class TestHelper extends CashbookCreditDebitHandler {

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
        when(mockUpdate.hasCallbackQuery()).thenReturn(false);
        when(mockMessage.getMessageId()).thenReturn(1);
        when(mockUpdate.getMessage()).thenReturn(mockMessage);
        when(mockMessage.getText()).thenReturn(data);
    }

    @Test
    void whenCashbookCreditDebitHandlerHandleEventCase0ThenReturnSendMessage() {
        //given
        when(mockMessage.getFrom()).thenReturn(mockUser);
        when(mockUser.getFirstName()).thenReturn("author");
        cashbookCreditDebitHandler.setCase(0);
        //when
        BotApiMethod method = cashbookCreditDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenCashbookCreditDebitHandlerHandleEventCase1ThenReturnSendMessage() {
        //given
        cashbookCreditDebitHandler.setCase(1);
        doNothing().when(cashbookDto).setAmount(any());
        when(mockMessage.getText()).thenReturn("1");
        //when
        BotApiMethod method = cashbookCreditDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenCashbookCreditDebitHandlerHandleEventCase2ThenReturnSendMessage() {
        //given
        cashbookCreditDebitHandler.setCase(2);
        doNothing().when(cashbookDto).setDescription(any());
        //when
        BotApiMethod method = cashbookCreditDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenCashbookCreditDebitHandlerHandleEventCase2SaveDebitThenReturnSendMessage() {
        //given
        when(mockDialogueInfo.getEventLock()).thenReturn(EventType.CASHBOOK_DEBIT);
        when(mockSession.getGoogleService()).thenReturn(mockDefaultGoogleService);
        doNothing().when(mockDefaultGoogleService).cashbookWriteDebit(any(CashbookDto.class));
        cashbookCreditDebitHandler.setCase(2);
        when(mockMessage.getText()).thenReturn(Buttons.SAVE_CALLBACK);
        when(mockBotUser.getRole()).thenReturn(Role.ADMIN);
        //when
        BotApiMethod method = cashbookCreditDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenCashbookCreditDebitHandlerHandleEventCase2SaveCreditThenReturnSendMessage() {
        //given
        when(mockDialogueInfo.getEventLock()).thenReturn(EventType.CASHBOOK_CREDIT);
        when(mockSession.getGoogleService()).thenReturn(mockDefaultGoogleService);
        doNothing().when(mockDefaultGoogleService).cashbookWriteCredit(any(CashbookDto.class));
        cashbookCreditDebitHandler.setCase(2);
        when(mockMessage.getText()).thenReturn(Buttons.SAVE_CALLBACK);
        when(mockBotUser.getRole()).thenReturn(Role.USER);
        //when
        BotApiMethod method = cashbookCreditDebitHandler.handleEvent(mockUpdate);
        //then
        assertEquals(SendMessage.class, method.getClass());
    }

    @Test
    void whenCashbookCreditDebitHandlerHandleEventCase3ThenReturnNull() {
        //given
        cashbookCreditDebitHandler.setCase(3);
        //when
        BotApiMethod method = cashbookCreditDebitHandler.handleEvent(mockUpdate);
        //then
        assertNull(method);
    }
}
