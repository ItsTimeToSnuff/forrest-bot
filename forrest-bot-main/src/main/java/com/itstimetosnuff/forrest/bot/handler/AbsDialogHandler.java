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
        session.setEventLock(lockType);
        MSG_DELETE_IDs.add(msgId + 2);
    }

    protected SendMessage finishAndClear(String formatDto) {
        session.setEventLock(EventType.LOCK_FREE);
        MSG_DELETE_IDs.forEach(msg -> session.getExecutes().add(deleteMessage(msg)));
        CREATE_CASE.set(0);
        MSG_DELETE_IDs.clear();
        return sendMessage(
                "✅ <b>Запись успешно создана:</b>\n\n" + formatDto,
                MainMenuKeyboard.mainMenu());
    }

    protected void addMsgDelete() {
        MSG_DELETE_IDs.add(msgId);
        MSG_DELETE_IDs.add(msgId + 1);
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
