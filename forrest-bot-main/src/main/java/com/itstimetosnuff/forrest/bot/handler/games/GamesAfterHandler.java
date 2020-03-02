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

public class GamesAfterHandler extends AbsDialogHandler {

    private transient AfterGameDto afterGameDto;

    public GamesAfterHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {

        variablesInit(update);

        if (data.equals(Buttons.SAVE_CALLBACK)) {
            return finishAndClear(formatDto());
        }
        if (data.equals(Buttons.NO_CALLBACK)){
            CREATE_CASE.set(12);
            afterGameDto.setGrenades(0);
            afterGameDto.setFlashM(0);
            afterGameDto.setFlashL(0);
            afterGameDto.setSmokeS(0);
            afterGameDto.setSmokeM(0);
            afterGameDto.setSmokeL(0);
        }
        switch (CREATE_CASE.getAndIncrement()) {
            case 0: {
                afterGameDto = new AfterGameDto();
                startAndInit(EventType.GAMES_AFTER);
                return sendMessage(
                        "Выберите дату",
                        MainMenuKeyboard.calendar(LocalDate.now()));
            }
            case 1: {
                afterGameDto.setDate(data);
                addMsgDelete();
                return editMessage(
                        "Выберите время начала",
                        GamesKeyboard.gameTime());
            }
            case 2: {
                addMsgDelete();
                LocalTime startTime = getTime(data);
                afterGameDto.setStartTime(startTime);
                return editMessage(
                        "Выберите тип игры",
                        GamesKeyboard.gameType());
            }
            case 3: {
                addMsgDelete();
                afterGameDto.setGameType(data);
                return editMessage(
                        "Укажите количество игроков",
                        GamesKeyboard.gamePeople());
            }
            case 4: {
                addMsgDelete();
                afterGameDto.setPeople(Integer.valueOf(data));
                return editMessage(
                        "Укажите количество купленых шаров",
                        GamesKeyboard.gameBalls());
            }
            case 5: {
                addMsgDelete();
                afterGameDto.setBalls(Integer.valueOf(data));
                return editMessage(
                        "Докупались: гранаты, флешки, дымы?",
                        GamesKeyboard.gamesYesNo());
            }
            case 6: {
                addMsgDelete();
                return editMessage(
                        "Укажите количество купленых гранат",
                        GamesKeyboard.gameConsumables());
            }
            case 7: {
                addMsgDelete();
                afterGameDto.setGrenades(Integer.valueOf(data));
                return editMessage(
                        "Укажите количество купленых флешек средних",
                        GamesKeyboard.gameConsumables());
            }
            case 8: {
                addMsgDelete();
                afterGameDto.setFlashM(Integer.valueOf(data));
                return editMessage(
                        "Укажите количество купленых флешек большых",
                        GamesKeyboard.gameConsumables());
            }
            case 9: {
                addMsgDelete();
                afterGameDto.setFlashL(Integer.valueOf(data));
                return editMessage(
                        "Укажите количество купленых дымов маленьких",
                        GamesKeyboard.gameConsumables());
            }
            case 10: {
                addMsgDelete();
                afterGameDto.setSmokeS(Integer.valueOf(data));
                return editMessage(
                        "Укажите количество купленых дымов средних",
                        GamesKeyboard.gameConsumables());
            }
            case 11: {
                addMsgDelete();
                afterGameDto.setSmokeM(Integer.valueOf(data));
                return editMessage(
                        "Укажите количество купленых дымов больших",
                        GamesKeyboard.gameConsumables());
            }
            case 12: {
                addMsgDelete();
                if (!data.equals(Buttons.NO_CALLBACK)) {
                    afterGameDto.setSmokeL(Integer.valueOf(data));
                }
                return editMessage(
                        "Укажите сколько часов сидели на беседках",
                        GamesKeyboard.gameConsumables());
            }
            case 13: {
                addMsgDelete();
                afterGameDto.setGazebo(Integer.valueOf(data));
                return sendMessage(
                        "Укажите сумму за поломки",
                        GamesKeyboard.gameEmpty());
            }
            case 14: {
                if (!data.equals(" ")) {
                    addMsgDelete();
                    afterGameDto.setRepair(Integer.valueOf(data));
                } else {
                    afterGameDto.setRepair(0);
                }
                addMsgDelete();
                return sendSaveMessage(formatDto());
            }
        }
        return null;
    }



    private String formatDto() {
        return  "<b>Дата</b>: " + afterGameDto.getDate() + "\n" +
                "<b>Время начала</b>: " + afterGameDto.getStartTime() + "\n" +
                "<b>Тип игры</b>: " + afterGameDto.getGameType() + "\n" +
                "<b>Количество игроков</b>: " + afterGameDto.getPeople() + "\n" +
                "<b>Куплено шаров</b>: " + afterGameDto.getBalls() + "\n" +
                "<b>Куплено гранат</b>: " + afterGameDto.getGrenades() + "\n" +
                "<b>Куплено средних флешек</b>: " + afterGameDto.getFlashM() + "\n" +
                "<b>Куплено больших флешек</b>: " + afterGameDto.getFlashL() + "\n" +
                "<b>Куплено маленьких дымов</b>: " + afterGameDto.getSmokeS() + "\n" +
                "<b>Куплено средних дымов</b>: " + afterGameDto.getSmokeM() + "\n" +
                "<b>Куплено больших дымов</b>: " + afterGameDto.getSmokeL() + "\n" +
                "<b>Часов на беседке</b>: " + afterGameDto.getGazebo() + "\n" +
                "<b>Дополнительно</b>: " + afterGameDto.getRepair() + "\n";
    }
}
