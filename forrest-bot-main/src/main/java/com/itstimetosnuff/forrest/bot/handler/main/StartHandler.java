package com.itstimetosnuff.forrest.bot.handler.main;

import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.MethodHelper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartHandler implements Handler {

    @Override
    public SendMessage handleEvent(Update update, Session session) {
        Long chatId = update.getMessage().getChatId();
        String name = update.getMessage().getFrom().getFirstName();
        return MethodHelper.sendMessage(
                chatId,
                String.format("<b>%s</b>,\nПриветствую тебя в боте клуба <i>For.Rest.</i>\nЧем могу помочь?", name),
                MainMenuKeyboard.mainMenu()
        );
    }
}
