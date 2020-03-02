package com.itstimetosnuff.forrest.bot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public final class MainMenuKeyboard extends KeyboardHelper {

    public static ReplyKeyboardMarkup mainMenu() {
        return fourButton(Buttons.GAMES, Buttons.WAREHOUSE, Buttons.CASHBOOK, Buttons.STATISTICS);
    }

    public static ReplyKeyboardMarkup gamesMenu() {
        return threeButton(Buttons.GAMES_CREATE, Buttons.GAMES_AFTER, Buttons.BACK);
    }

    public static ReplyKeyboardMarkup warehouseMenu() {
        return fourButton(Buttons.WAREHOUSE_DEBIT, Buttons.WAREHOUSE_CREDIT,
                Buttons.WAREHOUSE_BALANCE, Buttons.BACK);
    }

    public static ReplyKeyboardMarkup cashbookMenu() {
        return fourButton(Buttons.CASHBOOK_DEBIT, Buttons.CASHBOOK_CREDIT,
                Buttons.CASHBOOK_BALANCE, Buttons.BACK);
    }

    public static ReplyKeyboardMarkup statisticsMenu() {
        return threeButton(Buttons.STATISTICS_MONTH, Buttons.STATISTICS_YEAR, Buttons.BACK);
    }

    public static ReplyKeyboardMarkup cancel() {
        return oneButton(Buttons.CANCEL);
    }

    public static ReplyKeyboardMarkup back() {
        return oneButton(Buttons.BACK);
    }

    public static InlineKeyboardMarkup save() {
        return oneButton(Buttons.SAVE, Buttons.SAVE_CALLBACK);
    }

    public static InlineKeyboardMarkup yesNo() {
        return twoButtons(Buttons.YES, Buttons.YES_CALLBACK, Buttons.NO, Buttons.NO_CALLBACK);
    }

    public static InlineKeyboardMarkup calendar(LocalDate date) {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();

        date = date.withDayOfMonth(1);
        int dayOfWeek = date.getDayOfWeek().getValue();

        //create header of calendar navigation buttons |<backward|month|forward>|
        InlineKeyboardButton title = new InlineKeyboardButton();
        title.setText(Buttons.CALENDAR_SCROLL_BACKWARD);
        title.setCallbackData(Buttons.CALENDAR_SCROLL_BACKWARD_CALLBACK + ":" + date.getMonthValue());
        buttons.add(title);
        title = new InlineKeyboardButton();
        title.setText(date.format(DateTimeFormatter.ofPattern("MMMM YYYY")));
        title.setCallbackData(Buttons.EMPTY);
        buttons.add(title);
        title = new InlineKeyboardButton();
        title.setText(Buttons.CALENDAR_SCROLL_FORWARD);
        title.setCallbackData(Buttons.CALENDAR_SCROLL_FORWARD_CALLBACK + ":" + date.getMonthValue());
        buttons.add(title);
        keyboard.add(buttons);

        //create header of calendar titles of days of week
        InlineKeyboardButton dayTitle = new InlineKeyboardButton();
        buttons = new ArrayList<>();
        String[] dayTitles = {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд"};
        for (String t : dayTitles) {
            dayTitle.setText(t);
            dayTitle.setCallbackData(Buttons.EMPTY);
            buttons.add(dayTitle);
            dayTitle = new InlineKeyboardButton();
        }
        keyboard.add(buttons);

        //filling the empty spaces in the start of calendar if present
        buttons = new ArrayList<>();
        InlineKeyboardButton day = new InlineKeyboardButton();
        for (int i = 0; i < dayOfWeek - 1; i++) {
            day.setText(Buttons.EMPTY);
            day.setCallbackData(Buttons.EMPTY);
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
                day.setText(Buttons.EMPTY);
                day.setCallbackData(Buttons.EMPTY);
                buttons.add(day);
                day = new InlineKeyboardButton();
            }
            keyboard.add(buttons);
            buttons = new ArrayList<>();
        }
        return keyboardMarkup.setKeyboard(keyboard);
    }
}
