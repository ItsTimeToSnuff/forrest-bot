package com.itstimetosnuff.forrest.bot.utils;

import com.itstimetosnuff.forrest.bot.enums.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public final class MainMenuKeyboard extends BaseKeyboardHelper {

    public static ReplyKeyboardMarkup mainMenu(Role role) {
        List<String> buttons;
        switch (role) {
            case USER: {
                buttons = Arrays.asList(Buttons.GAMES, Buttons.WAREHOUSE, Buttons.CASHBOOK);
                break;
            }
            case ADMIN: {
                buttons = Arrays.asList(Buttons.GAMES, Buttons.WAREHOUSE, Buttons.CASHBOOK, Buttons.STATISTICS);
                break;
            }
            default: {
                buttons = new ArrayList<>();
                break;
            }
        }
        return createReplyKeyboard(buttons, 2);
    }

    public static ReplyKeyboardMarkup gamesMenu() {
        List<String> buttons = Arrays.asList(Buttons.GAMES_CREATE, Buttons.GAMES_AFTER, Buttons.BACK);
        return createReplyKeyboard(buttons, 2);
    }

    public static ReplyKeyboardMarkup warehouseMenu() {
        List<String> buttons =
                Arrays.asList(Buttons.WAREHOUSE_DEBIT, Buttons.WAREHOUSE_CREDIT, Buttons.WAREHOUSE_BALANCE, Buttons.BACK);
        return createReplyKeyboard(buttons, 2);
    }

    public static ReplyKeyboardMarkup cashbookMenu(Role role) {
        List<String> buttons;
        switch (role) {
            case USER: {
                buttons = Arrays.asList(Buttons.CASHBOOK_DEBIT, Buttons.CASHBOOK_CREDIT, Buttons.BACK);
                break;
            }
            case ADMIN: {
                buttons = Arrays.asList(Buttons.CASHBOOK_DEBIT, Buttons.CASHBOOK_CREDIT,
                        Buttons.CASHBOOK_BALANCE, Buttons.BACK);
                break;
            }
            default: {
                buttons = new ArrayList<>();
                break;
            }
        }
        return createReplyKeyboard(buttons, 2);
    }

    public static ReplyKeyboardMarkup statisticsMenu(Role role) {
        List<String> buttons;
        if (role == Role.ADMIN) {
            buttons = Arrays.asList(Buttons.STATISTICS_MONTH, Buttons.STATISTICS_YEAR, Buttons.BACK);
        } else {
            buttons = Collections.singletonList(Buttons.BACK);
        }
        return createReplyKeyboard(buttons, 2);
    }

    public static ReplyKeyboardMarkup cancel() {
        List<String> buttons = Collections.singletonList(Buttons.CANCEL);
        return createReplyKeyboard(buttons, 1);
    }

    public static ReplyKeyboardMarkup back() {
        List<String> buttons = Collections.singletonList(Buttons.BACK);
        return createReplyKeyboard(buttons, 1);
    }

    public static InlineKeyboardMarkup save() {
        List<String> texts = Collections.singletonList(Buttons.SAVE);
        List<String> values = Collections.singletonList(Buttons.SAVE_CALLBACK);
        return createInlineKeyboard(texts, values, 1);
    }

    public static InlineKeyboardMarkup yesNo() {
        List<String> texts = Arrays.asList(Buttons.YES, Buttons.NO);
        List<String> values = Arrays.asList(Buttons.YES_CALLBACK, Buttons.NO_CALLBACK);
        return createInlineKeyboard(texts, values, 2);
    }

    public static InlineKeyboardMarkup empty() {
        List<String> texts = Collections.singletonList(Buttons.EMPTY_BUTTON);
        List<String> values = Collections.singletonList(Buttons.EMPTY_BUTTON_VALUE);
        return createInlineKeyboard(texts, values, 1);
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
        title.setText(date.format(DateTimeFormatter.ofPattern("MMMM yyyy")));
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
