package com.itstimetosnuff.forrest.bot.handler.main;

import com.itstimetosnuff.forrest.bot.handler.AbsBaseHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

public class CalendarHandler extends AbsBaseHandler {

    public CalendarHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {
        variablesInit(update);
        int year = LocalDate.now().getYear();
        int month = Integer.parseInt(data.substring(data.lastIndexOf(":") + 1));
        if (data.contains(Buttons.CALENDAR_SCROLL_BACKWARD_CALLBACK)) {
            month -= 1;
        } else {
            month += 1;
        }
        return editMessage(
                "Выберите дату",
                MainMenuKeyboard.calendar(LocalDate.of(year, month, 1))
        );
    }
}
