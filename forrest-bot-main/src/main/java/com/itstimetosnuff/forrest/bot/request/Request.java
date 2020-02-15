package com.itstimetosnuff.forrest.bot.request;

import com.itstimetosnuff.forrest.bot.enums.EventType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Request {
    private Integer id;
    private EventType type;
}
