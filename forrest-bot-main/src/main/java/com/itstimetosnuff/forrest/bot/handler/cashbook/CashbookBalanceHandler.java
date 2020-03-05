package com.itstimetosnuff.forrest.bot.handler.cashbook;

import com.itstimetosnuff.forrest.bot.dto.CashbookDto;
import com.itstimetosnuff.forrest.bot.handler.AbsBaseHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CashbookBalanceHandler extends AbsBaseHandler {

    public CashbookBalanceHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {
        variablesInit(update);

        String balance = session.getGoogleService().cashbookGetBalance();
        return sendMessage(
                "<b>В кассе сейчас:</b> " + balance + " грн",
                MainMenuKeyboard.back()
        );
    }
}
