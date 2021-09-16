package com.itstimetosnuff.forrest.bot.handler.warehouse;

import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;
import com.itstimetosnuff.forrest.bot.handler.AbsBaseHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class WarehouseBalanceHandler extends AbsBaseHandler {

    public WarehouseBalanceHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {
        variablesInit(update);
        WarehouseDto warehouseDto = session.getGoogleService().warehouseGetBalance();
        return sendMessage(
                "<b>На складе осталось</b>:\n\n" + formatDto(warehouseDto),
                MainMenuKeyboard.back()
        );

    }

    private String formatDto(WarehouseDto warehouseDto) {
        return "<b>Шаров</b>: " + warehouseDto.getBalls() + " ящ\n" +
                "<b>Гранат картонных</b>: " + warehouseDto.getGrenades() + " шт\n" +
                "<b>Гранат пластиковых</b>: " + warehouseDto.getGrenadesPlastic() + " шт\n" +
                "<b>Маленьких флешек</b>: " + warehouseDto.getFlashS() + " шт\n" +
                "<b>Средних флешек</b>: " + warehouseDto.getFlashM() + " шт\n" +
                "<b>Дымов S</b>: " + warehouseDto.getSmokeS() + " шт\n" +
                "<b>Дымов M</b>: " + warehouseDto.getSmokeM() + " шт\n" +
                "<b>Дымов XL</b>: " + warehouseDto.getSmokeXL() + " шт\n" +
                "<b>Салфеток</b>: " + warehouseDto.getNaples() + " пачек\n" +
                "<b>Моющего средства</b>: " + warehouseDto.getClean() + " бут";
    }
}
