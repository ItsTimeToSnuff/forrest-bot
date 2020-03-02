package com.itstimetosnuff.forrest.bot.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Buttons {

    //main menu
    public static final String START = "/start";
    public static final String HELP = "/help";
    public static final String GAMES = "\uD83C\uDFAE Игры";
    public static final String WAREHOUSE = "\uD83C\uDFE6 Склад";
    public static final String STATISTICS = "\uD83D\uDCCA Статистика";
    public static final String CANCEL = "❌ Отмена";
    public static final String BACK = "⇐ Назад";
    public static final String ASK_FOR_HELP = "Чем могу помочь?";
    public static final String YES = "Да";
    public static final String YES_CALLBACK = "yes";
    public static final String NO = "Нет";
    public static final String NO_CALLBACK = "no";
    public static final String CALENDAR = "calendar";

    //games menu
    //reply keyboard
    public static final String GAMES_CREATE = "\uD83D\uDCC6 Новая игра";
    public static final String GAMES_AFTER = "\uD83D\uDCDD После игры";
    //inline keyboard
    public static final String CALENDAR_SCROLL_FORWARD = "Следующий";
    public static final String CALENDAR_SCROLL_FORWARD_CALLBACK = CALENDAR + "_next";
    public static final String CALENDAR_SCROLL_BACKWARD = "Предыдущий";
    public static final String CALENDAR_SCROLL_BACKWARD_CALLBACK = CALENDAR + "_prev";
    public static final String EMPTY = " ";
    public static final String SAVE = "Сохранить";
    public static final String SAVE_CALLBACK = "save";

    //warehouse menu
    public static final String WAREHOUSE_DEBIT = "\uD83D\uDED2 Пополнение";
    public static final String WAREHOUSE_CREDIT = "\uD83D\uDCE4 Расход";
    public static final String WAREHOUSE_BALANCE = "\uD83C\uDFE6 Баланс";
    //statistics menu
    public static final String STATISTICS_MONTH = "\uD83D\uDCD7 За месяц";
    public static final String STATISTICS_YEAR = "\uD83D\uDCDA За год";
}
