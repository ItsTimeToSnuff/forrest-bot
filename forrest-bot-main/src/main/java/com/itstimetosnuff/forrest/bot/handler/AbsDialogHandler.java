package com.itstimetosnuff.forrest.bot.handler;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.time.LocalTime;

public abstract class AbsDialogHandler extends AbsBaseHandler {

    protected AbsDialogHandler(Session session) {
        super(session);
    }

    protected void startAndInit(EventType lockType) {
        session.getExecutes().add(addCancelButton());
        session.getDialogueInfo().setEventLock(lockType);
        session.getDialogueInfo().getMsgToDelete().add(msgId + 2);
    }

    protected SendMessage finishAndClear(String formatDto) {
        session.getDialogueInfo().setEventLock(EventType.LOCK_FREE);
        session.getDialogueInfo().getMsgToDelete().forEach(msg -> session.getExecutes().add(deleteMessage(msg)));
        session.getDialogueInfo().getPosition().set(0);
        session.getDialogueInfo().getMsgToDelete().clear();
        return sendMessage(
                "✅ <b>Запись успешно создана:</b>\n\n" + formatDto,
                MainMenuKeyboard.mainMenu(session.getUser().getRole()));
    }

    protected void addMsgDelete() {
        session.getDialogueInfo().getMsgToDelete().add(msgId);
        session.getDialogueInfo().getMsgToDelete().add(msgId + 1);
    }

    protected LocalTime getTime(String data) {
        int lastIndex = data.lastIndexOf(":");
        int strHours = Integer.parseInt(data.substring(0, lastIndex));
        int strMinutes = Integer.parseInt(data.substring(lastIndex + 1));
        return LocalTime.of(strHours, strMinutes,0,1);
    }

    protected SendMessage sendSaveMessage(String formatDto) {
        return sendMessage(
                "<b>Сохранить запись?</b>\n\n" + formatDto,
                MainMenuKeyboard.save()
        );
    }

    private SendMessage addCancelButton() {
        return sendMessage(
                "\uD83C\uDFC1 <i>Начинаю запись</i>",
                MainMenuKeyboard.cancel()
        );
    }

}
