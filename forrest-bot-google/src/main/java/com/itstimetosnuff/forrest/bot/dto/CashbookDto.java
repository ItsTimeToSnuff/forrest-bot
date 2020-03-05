package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CashbookDto {

    private LocalDate recordDate;

    private String author;

    private String  amount;

    private String description;
}
