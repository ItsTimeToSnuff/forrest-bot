package com.itstimetosnuff.forrest.bot.factory;

import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DefaultSessionFactoryTest {

    @Mock
    private SessionStore mockSessionStore;

    @InjectMocks
    private DefaultSessionFactory sessionFactory;

    @Test
    void whenDefaultSessionFactoryCreateSessionThenReturnSession() {
        //when
        Session session = sessionFactory.createSession(1L);
        //then
        verify(mockSessionStore, times(1)).registerSession(any(Session.class));
        assertEquals(1L, session.getChatId());
    }
}
