package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WarehouseDto {

    private LocalDate recordDate;

    private String author;

    private String balls;

    private String grenades;

    private String grenadesPlastic;

    private String flashS;

    private String flashM;

    private String smokeS;

    private String smokeM;

    private String smokeXL;

    private String naples;

    private String clean;
}
