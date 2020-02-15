package com.itstimetosnuff.forrest.bot.session;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemorySessionStore implements SessionStore {

    private final transient Map<Long, Session> sessions;

    public InMemorySessionStore() {
        sessions = new ConcurrentHashMap<>();
    }

    @Override
    public Optional<Session> findSession(Long chatId) {
        Session session = sessions.get(chatId);
        if (session != null){
            return Optional.of(session);
        }else {
            return Optional.empty();
        }
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
