package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class GameDto {

    private String description;

    private String gameType;

    private Long people;

    private LocalTime startTime;

    private LocalTime endTime;

    private String date;

    private String phone;

}
