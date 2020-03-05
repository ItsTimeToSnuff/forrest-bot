package com.itstimetosnuff.forrest.bot.service;

import com.itstimetosnuff.forrest.bot.dto.AfterGameDto;
import com.itstimetosnuff.forrest.bot.dto.CashbookDto;
import com.itstimetosnuff.forrest.bot.dto.CreateGameDto;
import com.itstimetosnuff.forrest.bot.dto.StatisticsDto;
import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;

public interface GoogleService {

    void gameCreateEvent(CreateGameDto createGameDto);

    void gameRecordAfter(AfterGameDto afterGameDto);

    void warehouseWriteCredit(WarehouseDto warehouseDto);

    void warehouseWriteDebit(WarehouseDto warehouseDto);

    WarehouseDto warehouseGetBalance();

    void cashbookWriteDebit(CashbookDto cashbookDto);

    void cashbookWriteCredit(CashbookDto cashbookDto);

    String cashbookGetBalance();

    StatisticsDto statisticsGetMonth();

    StatisticsDto statisticsGetYear();
}
