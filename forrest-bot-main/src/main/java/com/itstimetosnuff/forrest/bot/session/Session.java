package com.itstimetosnuff.forrest.bot.session;

import com.itstimetosnuff.forrest.bot.entity.User;
import com.itstimetosnuff.forrest.bot.handler.DialogueInfo;
import com.itstimetosnuff.forrest.bot.handler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.service.GoogleService;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Session {

    protected final User user;

    protected DialogueInfo dialogueInfo;

    protected List<BotApiMethod> executes;

    protected HandlerRegistry handlerRegistry;

    protected SessionStore sessionStore;

    protected GoogleService googleService;


    public abstract BotApiMethod onUpdate(Update update);

    public abstract void close();
}
