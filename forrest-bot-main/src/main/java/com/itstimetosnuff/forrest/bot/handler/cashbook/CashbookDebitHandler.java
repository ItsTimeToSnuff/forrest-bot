package com.itstimetosnuff.forrest.bot.handler.cashbook;

import com.itstimetosnuff.forrest.bot.dto.CashbookDebitDto;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.AbsDialogHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CashbookDebitHandler extends AbsDialogHandler {

    private transient CashbookDebitDto cashbookDebitDto;

    public CashbookDebitHandler(Session session) {
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
                cashbookDebitDto = new CashbookDebitDto();
                startAndInit(EventType.CASHBOOK_DEBIT);
                return sendMessage(
                        "Веедите сумму пополнения",
                        null
                );
            }
            case 1 : {
                cashbookDebitDto.setAmount(Integer.parseInt(data));
                addMsgDelete();
                return sendMessage(
                        "Веедите описание",
                        null
                );
            }
            case 2 : {
                cashbookDebitDto.setDescription(data);
                addMsgDelete();
                return sendSaveMessage(formatDto());
            }
        }
        return null;
    }

    private String formatDto() {
        return  "<b>Сумма</b>: " + cashbookDebitDto.getAmount() + "\n" +
                "<b>Описание</b>: " + cashbookDebitDto.getDescription() + "\n";
    }
}
