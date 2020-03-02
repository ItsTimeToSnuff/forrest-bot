package com.itstimetosnuff.forrest.bot.service;

import com.itstimetosnuff.forrest.bot.dto.CreateGameDto;

public interface SchedulerService {

    void createEvent(CreateGameDto createGameDto);

}
