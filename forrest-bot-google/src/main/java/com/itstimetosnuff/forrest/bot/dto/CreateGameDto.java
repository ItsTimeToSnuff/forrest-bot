package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class CreateGameDto {

    private String description;

    private String gameType;

    private String people;

    private LocalTime startTime;

    private LocalTime endTime;

    private LocalDate date;

    private String phone;

}
