package com.itstimetosnuff.forrest.bot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GamesKeyboard extends KeyboardHelper{

    public static InlineKeyboardMarkup gameType() {
        return twoButtons("Покупон", "Покупон", "Обычный", "Обычный");
    }

    public static InlineKeyboardMarkup gameTime() {
        String[] times = {"9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00", "18:00", "19:00", "20:00"};
        return format(times);
    }

    public static InlineKeyboardMarkup gamePeople() {
        String[] times = {"3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22"};
        return format(times);
    }

    public static InlineKeyboardMarkup gameEmpty() {
        return oneButton("Оставить пустым", " ");
    }

    public static InlineKeyboardMarkup gameBalls() {
        String[] balls = {
                "0", "500", "1000", "1500", "2000",
                "2500", "3000", "3500", "4000",
                "4500", "5000", "5500", "6000",
                "6500", "7000", "7500", "8000",
                "8500", "9000", "9500", "10000"};
        return format(balls);
    }

    public static InlineKeyboardMarkup gameConsumables() {
        String[] amounts = {
                "0", "1", "2", "3",
                "4", "5", "6", "7",
                "8", "9", "10", "11",
                "12", "13", "14", "15",
                "16", "17", "18", "19",
                "20", "21", "22", "23",
                "24", "25", "26", "27",
                "28", "29", "30", "31"
        };
        return format(amounts);
    }
}
