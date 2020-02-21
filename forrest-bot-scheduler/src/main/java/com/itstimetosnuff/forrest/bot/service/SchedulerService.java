package com.itstimetosnuff.forrest.bot.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface SchedulerService {

    SendMessage createEvent(Update update);

    SendMessage fillEventStatistic(Update update);
}
