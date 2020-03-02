package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
public class AfterGameDto {

    private String date;

    private LocalTime startTime;

    private String gameType;

    private Integer people;

    private Integer balls;

    private Integer grenades;

    private Integer flashM;

    private Integer flashL;

    private Integer smokeS;

    private Integer smokeM;

    private Integer smokeL;

    private Integer gazebo;

    private Integer repair;
}
