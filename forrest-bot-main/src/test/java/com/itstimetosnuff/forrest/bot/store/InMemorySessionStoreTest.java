package com.itstimetosnuff.forrest.bot.store;

import com.itstimetosnuff.forrest.bot.session.Session;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class InMemorySessionStoreTest {

    @Mock
    private Session mockSession;
    @Mock
    private Map<Long, Session> mockSessions;

    @InjectMocks
    private InMemorySessionStore sessionStore;

    @Test
    void whenInMemorySessionStoreFindSessionThenFindIt() {
        //when
        sessionStore.findSession(1L);
        //then
        verify(mockSessions, times(1)).get(1L);
    }

    @Test
    void whenInMemorySessionStoreRegisterSessionThenRegisterIt() {
        //given
        when(mockSession.getChatId()).thenReturn(1L);
        //when
        sessionStore.registerSession(mockSession);
        //then
        verify(mockSessions, times(1)).put(1L, mockSession);
    }

    @Test
    void whenInMemorySessionStoreCloseSessionThenCloseIt() {
        //given
        when(mockSession.getChatId()).thenReturn(1L);
        //when
        sessionStore.closeSession(mockSession);
        //then
        verify(mockSessions, times(1)).remove(1L);
    }
}
