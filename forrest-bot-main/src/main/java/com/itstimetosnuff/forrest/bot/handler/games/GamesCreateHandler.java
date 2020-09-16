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
                        "Выберите <b>тип игры</b>",
                        GamesKeyboard.gameType()
                );
            }
            case 1: {
                createGameDto.setGameType(data);
                return editMessage(
                        "Выберите <b>дату</b>",
                        MainMenuKeyboard.calendar(LocalDate.now())
                );
            }
            case 2: {
                createGameDto.setDate(LocalDate.parse(data));
                return editMessage(
                        "Выберите <b>время начала</b>",
                        GamesKeyboard.gameTime()
                );
            }
            case 3: {
                createGameDto.setStartTime(getTime(data)
                );
                return editMessage(
                        "Выберите <b>продолжительность</b>",
                        GamesKeyboard.gameDuration()
                );
            }
            case 4: {
                createGameDto.setEndTime(createGameDto.getStartTime().plusMinutes(Long.parseLong(data)));
                return editMessage(
                        "Выберите <b>количество игроков</b>",
                        GamesKeyboard.gamePeople()
                );
            }
            case 5: {
                createGameDto.setPeople(data);
                return editMessage(
                        "Напишите <b>номер телефона с +380</b>",
                        null
                );
            }
            case 6: {
                createGameDto.setPhone(data.replaceAll("\\s+", ""));
                addMsgDelete();
                return sendMessage(
                        "Напишите <b>дополнительное описание</b>",
                        MainMenuKeyboard.empty()
                );
            }
            case 7: {
                createGameDto.setDescription(data);
                if (!data.equals(Buttons.EMPTY)) {
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
                "<b>Дата</b>: " + createGameDto.getDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "\n" +
                "<b>Время</b>: " + createGameDto.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")) +
                " - " + createGameDto.getEndTime().format(DateTimeFormatter.ofPattern("HH:mm")) + "\n" +
                "<b>Количество игроков</b>: " + createGameDto.getPeople() + "\n" +
                "<b>Номер телефона</b>: " + createGameDto.getPhone() + "\n" +
                "<b>Дополнительно</b>: " + createGameDto.getDescription() + "\n";
    }
}
