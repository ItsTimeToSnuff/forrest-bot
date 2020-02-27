package com.itstimetosnuff.forrest.bot.service;

import com.itstimetosnuff.forrest.bot.dto.GameDto;

public interface SchedulerService {

    void createEvent(GameDto gameDto);

}
