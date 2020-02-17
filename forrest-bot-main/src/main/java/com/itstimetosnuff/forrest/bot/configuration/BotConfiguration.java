package com.itstimetosnuff.forrest.bot.configuration;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Getter
@Slf4j
public class BotConfiguration {

    private String botUsername;

    private String botToken;

    private String botPath;

    private String externalUrl;

    private String internalUrl;

    public BotConfiguration() {
        Properties properties = new Properties();
        setProperties(properties);
        this.botUsername = properties.getProperty("bot.username");
        log.debug("set bot user name: " + botUsername);
        this.botToken = System.getenv("BOT_TOKEN");
        log.debug("set bot token: " + botToken);
        this.botPath = properties.getProperty("bot.path");
        log.debug("set bot path: " + botPath);
        this.externalUrl = properties.getProperty("bot.externalUrl");
        log.debug("set external url: " + externalUrl);
        this.internalUrl = properties.getProperty("bot.internalUrl");
        log.debug("set internal url: " + internalUrl);
    }

    private void setProperties(Properties properties) {
        String propFileName = "config.properties";
        log.debug("load properties from: " + propFileName);
        try(InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propFileName)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        }catch (IOException e){
            log.error(e.getMessage(), e);
        }
    }
}
