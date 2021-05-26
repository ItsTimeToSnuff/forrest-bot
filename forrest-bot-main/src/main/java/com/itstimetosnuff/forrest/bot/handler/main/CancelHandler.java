package com.itstimetosnuff.forrest.bot.handler.main;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.AbsBaseHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CancelHandler extends AbsBaseHandler {

    public CancelHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {
        variablesInit(update);
        session.getDialogueInfo().setEventLock(EventType.LOCK_FREE);
        session.getDialogueInfo().getMsgToDelete().forEach(msg -> session.getExecutes().add(deleteMessage(msg)));
        session.getDialogueInfo().getMsgToDelete().clear();
        session.getDialogueInfo().getPosition().set(0);
        return sendMessage(
                Buttons.ASK_FOR_HELP,
                MainMenuKeyboard.mainMenu(session.getUser().getRole())
        );
    }
}
