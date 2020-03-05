package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticsDto {

    private String period;

    private String netIncome;

    private String revenue;

    private String earnMoney;

    private String spendMoney;

    private String totalGames;

    private String pokuponGames;

    private String usualGame;

    private String totalPeople;

    private String gazeboTimes;

    private String spendBalls;

    private String spendGrenades;

    private String spendFlashS;

    private String spendFlashM;

    private String spendSmokeL;

    private String spendNapkins;

    private String spendClean;
}
