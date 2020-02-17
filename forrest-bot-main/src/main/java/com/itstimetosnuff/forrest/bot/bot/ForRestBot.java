package com.itstimetosnuff.forrest.bot.bot;

import com.itstimetosnuff.forrest.bot.configuration.BotConfiguration;
import com.itstimetosnuff.forrest.bot.request.Request;
import com.itstimetosnuff.forrest.bot.request.RequestUtils;
import com.itstimetosnuff.forrest.bot.session.DefaultSessionFactory;
import com.itstimetosnuff.forrest.bot.session.InMemorySessionStore;
import com.itstimetosnuff.forrest.bot.session.Session;
import com.itstimetosnuff.forrest.bot.session.SessionFactory;
import com.itstimetosnuff.forrest.bot.session.SessionStore;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
public class ForRestBot extends TelegramWebhookBot {

    private final transient BotConfiguration configuration;
    private final transient SessionStore sessionStore;
    private final transient SessionFactory sessionFactory;

    public ForRestBot(BotConfiguration configuration) {
        this.configuration = configuration;
        sessionStore = new InMemorySessionStore();
        sessionFactory = new DefaultSessionFactory(sessionStore);
    }

    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        synchronized (this) {
            Long chatId = update.getMessage().getChatId();
            log.info(chatId.toString());
            Session session = sessionStore.findSession(chatId).orElseGet(() -> sessionFactory.createSession(chatId));
            log.info(session.toString());
            Request request = RequestUtils.create(update);
            return session.onCommand(request);
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
