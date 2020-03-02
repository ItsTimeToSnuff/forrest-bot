package com.itstimetosnuff.forrest.bot.bot;

import com.itstimetosnuff.forrest.bot.configuration.BotConfiguration;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.factory.SessionFactory;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ForRestBot extends TelegramWebhookBot {

    private final transient BotConfiguration configuration;
    private final transient SessionStore sessionStore;
    private final transient SessionFactory sessionFactory;

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        try {
            Long chatId;
            if (update.hasCallbackQuery()) {
                chatId = update.getCallbackQuery().getMessage().getChatId();
            } else {
                chatId = update.getMessage().getChatId();
            }
            log.info(chatId.toString());
            Session session = sessionStore.findSession(chatId).orElseGet(() -> sessionFactory.createSession(chatId));
            log.info(session.toString());
            BotApiMethod onUpdate = session.onUpdate(update);
            List<BotApiMethod> executes = session.getExecutes();
            if (!executes.isEmpty()) {
                for (BotApiMethod method : executes) {
                    log.info(method.toString());
                    execute(method);
                }
                session.getExecutes().clear();
            }
            log.info(onUpdate.toString());
            return onUpdate;
        } catch (Exception e) {
            log.info(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String getBotUsername() {
        return configuration.getBotUsername();
    }

    @Override
    public String getBotToken() {
        return configuration.getBotToken();
    }

    @Override
    public String getBotPath() {
        return configuration.getBotPath();
    }
}
