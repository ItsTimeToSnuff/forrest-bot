package com.itstimetosnuff.forrest.bot.store;

import com.itstimetosnuff.forrest.bot.session.Session;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySessionStore implements SessionStore {

    private transient Map<Long, Session> sessions;

    public InMemorySessionStore() {
        sessions = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Session> findSession(Long chatId) {
        return Optional.ofNullable(sessions.get(chatId));
    }

    @Override
    public void registerSession(Session session) {
        sessions.put(session.getChatId(), session);
    }

    @Override
    public void closeSession(Session session) {
        sessions.remove(session.getChatId());
    }
}
