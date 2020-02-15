package com.itstimetosnuff.forrest.bot.bot;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import com.itstimetosnuff.forrest.bot.hendler.Handler;
import com.itstimetosnuff.forrest.bot.hendler.HandlerRegistry;
import com.itstimetosnuff.forrest.bot.hendler.StartHandler;
import com.itstimetosnuff.forrest.bot.keyboard.MainMenu;
import com.itstimetosnuff.forrest.bot.request.Request;
import com.itstimetosnuff.forrest.bot.request.StartRequest;
import com.itstimetosnuff.forrest.bot.session.DefaultSession;
import com.itstimetosnuff.forrest.bot.session.InMemorySessionStore;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.session.SessionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

//@Component
@Slf4j
public class ForRestBot extends TelegramWebhookBot {

    //private final BotConfiguration configuration;

    private final transient HandlerRegistry handlerRegistry;
    private final transient SessionStore sessionStore;

    public ForRestBot() {
        sessionStore = new InMemorySessionStore();
        handlerRegistry = new HandlerRegistry();
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        Long chatId = update.getMessage().getChatId();
        log.error(chatId.toString());
        try {
            Session session = sessionStore.findSession(chatId).orElse(new DefaultSession(chatId, handlerRegistry));
            sessionStore.registerSession(session);
            Message message = update.getMessage();
            String text = message.getText();
            handlerRegistry.register(EventType.byCommand(text), new StartHandler(sessionStore, session));
            Integer messageId = message.getMessageId();
            Request startRequest = new StartRequest(messageId, update);
            return session.onMessage(startRequest);
        }catch (Exception e) {
            log.error(e.getMessage(), e);
            SendMessage unknownCommand = new SendMessage();
            unknownCommand.setChatId(chatId);
            unknownCommand.setText("Извини, ничем не могу помочь, произошла ошибка: " + e.getMessage());
            return unknownCommand;
        }
    }

    @Override
    public String getBotUsername() {
        return "test";//configuration.getBotName();
    }

    @Override
    public String getBotToken() {
        return null;//configuration.botToken;
    }

    @Override
    public String getBotPath() {
        return "test";//configuration.getWebAppUrl();
    }
}
