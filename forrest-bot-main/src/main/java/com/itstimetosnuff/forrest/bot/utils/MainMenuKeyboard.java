package com.itstimetosnuff.forrest.bot.utils;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public final class MainMenuKeyboard {

    public static ReplyKeyboardMarkup mainMenu(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add(Buttons.GAMES);
        row.add(Buttons.WAREHOUSE);
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(Buttons.STATISTICS);
        keyboard.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static ReplyKeyboardMarkup gamesMenu(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add(Buttons.GAMES_CREATE);
        row.add(Buttons.GAMES_AFTER);
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(Buttons.BACK);
        keyboard.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static ReplyKeyboardMarkup warehouseMenu(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add(Buttons.WAREHOUSE_DEBIT);
        row.add(Buttons.WAREHOUSE_CREDIT);
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(Buttons.WAREHOUSE_BALANCE);
        row.add(Buttons.BACK);
        keyboard.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static ReplyKeyboardMarkup statisticsMenu(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add(Buttons.STATISTICS_MONTH);
        row.add(Buttons.STATISTICS_YEAR);
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(Buttons.BACK);
        keyboard.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static ReplyKeyboardMarkup cancel(){

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add(Buttons.CANCEL);
        keyboard.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup.setKeyboard(keyboard);
    }
}
