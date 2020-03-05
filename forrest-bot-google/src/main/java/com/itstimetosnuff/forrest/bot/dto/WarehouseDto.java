package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WarehouseDto {

    private LocalDate date;

    private String author;

    private String balls;

    private String grenades;

    private String flashM;

    private String flashL;

    private String smokeL;

    private String naples;

    private String clean;
}
