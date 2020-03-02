package com.itstimetosnuff.forrest.bot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CashbookDebitDto {

    private Integer amount;

    private String description;
}
