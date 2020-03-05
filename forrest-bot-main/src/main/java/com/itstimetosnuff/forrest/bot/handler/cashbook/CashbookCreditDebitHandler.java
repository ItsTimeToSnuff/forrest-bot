package com.itstimetosnuff.forrest.bot.handler.cashbook;

import com.itstimetosnuff.forrest.bot.dto.CashbookDto;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.AbsDialogHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

public class CashbookCreditDebitHandler extends AbsDialogHandler {

    private transient CashbookDto cashbookDto;

    public CashbookCreditDebitHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {

        variablesInit(update);

        if (data.equals(Buttons.SAVE_CALLBACK)) {
            if (session.getEventLock().equals(EventType.CASHBOOK_DEBIT)){
                session.getGoogleService().cashbookWriteDebit(cashbookDto);
            } else {
                session.getGoogleService().cashbookWriteCredit(cashbookDto);
            }
            return finishAndClear(formatDto());
        }
        switch (CREATE_CASE.getAndIncrement()) {
            case 0 : {
                cashbookDto = new CashbookDto();
                cashbookDto.setRecordDate(LocalDate.now());
                cashbookDto.setAuthor(chatId.toString() + "-" + update.getMessage().getFrom().getFirstName());
                startAndInit(EventType.byType(data));
                return sendMessage(
                        "Введите <b>сумму</b>",
                        null
                );
            }
            case 1 : {
                cashbookDto.setAmount(data);
                addMsgDelete();
                return sendMessage(
                        "Введите <b>описание</b>",
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
        return  "<b>Сумма</b>: " + cashbookDto.getAmount() + " грн" + "\n" +
                "<b>Описание</b>: " + cashbookDto.getDescription() + "\n";
    }
}
