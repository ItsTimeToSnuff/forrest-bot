package com.itstimetosnuff.forrest.bot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.itstimetosnuff.forrest.bot.utils.Buttons.CALENDAR_SCROLL_BACKWARD;
import static com.itstimetosnuff.forrest.bot.utils.Buttons.CALENDAR_SCROLL_BACKWARD_CALLBACK;
import static com.itstimetosnuff.forrest.bot.utils.Buttons.CALENDAR_SCROLL_FORWARD;
import static com.itstimetosnuff.forrest.bot.utils.Buttons.CALENDAR_SCROLL_FORWARD_CALLBACK;
import static com.itstimetosnuff.forrest.bot.utils.Buttons.EMPTY;
import static com.itstimetosnuff.forrest.bot.utils.Buttons.SAVE;
import static com.itstimetosnuff.forrest.bot.utils.Buttons.SAVE_CALLBACK;

@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public final class GameKeyboard {

    public static InlineKeyboardMarkup gameType() {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText("Покупон");
        button.setCallbackData("Покупон");
        buttons.add(button);
        button = new InlineKeyboardButton();
        button.setText("Обычный");
        button.setCallbackData("Обычный");
        buttons.add(button);
        keyboard.add(buttons);
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static InlineKeyboardMarkup gameDate(LocalDate date) {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        date = date.withDayOfMonth(1);
        int dayOfWeek = date.getDayOfWeek().getValue();

        //create header of calendar navigation buttons |<backward|month|forward>|
        InlineKeyboardButton title = new InlineKeyboardButton();
        title.setText(CALENDAR_SCROLL_BACKWARD);
        title.setCallbackData(CALENDAR_SCROLL_BACKWARD_CALLBACK + ":" + date.getMonthValue());
        buttons.add(title);
        title = new InlineKeyboardButton();
        title.setText(date.format(DateTimeFormatter.ofPattern("MMMM YYYY")));
        title.setCallbackData(EMPTY);
        buttons.add(title);
        title = new InlineKeyboardButton();
        title.setText(CALENDAR_SCROLL_FORWARD);
        title.setCallbackData(CALENDAR_SCROLL_FORWARD_CALLBACK + ":" + date.getMonthValue());
        buttons.add(title);
        keyboard.add(buttons);

        //create header of calendar titles of days of week
        InlineKeyboardButton dayTitle = new InlineKeyboardButton();
        buttons = new ArrayList<>();
        String [] dayTitles = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд"};
        for (String t : dayTitles) {
            dayTitle.setText(t);
            dayTitle.setCallbackData(EMPTY);
            buttons.add(dayTitle);
            dayTitle = new InlineKeyboardButton();
        }
        keyboard.add(buttons);

        //filling the empty spaces in the start of calendar if present
        buttons = new ArrayList<>();
        InlineKeyboardButton day = new InlineKeyboardButton();
        for (int i = 0; i < dayOfWeek - 1; i++) {
            day.setText(EMPTY);
            day.setCallbackData(EMPTY);
            buttons.add(day);
            day = new InlineKeyboardButton();
        }

        //filling main body of calendar
        for (int i = 0, dayOfMonth = 1; dayOfMonth <= date.getMonth().length(date.isLeapYear()); i++) {
            for (int j = ((i == 0) ? dayOfWeek : 1); j <= 7 && (dayOfMonth <= date.getMonth().length(date.isLeapYear())); j++) {
                day.setText(String.valueOf(dayOfMonth));
                day.setCallbackData(String.valueOf(LocalDate.of(date.getYear(), date.getMonthValue(), dayOfMonth)));
                buttons.add(day);
                day = new InlineKeyboardButton();
                dayOfMonth++;
            }
            //filing empty spaces in the end of calendar if present
            while (buttons.size() < 7) {
                day.setText(EMPTY);
                day.setCallbackData(EMPTY);
                buttons.add(day);
                day = new InlineKeyboardButton();
            }
            keyboard.add(buttons);
            buttons = new ArrayList<>();
        }
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static InlineKeyboardMarkup gameTime() {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton time = new InlineKeyboardButton();

        String [] times = {"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"};
        int i = 0;
        while(i < times.length) {
            for (int j = 0; j < 3 && i < times.length; j++, i++) {
                time.setText(times[i]);
                time.setCallbackData(times[i]);
                buttons.add(time);
                time = new InlineKeyboardButton();
            }
            keyboard.add(buttons);
            buttons = new ArrayList<>();
        }
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static InlineKeyboardMarkup gamePeople() {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton time = new InlineKeyboardButton();

        String [] times = {"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
        int i = 0;
        while(i < times.length) {
            for (int j = 0; j < 4 && i < times.length; j++, i++) {
                time.setText(times[i]);
                time.setCallbackData(times[i]);
                buttons.add(time);
                time = new InlineKeyboardButton();
            }
            keyboard.add(buttons);
            buttons = new ArrayList<>();
        }
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static InlineKeyboardMarkup gameSave() {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText(SAVE);
        button.setCallbackData(SAVE_CALLBACK);
        buttons.add(button);
        keyboard.add(buttons);
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static InlineKeyboardMarkup gameEmpty() {

        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton empty = new InlineKeyboardButton();

        empty.setText("Оставить пустым");
        empty.setCallbackData(" ");
        buttons.add(empty);
        keyboard.add(buttons);
        return keyboardMarkup.setKeyboard(keyboard);
    }

}
