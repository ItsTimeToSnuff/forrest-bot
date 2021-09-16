package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class AfterGameDto {

    private String author;

    private LocalDate date;

    private LocalTime startTime;

    private String gameType;

    private String people;

    private String balls;

    private String grenades;

    private String grenadesPlastic;

    private String flashS;

    private String flashM;

    private String smokeS;

    private String smokeM;

    private String smokeXL;

    private String gazebo;

    private String repair;

    private String prepayment;
}
