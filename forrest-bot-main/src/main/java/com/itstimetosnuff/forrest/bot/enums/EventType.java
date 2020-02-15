package com.itstimetosnuff.forrest.bot.enums;

public enum EventType {
    COMMAND_START("/start");

    private String command;

    EventType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static EventType byCommand(String command) {
        for (EventType t : values()) {
            if (t.command.equals(command)){
                return t;
            }
        }
        //TODO command not found
        throw new IllegalArgumentException("Command not found");
    }
}
