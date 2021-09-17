package com.itstimetosnuff.forrest.bot.handler.games;

import com.itstimetosnuff.forrest.bot.dto.AfterGameDto;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.AbsDialogHandler;

import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.GamesKeyboard;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.itstimetosnuff.forrest.bot.utils.Buttons.EMPTY_VALUE;

@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class GamesAfterHandler extends AbsDialogHandler {

    private transient AfterGameDto afterGameDto;

    public GamesAfterHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {

        variablesInit(update);

        if (data.equals(Buttons.SAVE_CALLBACK)) {
            session.getGoogleService().gameRecordAfter(afterGameDto);
            return finishAndClear(formatDto());
        }
        if (data.equals(Buttons.NO_CALLBACK)) {
            session.getDialogueInfo().getPosition().set(13);
            afterGameDto.setGrenades(EMPTY_VALUE);
            afterGameDto.setGrenadesPlastic(EMPTY_VALUE);
            afterGameDto.setFlashS(EMPTY_VALUE);
            afterGameDto.setFlashM(EMPTY_VALUE);
            afterGameDto.setSmokeS(EMPTY_VALUE);
            afterGameDto.setSmokeM(EMPTY_VALUE);
            afterGameDto.setSmokeXL(EMPTY_VALUE);
        }
        switch (session.getDialogueInfo().getPosition().getAndIncrement()) {
            case 0: {
                afterGameDto = new AfterGameDto();
                afterGameDto.setAuthor(chatId.toString() + "-" + update.getMessage().getFrom().getFirstName());
                startAndInit(EventType.GAMES_AFTER);
                return sendMessage(
                        "Выберите <b>дату</b>",
                        MainMenuKeyboard.calendar(LocalDate.now())
                );
            }
            case 1: {
                afterGameDto.setDate(LocalDate.parse(data));
                return editMessage(
                        "Выберите <b>время начала</b>",
                        GamesKeyboard.gameTime()
                );
            }
            case 2: {
                LocalTime startTime = getTime(data);
                afterGameDto.setStartTime(startTime);
                return editMessage(
                        "Выберите <b>тип игры</b>",
                        GamesKeyboard.gameType()
                );
            }
            case 3: {
                afterGameDto.setGameType(data);
                return editMessage(
                        "Укажите количество <b>игроков</b>",
                        GamesKeyboard.gamePeople()
                );
            }
            case 4: {
                afterGameDto.setPeople(data);
                return editMessage(
                        "Укажите количество <b>купленых шаров</b>",
                        GamesKeyboard.gameBalls()
                );
            }
            case 5: {
                afterGameDto.setBalls(data);
                return editMessage(
                        "Докупались: <b>гранаты, флешки, дымы?</b>",
                        MainMenuKeyboard.yesNo()
                );
            }
            case 6: {
                return editMessage(
                        "Укажите количество купленых <b>картонных гранат</b>",
                        GamesKeyboard.gameConsumables()
                );
            }
            case 7: {
                afterGameDto.setGrenades(data);
                return editMessage(
                        "Укажите количество купленых <b>пластиковых гранат</b>",
                        GamesKeyboard.gameConsumables()
                );
            }
            case 8: {
                afterGameDto.setGrenadesPlastic(data);
                return editMessage(
                        "Укажите количество купленых <b>маленьких флешек</b>",
                        GamesKeyboard.gameConsumables()
                );
            }
            case 9: {
                afterGameDto.setFlashS(data);
                return editMessage(
                        "Укажите количество купленых <b>средних флешек</b>",
                        GamesKeyboard.gameConsumables()
                );
            }
            case 10: {
                afterGameDto.setFlashM(data);
                return editMessage(
                        "Укажите количество купленых <b>дымов S</b>",
                        GamesKeyboard.gameConsumables()
                );
            }
            case 11: {
                afterGameDto.setSmokeS(data);
                return editMessage(
                        "Укажите количество купленых <b>дымов M</b>",
                        GamesKeyboard.gameConsumables()
                );
            }
            case 12: {
                afterGameDto.setSmokeM(data);
                return editMessage(
                        "Укажите количество купленых <b>дымов XL</b>",
                        GamesKeyboard.gameConsumables()
                );
            }
            case 13: {
                if (!data.equals(Buttons.NO_CALLBACK)) {
                    afterGameDto.setSmokeXL(data);
                }
                return editMessage(
                        "Укажите сколько <b>часов сидели на беседках</b>",
                        GamesKeyboard.gameGazebo()
                );
            }
            case 14: {
                addMsgDelete();
                afterGameDto.setGazebo(data);
                return sendMessage(
                        "Укажите сумму за <b>поломки</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 15: {
                if (data.equals(Buttons.EMPTY)) {
                    afterGameDto.setRepair(EMPTY_VALUE);
                } else {
                    addMsgDelete();
                    afterGameDto.setRepair(data);
                }
                addMsgDelete();
                return sendMessage(
                        "Укажите сумму <b>предоплаты</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 16: {
                if (data.equals(Buttons.EMPTY)) {
                    afterGameDto.setPrepayment(EMPTY_VALUE);
                } else {
                    addMsgDelete();
                    afterGameDto.setPrepayment(data);
                }
                addMsgDelete();
                return sendSaveMessage(formatDto());
            }
        }
        return null;
    }

    private String formatDto() {
        return "<b>Дата</b>: " + afterGameDto.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
                "<b>Время начала</b>: " + afterGameDto.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" +
                "<b>Тип игры</b>: " + afterGameDto.getGameType() + "\n" +
                "<b>Количество игроков</b>: " + afterGameDto.getPeople() + "\n" +
                "<b>Куплено шаров</b>: " + afterGameDto.getBalls() + " ящ" + "\n" +
                "<b>Куплено картонных гранат</b>: " + afterGameDto.getGrenades() + " шт" + "\n" +
                "<b>Куплено пластиковых гранат</b>: " + afterGameDto.getGrenadesPlastic() + " шт" + "\n" +
                "<b>Куплено маленьких флешек</b>: " + afterGameDto.getFlashS() + " шт" + "\n" +
                "<b>Куплено средних флешек</b>: " + afterGameDto.getFlashM() + " шт" + "\n" +
                "<b>Куплено дымов S</b>: " + afterGameDto.getSmokeS() + " шт" + "\n" +
                "<b>Куплено дымов M</b>: " + afterGameDto.getSmokeM() + " шт" + "\n" +
                "<b>Куплено дымов XL</b>: " + afterGameDto.getSmokeXL() + " шт" + "\n" +
                "<b>Часов на беседке</b>: " + afterGameDto.getGazebo() + " ч" + "\n" +
                "<b>Поломки</b>: " + afterGameDto.getRepair() + " грн" + "\n" +
                "<b>Предоплата</b>: " + afterGameDto.getPrepayment() + " грн" + "\n";
    }

}
