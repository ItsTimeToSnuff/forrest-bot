package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.hendler.Handler;
import com.itstimetosnuff.forrest.bot.hendler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

@AllArgsConstructor
public class DefaultSession implements Session {

    private Long carId;

    private HandlerRegistry handlerRegistry;

    @Override
    public Long getChatId() {
        return this.carId;
    }

    @Override
    public BotApiMethod onMessage(Request request) {
        Handler<Request, BotApiMethod> handler = handlerRegistry.getHandler(request.getType());
        if (handler != null){
            return handler.handleEvent(request);
        }
        return null;
    }

    @Override
    public void onClose() {

    }

    @Override
    public void close() {

    }
}
