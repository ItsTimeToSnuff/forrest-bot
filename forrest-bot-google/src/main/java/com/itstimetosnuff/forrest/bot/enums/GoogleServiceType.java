package com.itstimetosnuff.forrest.bot.enums;

public enum GoogleServiceType {

    CASHBOOK_SERVICE("cashbook"),
    GAMES_SERVICE("games"),
    STATISTICS_SERVICE("statistics"),
    WAREHOUSE_SERVICE("warehouse");

    private String type;

    GoogleServiceType(String type) {
        this.type = type;
    }

    public String getValue() {
        return type;
    }

    public static GoogleServiceType byType(String type) {
        for (GoogleServiceType t : values()) {
            if (type.contains(t.type)){
                return t;
            }
        }
        throw new IllegalArgumentException("Type not found");
    }
}
