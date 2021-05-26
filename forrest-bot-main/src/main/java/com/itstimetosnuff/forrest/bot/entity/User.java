package com.itstimetosnuff.forrest.bot.entity;

import com.itstimetosnuff.forrest.bot.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {

    private final Long chatId;

    private final Role role;

}
