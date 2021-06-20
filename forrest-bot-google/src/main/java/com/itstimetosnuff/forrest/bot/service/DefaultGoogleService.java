package com.itstimetosnuff.forrest.bot.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.itstimetosnuff.forrest.bot.configuration.GoogleConfiguration;
import com.itstimetosnuff.forrest.bot.dto.AfterGameDto;
import com.itstimetosnuff.forrest.bot.dto.CashbookDto;
import com.itstimetosnuff.forrest.bot.dto.CreateGameDto;
import com.itstimetosnuff.forrest.bot.dto.StatisticsDto;
import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DefaultGoogleService implements GoogleService {

    private static final String LOCATION = "For.Rest, вулиця Кургузова, 11, Вишгород, Київська обл., Украина, 07301";
    private static final String TIMEZONE = "Europe/Kiev";
    private static final String PHONE_SHEET = "'Номера телефонов'";
    private static final String GAME_RECORDS_SHEET = "'Записи после игр'";
    private static final String WAREHOUSE_SHEET = "'Склад'";
    private static final String CASHBOOK_SHEET ="'Касса'";
    private static final String STATISTICS_SHEET = "'Прайс/Статистика'";
    private static final String ZERO_VALUE = "0";
    private static final String MISSING_COLUMN = "-";

    private final GoogleConfiguration googleConfiguration;

    @Override
    public void gameCreateEvent(CreateGameDto createGameDto) {
        String summary = createGameDto.getGameType() + " " + createGameDto.getPeople() + " человек";
        String description = createGameDto.getDescription() + "\n" + createGameDto.getPhone();
        LocalDateTime localStart = LocalDateTime.of(createGameDto.getDate(), createGameDto.getStartTime());
        LocalDateTime localEnd = LocalDateTime.of(createGameDto.getDate(), createGameDto.getEndTime());
        EventDateTime startTime = localDateToEventDate(localStart);
        EventDateTime endTime = localDateToEventDate(localEnd);
        List<EventReminder> reminderOverrides = Arrays.asList(
                new EventReminder().setMethod("popup").setMinutes(10),
                new EventReminder().setMethod("popup").setMinutes(1440)
        );
        Event.Reminders reminders = new Event.Reminders();
        reminders.setUseDefault(false).setOverrides(reminderOverrides);
        Event event = new Event();
        event.setSummary(summary);
        event.setLocation(LOCATION);
        event.setDescription(description);
        event.setStart(startTime);
        event.setEnd(endTime);
        event.setReminders(reminders);
        insertEvent(event);

        //record phone number to spreadsheets
        String range = PHONE_SHEET + "!A:B";
        ValueRange data = new ValueRange();
        data.setValues(
                Collections.singletonList(
                        Arrays.asList(
                                createGameDto.getPhone(),
                                null)));
        appendRow(range, data);
    }

    @Override
    public void gameRecordAfter(AfterGameDto afterGameDto) {
        String range = GAME_RECORDS_SHEET + "!A:M";
        ValueRange data = new ValueRange();
        data.setValues(
                Collections.singletonList(
                        Arrays.asList(
                                afterGameDto.getAuthor(),
                                afterGameDto.getDate().toString(),
                                afterGameDto.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm")),
                                afterGameDto.getGameType(),
                                afterGameDto.getPeople(),
                                afterGameDto.getBalls(),
                                afterGameDto.getGrenades(),
                                afterGameDto.getFlashS(),
                                afterGameDto.getFlashM(),
                                afterGameDto.getSmokeL(),
                                afterGameDto.getGazebo(),
                                afterGameDto.getRepair(),
                                afterGameDto.getPrepayment()
                        )
                )
        );
        appendRow(range, data);
    }

    @Override
    public void warehouseWriteCredit(WarehouseDto warehouseDto) {
        String range = WAREHOUSE_SHEET + "!A:P";
        ValueRange data = new ValueRange();
        data.setValues(
                Collections.singletonList(
                        Arrays.asList(
                                warehouseDto.getRecordDate().toString(),
                                warehouseDto.getAuthor(),
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE,
                                warehouseDto.getBalls(),
                                warehouseDto.getGrenades(),
                                warehouseDto.getFlashS(),
                                warehouseDto.getFlashM(),
                                warehouseDto.getSmokeL(),
                                warehouseDto.getNaples(),
                                warehouseDto.getClean()
                        )
                )
        );
        appendRow(range, data);
    }

    @Override
    public void warehouseWriteDebit(WarehouseDto warehouseDto) {
        String range = WAREHOUSE_SHEET + "!A:P";
        ValueRange data = new ValueRange();
        data.setValues(
                Collections.singletonList(
                        Arrays.asList(
                                warehouseDto.getRecordDate().toString(),
                                warehouseDto.getAuthor(),
                                warehouseDto.getBalls(),
                                warehouseDto.getGrenades(),
                                warehouseDto.getFlashS(),
                                warehouseDto.getFlashM(),
                                warehouseDto.getSmokeL(),
                                warehouseDto.getNaples(),
                                warehouseDto.getClean(),
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE,
                                ZERO_VALUE
                        )
                )
        );
        appendRow(range, data);
    }

    @Override
    public WarehouseDto warehouseGetBalance() {
        List<String> ranges = Arrays.asList(
                WAREHOUSE_SHEET + "!R3",
                WAREHOUSE_SHEET + "!S3",
                WAREHOUSE_SHEET + "!T3",
                WAREHOUSE_SHEET + "!U3",
                WAREHOUSE_SHEET + "!V3",
                WAREHOUSE_SHEET + "!W3",
                WAREHOUSE_SHEET + "!X3"
        );
        List<ValueRange> data = batchGet(ranges).getValueRanges();
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setBalls(parseValues(data, 0, 0));
        warehouseDto.setGrenades(parseValues(data, 1, 0));
        warehouseDto.setFlashS(parseValues(data, 2, 0));
        warehouseDto.setFlashM(parseValues(data, 3, 0));
        warehouseDto.setSmokeL(parseValues(data, 4, 0));
        warehouseDto.setNaples(parseValues(data, 5, 0));
        warehouseDto.setClean(parseValues(data, 6, 0));
        return warehouseDto;
    }

    @Override
    public void cashbookWriteDebit(CashbookDto cashbookDto) {
        String range = CASHBOOK_SHEET + "!A:F";
        ValueRange data = new ValueRange();
        data.setValues(
                Collections.singletonList(
                        Arrays.asList(
                                cashbookDto.getRecordDate().toString(),
                                cashbookDto.getAuthor(),
                                cashbookDto.getAmount(),
                                cashbookDto.getDescription(),
                                ZERO_VALUE,
                                MISSING_COLUMN
                        )
                )
        );
        appendRow(range, data);
    }

    @Override
    public void cashbookWriteCredit(CashbookDto cashbookDto) {
        String range = CASHBOOK_SHEET + "!A:F";
        ValueRange data = new ValueRange();
        data.setValues(
                Collections.singletonList(
                        Arrays.asList(
                                cashbookDto.getRecordDate().toString(),
                                cashbookDto.getAuthor(),
                                ZERO_VALUE,
                                MISSING_COLUMN,
                                cashbookDto.getAmount(),
                                cashbookDto.getDescription()
                        )
                )
        );
        appendRow(range, data);
    }

    @Override
    public String cashbookGetBalance() {
        List<String> ranges = Collections.singletonList(CASHBOOK_SHEET + "!I3");
        List<ValueRange> data = batchGet(ranges).getValueRanges();
        return parseValues(data, 0, 0);
    }

    @Override
    public StatisticsDto statisticsGetMonth(LocalDate date) {
        List<String> ranges = Arrays.asList(
                STATISTICS_SHEET + "!F41:F52",
                STATISTICS_SHEET + "!E41:E52",
                STATISTICS_SHEET + "!C41:C52",
                STATISTICS_SHEET + "!D41:D52",
                STATISTICS_SHEET + "!B7:B18",
                STATISTICS_SHEET + "!C7:C18",
                STATISTICS_SHEET + "!D7:D18",
                STATISTICS_SHEET + "!E7:E18",
                STATISTICS_SHEET + "!F7:F18",
                STATISTICS_SHEET + "!B24:B35",
                STATISTICS_SHEET + "!C24:C35",
                STATISTICS_SHEET + "!D24:D35",
                STATISTICS_SHEET + "!E24:E35",
                STATISTICS_SHEET + "!F24:F35",
                STATISTICS_SHEET + "!G24:G35",
                STATISTICS_SHEET + "!H24:H35"
        );
        List<ValueRange> data = batchGet(ranges).getValueRanges();
        int secondInd = date.getMonthValue() - 1;
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setPeriod(date.format(DateTimeFormatter.ofPattern("MM:yyyy")));
        return statisticsMapper(statisticsDto, data, secondInd);
    }

    @Override
    public StatisticsDto statisticsGetYear(LocalDate date) {
        List<String> ranges = Arrays.asList(
                STATISTICS_SHEET + "!F53",
                STATISTICS_SHEET + "!E53",
                STATISTICS_SHEET + "!C53",
                STATISTICS_SHEET + "!D53",
                STATISTICS_SHEET + "!B19",
                STATISTICS_SHEET + "!C19",
                STATISTICS_SHEET + "!D19",
                STATISTICS_SHEET + "!E19",
                STATISTICS_SHEET + "!F19",
                STATISTICS_SHEET + "!B36",
                STATISTICS_SHEET + "!C36",
                STATISTICS_SHEET + "!D36",
                STATISTICS_SHEET + "!E36",
                STATISTICS_SHEET + "!F36",
                STATISTICS_SHEET + "!G36",
                STATISTICS_SHEET + "!H36"
        );
        List<ValueRange> data = batchGet(ranges).getValueRanges();
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setPeriod(date.format(DateTimeFormatter.ofPattern("yyyy")));
        return statisticsMapper(statisticsDto, data, 0);
    }

    private StatisticsDto statisticsMapper(StatisticsDto statisticsDto,List<ValueRange> data, int secondInd) {
        statisticsDto.setNetIncome(parseValues(data, 0,secondInd));
        statisticsDto.setRevenue(parseValues(data, 1, secondInd));
        statisticsDto.setEarnMoney(parseValues(data, 2, secondInd));
        statisticsDto.setSpendMoney(parseValues(data, 3, secondInd));
        statisticsDto.setTotalGames(parseValues(data, 4, secondInd));
        statisticsDto.setPokuponGames(parseValues(data, 5, secondInd));
        statisticsDto.setUsualGame(parseValues(data, 6, secondInd));
        statisticsDto.setTotalPeople(parseValues(data, 7, secondInd));
        statisticsDto.setGazeboTimes(parseValues(data, 8, secondInd));
        statisticsDto.setSpendBalls(parseValues(data, 9, secondInd));
        statisticsDto.setSpendGrenades(parseValues(data, 10, secondInd));
        statisticsDto.setSpendFlashS(parseValues(data, 11, secondInd));
        statisticsDto.setSpendFlashM(parseValues(data, 12, secondInd));
        statisticsDto.setSpendSmokeL(parseValues(data, 13, secondInd));
        statisticsDto.setSpendNapkins(parseValues(data, 14, secondInd));
        statisticsDto.setSpendClean(parseValues(data, 15, secondInd));
        return statisticsDto;
    }

    private void insertEvent(Event event) {
        try {
            googleConfiguration
                    .getCalendarService()
                    .events()
                    .insert(googleConfiguration.getCalendarId(), event)
                    .execute();
        } catch (IOException | GeneralSecurityException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void appendRow(String range, ValueRange data) {
        try {
            data.setMajorDimension("ROWS");
            googleConfiguration
                    .getSheetsService()
                    .spreadsheets()
                    .values()
                    .append(googleConfiguration.getSpreadsheetsId(), range, data)
                    .setValueInputOption("USER_ENTERED")
                    .execute()
            ;
        } catch (IOException | GeneralSecurityException e) {
            log.error(e.getMessage(), e);
        }
    }

    private BatchGetValuesResponse batchGet(List<String> ranges) {
        try {
            return googleConfiguration
                    .getSheetsService()
                    .spreadsheets()
                    .values()
                    .batchGet(googleConfiguration.getSpreadsheetsId())
                    .setRanges(ranges)
                    .execute();

        } catch (IOException | GeneralSecurityException e) {
            log.error(e.getMessage(), e);
            throw new NullPointerException("Invalid range, nothing war found");
        }
    }

    private String parseValues(List<ValueRange> data, int firstInd, int secondInd) {
        return data.get(firstInd).getValues().get(secondInd).get(0).toString();
    }

    private EventDateTime localDateToEventDate(LocalDateTime dateTime) {
        Date date = Date.from(ZonedDateTime.of(dateTime, ZoneId.of(TIMEZONE)).toInstant());
        return new EventDateTime().setDateTime(new DateTime(date)).setTimeZone(TIMEZONE);
    }
}
