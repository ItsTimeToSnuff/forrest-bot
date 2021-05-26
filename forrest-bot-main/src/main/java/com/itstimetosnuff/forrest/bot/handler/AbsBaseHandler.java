package com.itstimetosnuff.forrest.bot.handler;

import com.itstimetosnuff.forrest.bot.session.Session;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

public abstract class AbsBaseHandler implements Handler {

    protected transient String data;
    protected transient Integer msgId;
    protected transient final Long chatId;
    protected transient final Session session;

    protected AbsBaseHandler(Session session) {
        this.session = session;
        chatId = session.getUser().getChatId();
    }

    protected void variablesInit(Update update) {
        if (update.hasCallbackQuery()) {
            data = update.getCallbackQuery().getData();
            msgId = update.getCallbackQuery().getMessage().getMessageId();
        } else {
            data = update.getMessage().getText();
            msgId = update.getMessage().getMessageId();
        }
    }

    protected SendMessage sendMessage(String text, ReplyKeyboard keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        sendMessage.setParseMode("HTML");
        if (keyboard != null) {
            sendMessage.setReplyMarkup(keyboard);
        }
        return sendMessage;
    }

    protected EditMessageText editMessage(String text, InlineKeyboardMarkup keyboard) {
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

    protected DeleteMessage deleteMessage(Integer msgId) {
        DeleteMessage deleteMessage = new DeleteMessage();
        deleteMessage.setChatId(chatId);
        deleteMessage.setMessageId(msgId);
        return deleteMessage;
    }

    @Override
    public abstract BotApiMethod handleEvent(Update update);
}
