package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticsDto {

    private String period;

    private Integer earnMoney;

    private Integer spendMoney;

    private Integer spendBalls;

    private Integer spendGrenades;

    private Integer spendFlashM;

    private Integer spendFlashL;

    private Integer spendSmokeL;

    private Integer gazeboTimes;

    private Integer spendNapkins;

    private Integer spendClean;
}
