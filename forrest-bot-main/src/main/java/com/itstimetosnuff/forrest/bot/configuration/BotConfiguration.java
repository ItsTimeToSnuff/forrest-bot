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
            String botUsername = properties.getProperty("bot.username");
            if (botUsername.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.username' should not be empty");
            }
            botConfiguration.botUsername = botUsername;
            return this;
        }

        public Builder withEnvBotToken() {
            String name = properties.getProperty("bot.token.name");
            if (name.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.token.name' should not be empty");
            }
            botConfiguration.botToken = System.getenv(name);
            return this;
        }

        public Builder withBotPath() {
            String  botPath = properties.getProperty("bot.path");
            if (botPath.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.path' should not be empty");
            }
            botConfiguration.botPath = botPath;
            return this;
        }

        public Builder withExternalUrl() {
            String externalUrl = properties.getProperty("bot.externalUrl");
            if (externalUrl.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.externalUrl' should not be empty");
            }
            botConfiguration.externalUrl = externalUrl;
            return this;
        }

        public Builder withInternalUrl() {
            String portVarName = properties.getProperty("env.var.name.internalUrl.port");
            if (portVarName.isEmpty()) {
                throw new IllegalArgumentException("Property 'env.var.name.internalUrl.port' should not be empty");
            }
            String port = System.getenv(portVarName);
            if (port.isEmpty()) {
                throw new IllegalArgumentException("Property 'portVarName' should not be empty");
            }
            String internalUrl = properties.getProperty("bot.internalUrl");
            if (internalUrl.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.internalUrl' should not be empty");
            }
            botConfiguration.internalUrl = internalUrl + port;
            return this;
        }

        public Builder withGoogleTokenPath() {
            String tokenPath = properties.getProperty("google.tokenPath");
            if (tokenPath.isEmpty()) {
                throw new IllegalArgumentException("Property 'google.tokenPath' should not be empty");
            }
            botConfiguration.googleTokenPath = tokenPath;
            return this;
        }

        public Builder withGoogleTCredentialsPath() {
            String credentialPath = properties.getProperty("google.credentialsPath");
            if (credentialPath.isEmpty()) {
                throw new IllegalArgumentException("Property 'google.credentialsPath' should not be empty");
            }
            botConfiguration.googleCredentialsPath = credentialPath;
            return this;
        }

        public Builder withGoogleAppName() {
            String annName = properties.getProperty("google.appName");
            if (annName.isEmpty()) {
                throw new IllegalArgumentException("Property 'google.appName' should not be empty");
            }
            botConfiguration.googleAppName = annName;
            return this;
        }

        public Builder withGoogleCalendarId() {
            String calendarVarName = properties.getProperty("env.var.name.calendar");
            if (calendarVarName.isEmpty()) {
                throw new IllegalArgumentException("Property 'env.var.name.calendar' should not be empty");
            }
            String calendarId = System.getenv(calendarVarName);
            if (calendarId.isEmpty()) {
                throw new IllegalArgumentException("Property 'calendarId' should not be empty");
            }
            botConfiguration.calendarId = calendarId;
            return this;
        }

        public Builder withGoogleSpreadsheetsId() {
            String spreadsheetsVarName = properties.getProperty("env.var.name.spreadsheets");
            if (spreadsheetsVarName.isEmpty()) {
                throw new IllegalArgumentException("Property 'env.var.name.spreadsheets' should not be empty");
            }
            String spreadsheetId =  System.getenv(spreadsheetsVarName);
            if (spreadsheetId.isEmpty()) {
                throw new IllegalArgumentException("Property 'spreadsheetId' should not be empty");
            }
            botConfiguration.spreadsheetsId = spreadsheetId;
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
