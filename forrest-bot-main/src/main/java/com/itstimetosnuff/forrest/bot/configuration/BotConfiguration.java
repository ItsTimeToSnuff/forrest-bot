package com.itstimetosnuff.forrest.bot.configuration;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BotConfiguration {

    private String botUsername;

    private String botToken;

    private String botPath;

    private String externalUrl;

    private String internalUrl;

    private String googleTokenPath;

    private String googleCredentialsPath;

    private String googleAppName;

    private String calendarId;

    private String spreadsheetsId;

    public static Builder builder(String configFileName) {
        return new Builder(configFileName);
    }

    public static class Builder {

        private transient BotConfiguration botConfiguration = new BotConfiguration();
        private transient Properties properties;

        private Builder(String configFileName) {
            properties = loadProperties(configFileName);
        }

        public Builder withBotUsername() {
            botConfiguration.botUsername = properties.getProperty("bot.username");
            return this;
        }

        public Builder withEnvBotToken() {
            String name = properties.getProperty("bot.token.name");
            botConfiguration.botToken = System.getenv(name);
            return this;
        }

        public Builder withBotPath() {
            botConfiguration.botPath = properties.getProperty("bot.path");
            return this;
        }

        public Builder withExternalUrl() {
            botConfiguration.externalUrl = properties.getProperty("bot.externalUrl");
            return this;
        }

        public Builder withInternalUrl() {
            String portVarName = properties.getProperty("env.var.name.internalUrl.port");
            botConfiguration.internalUrl = properties.getProperty("bot.internalUrl") + System.getenv(portVarName);
            return this;
        }

        public Builder withGoogleTokenPath() {
            botConfiguration.googleTokenPath = properties.getProperty("google.tokenPath");
            return this;
        }

        public Builder withGoogleTCredentialsPath() {
            botConfiguration.googleCredentialsPath = properties.getProperty("google.credentialsPath");
            return this;
        }

        public Builder withGoogleAppName() {
            botConfiguration.googleAppName = properties.getProperty("google.appName");
            return this;
        }

        public Builder withGoogleCalendarId() {
            String calendarVarName = properties.getProperty("env.var.name.calendar");
            botConfiguration.calendarId = System.getenv(calendarVarName);
            return this;
        }

        public Builder withGoogleSpreadsheetsId() {
            String spreadsheetsVarName = properties.getProperty("env.var.name.spreadsheets");
            botConfiguration.spreadsheetsId = System.getenv(spreadsheetsVarName);
            return this;
        }

        public BotConfiguration build() {
            return botConfiguration;
        }

        private Properties loadProperties(String configFileName) {
            log.debug("load properties from: " + configFileName);
            try (InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(configFileName)) {
                if (inputStream != null) {
                    Properties properties = new Properties();
                    properties.load(inputStream);
                    return properties;
                } else {
                    throw new FileNotFoundException("property file '" + configFileName + "' not found in the classpath");
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                return null;
            }
        }
    }
}
