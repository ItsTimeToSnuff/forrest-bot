package com.itstimetosnuff.forrest.bot.hendler;

import com.itstimetosnuff.forrest.bot.request.Request;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

public interface Handler <REQ extends Request, RES extends BotApiMethod> {
    RES handleEvent (REQ request);
}
