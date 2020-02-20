package com.itstimetosnuff.forrest.bot.keyboard;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public final class MainMenu {

    public static ReplyKeyboardMarkup mainMenu(){
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Новый прокат");
        row.add("После проката");
        keyboard.add(row);
        row = new KeyboardRow();
        row.add("Склад");
        row.add("Статистика");
        keyboard.add(row);
        return keyboardMarkup.setKeyboard(keyboard);
    }
}
