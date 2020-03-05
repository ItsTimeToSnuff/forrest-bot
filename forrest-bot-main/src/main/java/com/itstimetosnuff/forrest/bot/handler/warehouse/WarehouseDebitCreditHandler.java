package com.itstimetosnuff.forrest.bot.handler.warehouse;

import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.AbsDialogHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import com.itstimetosnuff.forrest.bot.utils.WarehouseKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class WarehouseDebitCreditHandler extends AbsDialogHandler {

    private transient WarehouseDto warehouseDto;

    public WarehouseDebitCreditHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {
        variablesInit(update);
        if (data.equals(Buttons.SAVE_CALLBACK)) {
            if (session.getEventLock().equals(EventType.WAREHOUSE_DEBIT)) {
                session.getGoogleService().warehouseWriteDebit(warehouseDto);
            } else {
                session.getGoogleService().warehouseWriteCredit(warehouseDto);
            }
            return finishAndClear(formatDto(warehouseDto));
        }
        if (data.equals(Buttons.NO_CALLBACK)) {
            CREATE_CASE.set(8);
            warehouseDto.setGrenades("0");
            warehouseDto.setFlashM("0");
            warehouseDto.setFlashL("0");
            warehouseDto.setSmokeL("0");
        }
        switch (CREATE_CASE.getAndIncrement()) {
            case 0: {
                warehouseDto = new WarehouseDto();
                warehouseDto.setDate(LocalDate.now());
                warehouseDto.setAuthor(chatId.toString() + "-" + update.getMessage().getFrom().getFirstName());
                startAndInit(EventType.byType(data));
                return sendMessage(
                        "Выберите количество <b>ящиков шаров</b>",
                        WarehouseKeyboard.balls()
                );
            }
            case 1: {
                warehouseDto.setBalls(data);
                addMsgDelete();
                return editMessage(
                        "Выберите количество <b>пачек салфеток</b>",
                        WarehouseKeyboard.naplesClean()
                );
            }
            case 2: {
                warehouseDto.setNaples(data);
                addMsgDelete();
                return editMessage(
                        "Выберите количество <b>бутылок моющего средства</b>",
                        WarehouseKeyboard.naplesClean()
                );
            }
            case 3: {
                warehouseDto.setClean(data);
                addMsgDelete();
                return editMessage(
                        "Гранаты, дымы, флешки?",
                        MainMenuKeyboard.yesNo()
                );
            }
            case 4: {
                addMsgDelete();
                return editMessage(
                        "Выберите количество <b>гранат</b>",
                        WarehouseKeyboard.grenades()
                );
            }
            case 5: {
                warehouseDto.setGrenades(data);
                addMsgDelete();
                return editMessage(
                        "Выберите количество <b>средних флешек</b>",
                        WarehouseKeyboard.grenades()
                );
            }
            case 6: {
                warehouseDto.setFlashM(data);
                addMsgDelete();
                return editMessage(
                        "Выберите количество <b>больших флешек</b>",
                        WarehouseKeyboard.grenades()
                );
            }
            case 7: {
                warehouseDto.setFlashL(data);
                addMsgDelete();
                return editMessage(
                        "Выберите количество <b>больших дымов</b>",
                        WarehouseKeyboard.grenades()
                );
            }
            case 8: {
                if (!data.equals(Buttons.NO_CALLBACK)) {
                    warehouseDto.setSmokeL(data);
                }
                addMsgDelete();
                return sendSaveMessage(formatDto(warehouseDto));
            }
        }
        return null;
    }

    private String formatDto(WarehouseDto warehouseDto) {
        return "<b>Шаров</b>: " + warehouseDto.getBalls() + " ящ\n" +
                "<b>Гранат</b>: " + warehouseDto.getGrenades() + " шт\n" +
                "<b>Средних флешек</b>: " + warehouseDto.getFlashM() + " шт\n" +
                "<b>Больших флешек</b>: " + warehouseDto.getFlashL() + " шт\n" +
                "<b>Больших дымов</b>: " + warehouseDto.getSmokeL() + " шт\n" +
                "<b>Салфеток</b>: " + warehouseDto.getNaples() + " пачек\n" +
                "<b>Моющего средства</b>: " + warehouseDto.getClean() + " бут";
    }
}
