package com.itstimetosnuff.forrest.bot.handler.games;

import com.itstimetosnuff.forrest.bot.dto.CreateGameDto;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.AbsDialogHandler;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.GamesKeyboard;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class GamesCreateHandler extends AbsDialogHandler {

    private transient CreateGameDto createGameDto;

    public GamesCreateHandler(Session session) {
        super(session);
    }

    @Override
    public BotApiMethod handleEvent(Update update) {

        variablesInit(update);

        if (data.contains(Buttons.SAVE_CALLBACK)) {
            session.getGoogleService().gameCreateEvent(createGameDto);
            return finishAndClear(formatDto());
        }
        switch (CREATE_CASE.getAndIncrement()) {
            case 0: {
                createGameDto = new CreateGameDto();
                startAndInit(EventType.GAMES_CREATE);
                return sendMessage(
                        "Выберите тип игры",
                        GamesKeyboard.gameType());
            }
            case 1: {
                createGameDto.setGameType(data);
                addMsgDelete();
                return editMessage(
                        "Укажите дату",
                        MainMenuKeyboard.calendar(LocalDate.now()));
            }
            case 2: {
                createGameDto.setDate(LocalDate.parse(data));
                addMsgDelete();
                return editMessage(
                        "Укажите время начала",
                        GamesKeyboard.gameTime());
            }
            case 3: {
                LocalTime startTime = getTime(data);
                createGameDto.setStartTime(startTime);
                createGameDto.setEndTime(startTime.plusHours(2));
                addMsgDelete();
                return editMessage(
                        "Укажите количество игроков",
                        GamesKeyboard.gamePeople());
            }
            case 4: {
                createGameDto.setPeople(data);
                addMsgDelete();
                return editMessage(
                        "Укажите номер телефона с +380",
                        null);
            }
            case 5: {
                createGameDto.setPhone(data);
                addMsgDelete();
                return sendMessage(
                        "Укажите дополнительное описание",
                        GamesKeyboard.gameEmpty());
            }
            case 6: {
                createGameDto.setDescription(data);
                if (!data.equals(" ")) {
                    addMsgDelete();
                }
                addMsgDelete();
                return sendSaveMessage(formatDto());
            }
        }
        return null;
    }

    private String formatDto() {
        return "<b>Тип игры</b>: " + createGameDto.getGameType() + "\n" +
                "<b>Дата</b>: " + createGameDto.getDate() + "\n" +
                "<b>Время</b>: " + createGameDto.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) +
                " - " + createGameDto.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" +
                "<b>Количество игроков</b>: " + createGameDto.getPeople() + "\n" +
                "<b>Номер телефона</b>: " + createGameDto.getPhone() + "\n" +
                "<b>Дополнительно</b>: " + createGameDto.getDescription() + "\n";
    }
}
