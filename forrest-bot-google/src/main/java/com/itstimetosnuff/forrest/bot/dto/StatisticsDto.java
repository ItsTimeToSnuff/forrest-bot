package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticsDto {

    private String period;

    private String earnMoney;

    private String spendMoney;

    private String spendBalls;

    private String spendGrenades;

    private String spendFlashM;

    private String spendFlashL;

    private String spendSmokeL;

    private String gazeboTimes;

    private String spendNapkins;

    private String spendClean;
}
