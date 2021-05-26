package com.itstimetosnuff.forrest.bot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Arrays;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WarehouseKeyboard extends BaseKeyboardHelper {

    public static InlineKeyboardMarkup balls() {
        List<String> balls = Arrays.asList(
                "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9",
                "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19",
                "20"
        );
        return createInlineKeyboard(balls, null, 5);
    }

    public static InlineKeyboardMarkup naplesClean() {
        List<String> naplesClean = Arrays.asList(
                "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9",
                "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19",
                "20"
        );
        return createInlineKeyboard(naplesClean, null, 5);
    }

    public static InlineKeyboardMarkup grenades() {
        List<String> grenades = Arrays.asList(
                "0", "10", "20", "30", "40",
                "50", "60", "70", "80", "90",
                "100"
        );
        return createInlineKeyboard(grenades, null, 4);
    }
}
