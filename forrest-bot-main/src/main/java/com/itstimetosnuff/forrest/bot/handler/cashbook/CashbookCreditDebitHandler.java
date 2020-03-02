package com.itstimetosnuff.forrest.bot.handler.cashbook;

import com.itstimetosnuff.forrest.bot.dto.CashbookDto;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.AbsDialogHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CashbookCreditDebitHandler extends AbsDialogHandler {

    private transient CashbookDto cashbookDto;

    public CashbookCreditDebitHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {

        variablesInit(update);

        if (data.equals(Buttons.SAVE_CALLBACK)) {
            return finishAndClear(formatDto());
        }
        switch (CREATE_CASE.getAndIncrement()) {
            case 0 : {
                cashbookDto = new CashbookDto();
                startAndInit(EventType.CASHBOOK_DEBIT);
                return sendMessage(
                        "Введите сумму",
                        null
                );
            }
            case 1 : {
                cashbookDto.setAmount(Integer.parseInt(data));
                addMsgDelete();
                return sendMessage(
                        "Введите описание",
                        null
                );
            }
            case 2 : {
                cashbookDto.setDescription(data);
                addMsgDelete();
                return sendSaveMessage(formatDto());
            }
        }
        return null;
    }

    private String formatDto() {
        return  "<b>Сумма</b>: " + cashbookDto.getAmount() + "\n" +
                "<b>Описание</b>: " + cashbookDto.getDescription() + "\n";
    }
}
