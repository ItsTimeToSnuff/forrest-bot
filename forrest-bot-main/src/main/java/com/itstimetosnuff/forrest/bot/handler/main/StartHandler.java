package com.itstimetosnuff.forrest.bot.handler.main;

import com.itstimetosnuff.forrest.bot.handler.AbsBaseHandler;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import com.itstimetosnuff.forrest.bot.session.Session;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartHandler extends AbsBaseHandler {

    public StartHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {
        variablesInit(update);
        String name = update.getMessage().getFrom().getFirstName();
        return sendMessage(
                String.format("<b>%s</b>,\n" +
                        "Приветствую тебя в боте клуба <i>For.Rest.</i>\nЧем могу помочь?", name),
                MainMenuKeyboard.mainMenu()
        );
    }
}
