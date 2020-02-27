package com.itstimetosnuff.forrest.bot.utils;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public final class MethodHelper {

    public static SendMessage sendMessage(Long chatId, String text, ReplyKeyboard keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setParseMode("HTML");
        if (keyboard != null) {
            sendMessage.setReplyMarkup(keyboard);
        }
        return sendMessage;
    }

    public static EditMessageText editMessage(Long chatId, Integer msgId, String text, InlineKeyboardMarkup keyboard) {
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setChatId(chatId);
        editMessageText.setMessageId(msgId);
        editMessageText.setParseMode("HTML");
        editMessageText.setText(text);
        if (keyboard != null) {
            editMessageText.setReplyMarkup(keyboard);
        }
        return editMessageText;
    }

    public static DeleteMessage deleteMessage(Long chatId, Integer msgId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(msgId);
        return deleteMessage;
    }

    public static SendMessage addCancelButton(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("\uD83C\uDFC1 Начинаю запись");
        sendMessage.setReplyMarkup(MainMenuKeyboard.cancel());
        return sendMessage;
    }

    public static SendMessage backMainMenu(Long chatId) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(Buttons.ASK_FOR_HELP);
        sendMessage.setReplyMarkup(MainMenuKeyboard.mainMenu());
        return sendMessage;
    }
}
