package com.itstimetosnuff.forrest.bot;

import com.itstimetosnuff.forrest.bot.bot.ForRestBot;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
//import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class BotStarter {

//    public static void disableWarning() {
//        try {
//            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
//            theUnsafe.setAccessible(true);
//            Unsafe u = (Unsafe) theUnsafe.get(null);
//
//            Class cls = Class.forName("jdk.internal.module.IllegalAccessLogger");
//            Field logger = cls.getDeclaredField("logger");
//            u.putObjectVolatile(cls, u.staticFieldOffset(logger), null);
//        } catch (Exception e) {
//            // ignore
//        }
//    }

    public static void main(String[] args) {
        //disableWarning();
        //System.setErr();
        //Logger.getRootLogger().setLevel(Level.OFF);
        log.info("Initiate api context");
        ApiContextInitializer.init();
        try {
            log.info("Create TelegramBotApi");
            TelegramBotsApi botsApi = new TelegramBotsApi("https://f9219cb3.ngrok.io", "http://localhost:80");
            botsApi.registerBot(new ForRestBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
//        try {
//            log.info("Register ForRestBot");
//            //botsApi.registerBot(new ForRestBot());
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
        //SpringApplication.run(BotStarter.class, args);
    }
}
