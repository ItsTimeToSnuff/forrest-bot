package com.itstimetosnuff.forrest.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class DefaultSchedulerService implements SchedulerService {

    @Override
    public SendMessage createEvent(Update update) {

        return null;
    }

    @Override
    public SendMessage fillEventStatistic(Update update) {
        return null;
    }
}
