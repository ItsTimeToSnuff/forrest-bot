package com.itstimetosnuff.forrest.bot.handler;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.hendler.Handler;
import com.itstimetosnuff.forrest.bot.hendler.HandlerRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HandlerRegistryTest {

    @Mock
    private Map<EventType, Handler> handlers;
    @Mock
    private Handler handler;

    @InjectMocks
    private HandlerRegistry handlerRegistry;

    @Test
    void whenHandlerRegistryGetHandlerThenReturnIt() {
        //when
        handlerRegistry.getHandler(EventType.COMMAND_START);
        //then
        verify(handlers, times(1)).get(any(EventType.class));
    }

    @Test
    void whenHandlerRegistryRegisterThenRegisterIt() {
        //when
        handlerRegistry.register(EventType.COMMAND_START, handler);
        //then
        verify(handlers, times(1)).put(any(EventType.class), any(Handler.class));
    }
}
