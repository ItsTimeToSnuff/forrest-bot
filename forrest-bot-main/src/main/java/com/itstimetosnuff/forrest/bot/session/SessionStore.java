package com.itstimetosnuff.forrest.bot.session;

import java.util.Optional;

public interface SessionStore {

    Optional<Session> findSession(Long chatId);

    void registerSession(Session session);

    void closeSession(Session session);
}
