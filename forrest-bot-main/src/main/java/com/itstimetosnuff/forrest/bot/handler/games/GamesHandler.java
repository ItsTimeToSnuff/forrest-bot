package com.itstimetosnuff.forrest.bot.handler.games;

import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.MethodHelper;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class GamesHandler implements Handler {

    @Override
    public SendMessage handleEvent(Update update, Session session) {
        Long chatId = update.getMessage().getChatId();
        return MethodHelper.sendMessage(
                chatId,
                Buttons.ASK_FOR_HELP,
                MainMenuKeyboard.gamesMenu()
        );
    }
}
