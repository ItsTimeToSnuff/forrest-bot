package com.itstimetosnuff.forrest.bot.configuration;

//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;

//@Configuration
//@ConfigurationProperties(prefix = "test")
//@EnableConfigurationProperties
public class BotConfiguration {

    private String webAppUrl;

    private String botName;

    public final String botToken = "";

    public String getWebAppUrl() {
        return webAppUrl;
    }

    public void setWebAppUrl(String webAppUrl) {
        this.webAppUrl = webAppUrl;
    }

    public String getBotName() {
        return botName;
    }

    public void setBotName(String botName) {
        this.botName = botName;
    }

    //    public BotConfiguration(/*@Value("${BOT_TOKEN") String botToken, */String botName, String webAppUrl) {
//        this.botToken = ;//botToken;
//        this.botName = botName;
//        this.webAppUrl = webAppUrl;
//    }
}
