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
public abstract class BaseKeyboardHelper {

    protected static ReplyKeyboardMarkup createReplyKeyboard(List<String> buttons, int rowElements) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();

        int rowCount = (buttons.size() + rowElements - 1) / rowElements;
        for (int i = 0; i < rowCount; i++) {
            KeyboardRow row = new KeyboardRow();
            row.addAll(buttons.subList(i * rowElements, Math.min(i * rowElements + rowElements, buttons.size())));
            keyboard.add(row);
        }

        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        return keyboardMarkup;
    }

    protected static InlineKeyboardMarkup createInlineKeyboard(List<String> texts, List<String> values, int rowElements) {
        if (values == null) {
            values = texts;
        }

        if (texts.size() != values.size()) {
            throw new IllegalArgumentException("Values and texts length must be equal!");
        }

        InlineKeyboardMarkup keyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        int rowCount = (texts.size() + rowElements - 1) / rowElements;
        for (int i = 0; i < rowCount; i++) {
            List<InlineKeyboardButton> row = new ArrayList<>();
            for (int j = 0; j < rowElements; j++) {
                int n = i * rowElements + j;
                if (n >= texts.size()) {
                    break;
                }
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText(texts.get(n));
                button.setCallbackData(values.get(n));
                row.add(button);
            }
            keyboard.add(row);
        }

        keyboardMarkup.setKeyboard(keyboard);
        return keyboardMarkup;
    }

}
