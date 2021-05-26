package com.itstimetosnuff.forrest.bot.handler.statistics;

import com.itstimetosnuff.forrest.bot.handler.AbsBaseHandler;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import com.itstimetosnuff.forrest.bot.session.Session;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StatisticsHandler extends AbsBaseHandler {

    public StatisticsHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {
        variablesInit(update);
        return sendMessage(
                Buttons.ASK_FOR_HELP,
                MainMenuKeyboard.statisticsMenu(session.getUser().getRole())
        );
    }
}
