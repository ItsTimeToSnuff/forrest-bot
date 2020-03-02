package com.itstimetosnuff.forrest.bot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuppressWarnings("PMD.DataflowAnomalyAnalysis")
public class KeyboardHelper {

    public static InlineKeyboardMarkup oneButton(String name1, String val1) {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText(name1);
        button.setCallbackData(val1);
        buttons.add(button);
        keyboard.add(buttons);

        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static ReplyKeyboardMarkup oneButton(String name) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add(name);
        keyboard.add(row);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static InlineKeyboardMarkup twoButtons(String name1, String val1, String name2, String val2) {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();

        button.setText(name1);
        button.setCallbackData(val1);
        buttons.add(button);
        button = new InlineKeyboardButton();
        button.setText(name2);
        button.setCallbackData(val2);
        buttons.add(button);
        keyboard.add(buttons);

        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static ReplyKeyboardMarkup threeButton(String name1, String name2, String name3) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add(name1);
        row.add(name2);
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(name3);
        keyboard.add(row);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static ReplyKeyboardMarkup fourButton(String name1, String name2, String name3, String name4) {

        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        row.add(name1);
        row.add(name2);
        keyboard.add(row);
        row = new KeyboardRow();
        row.add(name3);
        row.add(name4);
        keyboard.add(row);
        keyboardMarkup.setResizeKeyboard(true);

        return keyboardMarkup.setKeyboard(keyboard);
    }

    public static InlineKeyboardMarkup format(String [] values) {

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        InlineKeyboardButton button = new InlineKeyboardButton();

        int i = 0;
        while (i < values.length) {
            for (int j = 0; j < 5 && i < values.length; j++, i++) {
                button.setText(values[i]);
                button.setCallbackData(values[i]);
                buttons.add(button);
                button = new InlineKeyboardButton();
            }
            keyboard.add(buttons);
            buttons = new ArrayList<>();
        }
        return keyboardMarkup.setKeyboard(keyboard);
    }
}
