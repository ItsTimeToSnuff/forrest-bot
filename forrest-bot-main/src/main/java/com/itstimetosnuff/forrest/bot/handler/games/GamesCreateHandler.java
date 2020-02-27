package com.itstimetosnuff.forrest.bot.handler.games;

import com.itstimetosnuff.forrest.bot.dto.GameDto;
import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.handler.Handler;
import com.itstimetosnuff.forrest.bot.utils.Buttons;
import com.itstimetosnuff.forrest.bot.utils.GameKeyboard;
import com.itstimetosnuff.forrest.bot.utils.MainMenuKeyboard;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.utils.MethodHelper;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class GamesCreateHandler implements Handler {

    private transient int createCase;
    private transient GameDto gameCreate;
    private transient List<Integer> msgIdDelete;

    public GamesCreateHandler(int createCase) {
        this.createCase = createCase;
    }

    @Override
    public BotApiMethod handleEvent(Update update, Session session) {

        String data;
        Integer msgId;
        Long chatId;

        //initiate variables
        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();
            data = update.getCallbackQuery().getData();
            msgId = update.getCallbackQuery().getMessage().getMessageId();
        } else {
            chatId = update.getMessage().getChatId();
            data = update.getMessage().getText();
            msgId = update.getMessage().getMessageId();
        }

        //check for cancel
        if (data.contains(Buttons.CANCEL)) {
            session.setEventLock(EventType.LOCK_FREE);
            msgIdDelete.forEach(msg -> session.getExecutes().add(MethodHelper.deleteMessage(chatId, msg)));
            createCase = 0;
            return MethodHelper.backMainMenu(chatId);
        }

        //check for calendar scrolling
        if (data.contains(Buttons.CALENDAR_SCROLL_FORWARD_CALLBACK) || data.contains(Buttons.CALENDAR_SCROLL_BACKWARD_CALLBACK)) {
            int year = LocalDate.now().getYear();
            int month = Integer.parseInt(data.substring(data.lastIndexOf(":") + 1));
            if (data.contains(Buttons.CALENDAR_SCROLL_FORWARD_CALLBACK)) {
                month += 1;
            } else {
                month -= 1;
            }
            return MethodHelper.editMessage(
                    chatId,
                    msgId,
                    "Укажите дату",
                    GameKeyboard.gameDate(LocalDate.of(year, month, 1)));
        }

        //main workflow
        switch (createCase) {
            case 0: {
                gameCreate = new GameDto();
                msgIdDelete = new ArrayList<>();
                session.setEventLock(EventType.GAMES_CREATE);
                session.getExecutes().add(MethodHelper.addCancelButton(chatId));
                createCase += 1;
                msgIdDelete.add(msgId + 2);
                return MethodHelper.sendMessage(
                        chatId,
                        "Выберите тип игры",
                        GameKeyboard.gameType());
            }
            case 1: {
                gameCreate.setGameType(data);
                createCase += 1;
                return MethodHelper.editMessage(
                        chatId,
                        msgId,
                        "Укажите дату",
                        GameKeyboard.gameDate(LocalDate.now()));
            }
            case 2: {
                gameCreate.setDate(data);
                createCase += 1;
                return MethodHelper.editMessage(
                        chatId,
                        msgId,
                        "Укажите время начала",
                        GameKeyboard.gameTime());
            }
            case 3: {
                int lastIndex = data.lastIndexOf(":");
                int strHours = Integer.parseInt(data.substring(0, lastIndex));
                int strMinutes = Integer.parseInt(data.substring(lastIndex + 1));
                LocalTime startTime = LocalTime.of(strHours, strMinutes);
                gameCreate.setStartTime(startTime);
                gameCreate.setEndTime(startTime.plusHours(2));
                createCase += 1;
                return MethodHelper.editMessage(
                        chatId,
                        msgId,
                        "Укажите количество игроков",
                        GameKeyboard.gamePeople());
            }
            case 4: {
                gameCreate.setPeople(Long.valueOf(data));
                createCase += 1;
                return MethodHelper.editMessage(
                        chatId,
                        msgId,
                        "Укажите номер телефона с +380",
                        null);
            }
            case 5: {
                gameCreate.setPhone(data);
                createCase += 1;
                msgIdDelete.add(msgId);
                msgIdDelete.add(msgId + 1);
                return MethodHelper.sendMessage(
                        chatId,
                        "Укажите дополнительное описание",
                        GameKeyboard.gameEmpty());
            }
            case 6: {
                if (data.contains(Buttons.SAVE_CALLBACK)) {
                    session.setEventLock(EventType.LOCK_FREE);
                    msgIdDelete.forEach(msg -> session.getExecutes().add(MethodHelper.deleteMessage(chatId, msg)));
                    createCase = 0;
                    return MethodHelper.sendMessage(
                            chatId,
                            "✅ Запись успешно создана:\n" + formatDto(),
                            MainMenuKeyboard.mainMenu());
                }
                gameCreate.setDescription(data);
                if (!data.equals(" ")) {
                    msgIdDelete.add(msgId);
                }
                msgIdDelete.add(msgId + 1);
                return MethodHelper.sendMessage(
                        chatId,
                        "Сохранить запись?\n" + formatDto(),
                        GameKeyboard.gameSave());
            }
        }
        return null;
    }

    private String formatDto() {
        return "<b>Тип игры</b>: " + gameCreate.getGameType() + "\n" +
                "<b>Дата</b>: " + gameCreate.getDate() + "\n" +
                "<b>Время</b>: " + gameCreate.getStartTime() + " - " + gameCreate.getEndTime() + "\n" +
                "<b>Количество игроков</b>: " + gameCreate.getPeople() + "\n" +
                "<b>Номер телефона</b>: " + gameCreate.getPhone() + "\n" +
                "<b>Дополнительно</b>: " + gameCreate.getDescription() + "\n";
    }
}
