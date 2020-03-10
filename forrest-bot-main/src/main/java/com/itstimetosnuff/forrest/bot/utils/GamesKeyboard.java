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
        String[] times = {
                "9:00", "9:30", "10:00", "10:30", "11:00",
                "11:30", "12:00", "12:30", "13:00", "13:30",
                "14:00", "14:30", "15:00", "15:30", "16:00",
                "16:30", "17:00", "17:30", "18:00", "18:30",
                "19:00", "19:30", "20:00", "20:30", "21:00"
        };
        return format(times, null);
    }

    public static InlineKeyboardMarkup gameDuration() {
        String[] times = {
                "1:00", "1:30", "2:00", "2:30", "3:00",
                "3:30", "4:00", "5:00", "6:00", "12:00"
        };
        String[] values = {
                "60", "90", "120", "150", "180",
                "210", "240", "300", "360", "720"
        };
        return format(times, values);
    }

    public static InlineKeyboardMarkup gamePeople() {
        String[] peoples = {
                "3", "4", "5", "6", "7",
                "8", "9", "10", "11", "12",
                "13", "14", "15", "16", "17",
                "18", "19", "20", "21", "22",
                "23", "24", "25", "26", "27",
                "28", "29", "30", "31", "32"
        };
        return format(peoples, null);
    }

    public static InlineKeyboardMarkup gameBalls() {
        String[] text = {
                "0", "1 пак", "2 пак", "3 пак", "1 кор",
                "1к 1п", "1к 2п", "1к 3п", "2 кор",
                "2к 1п", "2к 2п", "2к 3п", "3 кор",
                "3к 1п", "3к 2п", "3к 3п", "4 кор",
                "3к 1п", "3к 2п", "3к 3п", "5 кор",
                "5к 1п", "5к 2п", "5к 3п", "6 кор"
        };
        String[] values = {
                "0", "0,25", "0,5", "0,75", "1",
                "1,25", "1,5", "1,75", "2",
                "2,25", "2,5", "2,75", "3",
                "3,25", "3,5", "3,75", "4",
                "4,25", "4,5", "4,75", "5",
                "5,25", "5,5", "5,75", "6"
        };
        return format(text, values);
    }

    public static InlineKeyboardMarkup gameConsumables() {
        String[] amounts = {
                "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9",
                "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24",
                "25", "26", "27", "28", "29"
        };
        return format(amounts, null);
    }

    public static InlineKeyboardMarkup gameGazebo() {
        String[] amounts = {
                "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9"
        };
        return format(amounts, null);
    }
}
