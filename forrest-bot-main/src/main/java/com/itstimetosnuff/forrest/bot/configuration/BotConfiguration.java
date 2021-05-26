package com.itstimetosnuff.forrest.bot.configuration;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

@Slf4j
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class BotConfiguration {

    private String botUsername;

    private String botToken;

    private String botPath;

    private String externalUrl;

    private String internalUrl;

    private String googleCredentialsPath;

    private String googleAppName;

    private String calendarId;

    private String spreadsheetsId;

    private List<Long> admins;

    public static Builder builder(String configFileName) {
        return new Builder(configFileName);
    }

    public static class Builder {

        private final transient BotConfiguration botConfiguration = new BotConfiguration();
        private final transient Properties properties;
        private final transient Gson gson;

        private Builder(String configFileName) {
            properties = loadProperties(configFileName);
            gson = new Gson();
        }

        public Builder withBotUsername() {
            String botUsername = properties.getProperty("bot.username");
            if (botUsername == null || botUsername.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.username' should not be empty");
            }
            botConfiguration.botUsername = botUsername;
            return this;
        }

        public Builder withEnvBotToken() {
            String name = properties.getProperty("env.var.name.botToken");
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.token.name' should not be empty");
            }
            String token = System.getenv(name);
            if (token == null || token.isEmpty()) {
                throw new IllegalArgumentException(String.format("Environmental variable '%s' should not be empty", name));
            }
            botConfiguration.botToken = token;
            return this;
        }

        public Builder withBotPath() {
            String  botPath = properties.getProperty("bot.path");
            if (botPath == null || botPath.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.path' should not be empty");
            }
            botConfiguration.botPath = botPath;
            return this;
        }

        public Builder withExternalUrl() {
            String externalUrl = properties.getProperty("bot.externalUrl");
            if (externalUrl == null || externalUrl.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.externalUrl' should not be empty");
            }
            botConfiguration.externalUrl = externalUrl;
            return this;
        }

        public Builder withInternalUrl() {
            String portVarName = properties.getProperty("env.var.name.internalUrl.port");
            if (portVarName == null || portVarName.isEmpty()) {
                throw new IllegalArgumentException("Property 'env.var.name.internalUrl.port' should not be empty");
            }
            String port = System.getenv(portVarName);
            if (port == null || port.isEmpty()) {
                throw new IllegalArgumentException(String.format("Environmental variable '%s' should not be empty", portVarName));
            }
            String internalUrl = properties.getProperty("bot.internalUrl");
            if (internalUrl == null || internalUrl.isEmpty()) {
                throw new IllegalArgumentException("Property 'bot.internalUrl' should not be empty");
            }
            botConfiguration.internalUrl = internalUrl + port;
            return this;
        }

        public Builder withGoogleTCredentialsPath() {
            String credentialPath = properties.getProperty("google.credentialsPath");
            if (credentialPath == null || credentialPath.isEmpty()) {
                throw new IllegalArgumentException("Property 'google.credentialsPath' should not be empty");
            }
            botConfiguration.googleCredentialsPath = credentialPath;
            return this;
        }

        public Builder withGoogleAppName() {
            String appName = properties.getProperty("google.appName");
            if (appName == null || appName.isEmpty()) {
                throw new IllegalArgumentException("Property 'google.appName' should not be empty");
            }
            botConfiguration.googleAppName = appName;
            return this;
        }

        public Builder withGoogleCalendarId() {
            String calendarVarName = properties.getProperty("env.var.name.calendar");
            if (calendarVarName == null ||calendarVarName.isEmpty()) {
                throw new IllegalArgumentException("Property 'env.var.name.calendar' should not be empty");
            }
            String calendarId = System.getenv(calendarVarName);
            if (calendarId == null || calendarId.isEmpty()) {
                throw new IllegalArgumentException(String.format("Environmental variable '%s' should not be empty", calendarVarName));
            }
            botConfiguration.calendarId = calendarId;
            return this;
        }

        public Builder withGoogleSpreadsheetsId() {
            String spreadsheetsVarName = properties.getProperty("env.var.name.spreadsheets");
            if (spreadsheetsVarName == null || spreadsheetsVarName.isEmpty()) {
                throw new IllegalArgumentException("Property 'env.var.name.spreadsheets' should not be empty");
            }
            String spreadsheetId =  System.getenv(spreadsheetsVarName);
            if (spreadsheetId == null || spreadsheetId.isEmpty()) {
                throw new IllegalArgumentException(String.format("Environmental variable '%s' should not be empty", spreadsheetsVarName));
            }
            botConfiguration.spreadsheetsId = spreadsheetId;
            return this;
        }

        public Builder withAdminIdList() {
            String adminsVarName = properties.getProperty("env.var.name.admins");
            if (adminsVarName == null || adminsVarName.isEmpty()) {
                throw new IllegalArgumentException("Property 'env.var.name.admins' should not be empty");
            }
            String adminIdList =  System.getenv(adminsVarName);
            if (adminIdList == null || adminIdList.isEmpty()) {
                throw new IllegalArgumentException(String.format("Environmental variable '%s' should not be empty", adminsVarName));
            }
            Type type = new TypeToken<Collection<Long>>() {}.getType();
            botConfiguration.admins = gson.fromJson(adminIdList, type);
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
