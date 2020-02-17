package com.itstimetosnuff.forrest.bot;

import com.itstimetosnuff.forrest.bot.bot.ForRestBot;
import com.itstimetosnuff.forrest.bot.configuration.BotConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

@Slf4j
public class BotStarter {

    public static void main(String[] args) {
        log.info("Initiating api context");
        ApiContextInitializer.init();
        log.info("Initiating bot configuration");
        BotConfiguration configuration = new BotConfiguration();
        try {
            String externalUrl = configuration.getExternalUrl();
            String internalUrl = configuration.getInternalUrl();
            log.info("Creating TelegramBotApi");
            TelegramBotsApi botsApi = new TelegramBotsApi(externalUrl, internalUrl);
            log.info("Registering bot in TelegramBotApi");
            botsApi.registerBot(new ForRestBot(configuration));
            log.info("Bot successful started");
        } catch (TelegramApiRequestException e) {
            log.error(e.getMessage(), e);
        }
    }
}
