package com.itstimetosnuff.forrest.bot.enums;

import com.itstimetosnuff.forrest.bot.utils.Buttons;

public enum EventType {
    LOCK_FREE("lockFree"),
    ERROR("error"),
    START(Buttons.START),
    BACK(Buttons.BACK),
    CANCEL(Buttons.CANCEL),
    GAMES(Buttons.GAMES),
    GAMES_CREATE(Buttons.GAMES_CREATE),
    GAMES_AFTER(Buttons.GAMES_AFTER),
    WAREHOUSE(Buttons.WAREHOUSE),
    WAREHOUSE_BALANCE(Buttons.WAREHOUSE_BALANCE),
    WAREHOUSE_CREDIT(Buttons.WAREHOUSE_CREDIT),
    WAREHOUSE_DEBIT(Buttons.WAREHOUSE_DEBIT),
    CASHBOOK(Buttons.CASHBOOK),
    CASHBOOK_DEBIT(Buttons.CASHBOOK_DEBIT),
    CASHBOOK_CREDIT(Buttons.CASHBOOK_CREDIT),
    CASHBOOK_BALANCE(Buttons.CASHBOOK_BALANCE),
    STATISTICS(Buttons.STATISTICS),
    STATISTICS_MONTH(Buttons.STATISTICS_MONTH),
    STATISTICS_YEAR(Buttons.STATISTICS_YEAR),
    CALENDAR(Buttons.CALENDAR);

    private final String type;

    EventType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }

    public static EventType byType(String type) {
        for (EventType t : values()) {
            if (type.contains(t.type)){
                return t;
            }
        }
        return ERROR;
    }
}
