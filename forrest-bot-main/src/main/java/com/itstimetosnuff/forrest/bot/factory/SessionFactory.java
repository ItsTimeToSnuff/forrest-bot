package com.itstimetosnuff.forrest.bot.factory;

import com.itstimetosnuff.forrest.bot.session.Session;

public interface SessionFactory {

    Session createSession(Long chatId);
}
