package com.itstimetosnuff.forrest.bot.handler.warehouse;

import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.AbsDialogHandler;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.GamesKeyboard;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;

import static com.itstimetosnuff.forrest.bot.utils.Buttons.EMPTY;
import static com.itstimetosnuff.forrest.bot.utils.Buttons.EMPTY_VALUE;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class WarehouseCreditHandler extends AbsDialogHandler {

    private transient WarehouseDto warehouseDto;

    public WarehouseCreditHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {

        variablesInit(update);

        if (data.equals(Buttons.SAVE_CALLBACK)) {
            session.getGoogleService().warehouseWriteCredit(warehouseDto);
            return finishAndClear(formatDto(warehouseDto));
        }
        if (data.equals(Buttons.NO_CALLBACK)) {
            session.getDialogueInfo().getPosition().set(8);
            warehouseDto.setGrenades(EMPTY_VALUE);
            warehouseDto.setGrenadesPlastic(EMPTY_VALUE);
            warehouseDto.setFlashS(EMPTY_VALUE);
            warehouseDto.setFlashM(EMPTY_VALUE);
            warehouseDto.setSmokeS(EMPTY_VALUE);
            warehouseDto.setSmokeM(EMPTY_VALUE);
            warehouseDto.setSmokeXL(EMPTY_VALUE);
        }
        switch (session.getDialogueInfo().getPosition().getAndIncrement()) {
            case 0: {
                warehouseDto = new WarehouseDto();
                warehouseDto.setRecordDate(LocalDate.now());
                warehouseDto.setAuthor(chatId.toString() + "-" + update.getMessage().getFrom().getFirstName());
                startAndInit(EventType.byType(data));
                return sendMessage(
                        "Выберите количество <b>потраченых шаров</b>",
                        GamesKeyboard.gameBalls()
                );
            }
            case 1: {
                warehouseDto.setBalls(data);
                addMsgDelete();
                return sendMessage(
                        "Укажите количество <b>потраченых пачек салфеток</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 2: {
                if (data.equals(EMPTY)) {
                    warehouseDto.setNaples(EMPTY_VALUE);
                } else {
                    warehouseDto.setNaples(data);
                }
                addMsgDelete();
                return sendMessage(
                        "Укажыте количество <b>потраченых бутылок моющего средства</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 3: {
                if (data.equals(EMPTY)) {
                    warehouseDto.setClean(EMPTY_VALUE);
                } else {
                    warehouseDto.setClean(data);
                }
                addMsgDelete();
                return sendMessage(
                        "<b>Гранаты, дымы, флешки</b>?",
                        MainMenuKeyboard.yesNo()
                );
            }
            case 4: {
                addMsgDelete();
                return sendMessage(
                        "Укажите количество <b>картонных гранат</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 5: {
                if (data.equals(EMPTY)) {
                    warehouseDto.setGrenades(EMPTY_VALUE);
                } else {
                    warehouseDto.setGrenades(data);
                }
                addMsgDelete();
                return sendMessage(
                        "Укажите количество <b>пластиковых гранат</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 6: {
                if (data.equals(EMPTY)) {
                    warehouseDto.setGrenadesPlastic(EMPTY_VALUE);
                } else {
                    warehouseDto.setGrenadesPlastic(data);
                }
                addMsgDelete();
                return sendMessage(
                        "Укажите количество <b>потраченых маленьких флешек</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 7: {
                if (data.equals(EMPTY)) {
                    warehouseDto.setFlashS(EMPTY_VALUE);
                } else {
                    warehouseDto.setFlashS(data);
                }
                addMsgDelete();
                return sendMessage(
                        "Укажите количество <b>потраченых средних флешек</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 8: {
                if (data.equals(EMPTY)) {
                    warehouseDto.setFlashM(EMPTY_VALUE);
                } else {
                    warehouseDto.setFlashM(data);
                }
                addMsgDelete();
                return sendMessage(
                        "Укажите количество <b>потраченых дымов S</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 9: {
                if (data.equals(EMPTY)) {
                    warehouseDto.setSmokeS(EMPTY_VALUE);
                } else {
                    warehouseDto.setSmokeS(data);
                }
                addMsgDelete();
                return sendMessage(
                        "Укажите количество <b>потраченых дымов M</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 10: {
                if (data.equals(EMPTY)) {
                    warehouseDto.setSmokeM(EMPTY_VALUE);
                } else {
                    warehouseDto.setSmokeM(data);
                }
                addMsgDelete();
                return sendMessage(
                        "Укажите количество <b>потраченых дымов XL</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 11: {
                if (!data.equals(Buttons.NO_CALLBACK) && !data.equals(EMPTY)) {
                    warehouseDto.setSmokeXL(data);
                } else {
                    warehouseDto.setSmokeXL(EMPTY_VALUE);
                }
                addMsgDelete();
                return sendSaveMessage(formatDto(warehouseDto));
            }
        }
        return null;
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
