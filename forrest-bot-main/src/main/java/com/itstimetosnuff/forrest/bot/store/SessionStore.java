package com.itstimetosnuff.forrest.bot.store;

import com.itstimetosnuff.forrest.bot.session.Session;

import java.util.Optional;

public interface SessionStore {

    Optional<Session> findSession(Long chatId);

    void registerSession(Session session);

    void closeSession(Session session);
}
