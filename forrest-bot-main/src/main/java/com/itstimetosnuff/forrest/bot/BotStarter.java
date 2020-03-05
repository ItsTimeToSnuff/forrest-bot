package com.itstimetosnuff.forrest.bot;

import com.itstimetosnuff.forrest.bot.bot.ForRestBot;
import com.itstimetosnuff.forrest.bot.configuration.BotConfiguration;
import com.itstimetosnuff.forrest.bot.configuration.GoogleConfiguration;
import com.itstimetosnuff.forrest.bot.factory.DefaultSessionFactory;
import com.itstimetosnuff.forrest.bot.factory.SessionFactory;
import com.itstimetosnuff.forrest.bot.service.DefaultGoogleService;
import com.itstimetosnuff.forrest.bot.service.GoogleService;
import com.itstimetosnuff.forrest.bot.store.InMemorySessionStore;
import com.itstimetosnuff.forrest.bot.store.SessionStore;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

@Slf4j
public class BotStarter {

    public static void main(String[] args) {
        try {
            log.info("Initiating api context");
            ApiContextInitializer.init();
            log.info("Initiating bot configuration");
            BotConfiguration configuration =
                    BotConfiguration
                            .builder(args[0])
                            .withBotPath()
                            .withEnvBotToken()
                            .withBotUsername()
                            .withExternalUrl()
                            .withInternalUrl()
                            .withGoogleTokenPath()
                            .withGoogleTCredentialsPath()
                            .withGoogleAppName()
                            .withGoogleCalendarId()
                            .withGoogleSpreadsheetsId()
                            .build();
            String externalUrl = configuration.getExternalUrl();
            String internalUrl = configuration.getInternalUrl();
            log.info("Creating TelegramBotApi");
            TelegramBotsApi botsApi = new TelegramBotsApi(externalUrl, internalUrl);
            log.info("Registering bot in TelegramBotApi");
            SessionStore sessionStore = new InMemorySessionStore();
            GoogleConfiguration googleConfiguration = new GoogleConfiguration(
                    configuration.getGoogleCredentialsPath(),
                    configuration.getGoogleTokenPath(),
                    configuration.getGoogleAppName(),
                    configuration.getCalendarId(),
                    configuration.getSpreadsheetsId()
            );
            GoogleService googleService = new DefaultGoogleService(googleConfiguration);
            SessionFactory sessionFactory = new DefaultSessionFactory(sessionStore, googleService);
            botsApi.registerBot(new ForRestBot(configuration, sessionStore, sessionFactory));
            log.info("Bot successful started");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
