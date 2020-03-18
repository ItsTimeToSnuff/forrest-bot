package com.itstimetosnuff.forrest.bot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class WarehouseKeyboard extends KeyboardHelper {

    public static InlineKeyboardMarkup balls() {
        String [] balls = {
                "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9",
                "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19",
                "20"
        };
        return format(balls, null);
    }

    public static InlineKeyboardMarkup naplesClean() {
        String [] naplesClean = {
                "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9",
                "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19",
                "20"
        };
        return format(naplesClean, null);
    }

    public static InlineKeyboardMarkup grenades() {
        String [] grenades = {
                "0", "10", "20", "30", "40",
                "50", "60", "70", "80", "90",
                "100"
        };
        return format(grenades, null);
    }
}
