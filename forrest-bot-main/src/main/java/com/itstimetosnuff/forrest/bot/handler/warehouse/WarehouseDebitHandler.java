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

import static com.itstimetosnuff.forrest.bot.utils.Buttons.EMPTY_VALUE;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class WarehouseDebitHandler extends AbsDialogHandler {

    private transient WarehouseDto warehouseDto;

    public WarehouseDebitHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {

        variablesInit(update);

        if (data.equals(Buttons.SAVE_CALLBACK)) {
            session.getGoogleService().warehouseWriteDebit(warehouseDto);
            return finishAndClear(formatDto(warehouseDto));
        }
        if (data.equals(Buttons.NO_CALLBACK)) {
            session.getDialogueInfo().getPosition().set(8);
            warehouseDto.setGrenades(EMPTY_VALUE);
            warehouseDto.setFlashS(EMPTY_VALUE);
            warehouseDto.setFlashM(EMPTY_VALUE);
            warehouseDto.setSmokeL(EMPTY_VALUE);
        }
        switch (session.getDialogueInfo().getPosition().getAndIncrement()) {
            case 0: {
                warehouseDto = new WarehouseDto();
                warehouseDto.setRecordDate(LocalDate.now());
                warehouseDto.setAuthor(chatId.toString() + "-" + update.getMessage().getFrom().getFirstName());
                startAndInit(EventType.byType(data));
                return sendMessage(
                        "Выберите количество <b>ящиков шаров</b>",
                        WarehouseKeyboard.balls()
                );
            }
            case 1: {
                warehouseDto.setBalls(data);
                return editMessage(
                        "Выберите количество <b>пачек салфеток</b>",
                        WarehouseKeyboard.naplesClean()
                );
            }
            case 2: {
                warehouseDto.setNaples(data);
                return editMessage(
                        "Выберите количество <b>бутылок моющего средства</b>",
                        WarehouseKeyboard.naplesClean()
                );
            }
            case 3: {
                warehouseDto.setClean(data);
                return editMessage(
                        "<b>Гранаты, дымы, флешки</b>?",
                        MainMenuKeyboard.yesNo()
                );
            }
            case 4: {
                return editMessage(
                        "Выберите количество <b>гранат</b>",
                        WarehouseKeyboard.grenades()
                );
            }
            case 5: {
                warehouseDto.setGrenades(data);
                return editMessage(
                        "Выберите количество <b>маленьких флешек</b>",
                        WarehouseKeyboard.grenades()
                );
            }
            case 6: {
                warehouseDto.setFlashS(data);
                return editMessage(
                        "Выберите количество <b>средних флешек</b>",
                        WarehouseKeyboard.grenades()
                );
            }
            case 7: {
                warehouseDto.setFlashM(data);
                return editMessage(
                        "Выберите количество <b>больших дымов</b>",
                        WarehouseKeyboard.grenades()
                );
            }
            case 8: {
                if (!data.equals(Buttons.NO_CALLBACK)) {
                    warehouseDto.setSmokeL(data);
                } else {
                    warehouseDto.setSmokeL(EMPTY_VALUE);
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
                "<b>Маленьких флешек</b>: " + warehouseDto.getFlashS() + " шт\n" +
                "<b>Средних флешек</b>: " + warehouseDto.getFlashM() + " шт\n" +
                "<b>Больших дымов</b>: " + warehouseDto.getSmokeL() + " шт\n" +
                "<b>Салфеток</b>: " + warehouseDto.getNaples() + " пачек\n" +
                "<b>Моющего средства</b>: " + warehouseDto.getClean() + " бут";
    }
}
