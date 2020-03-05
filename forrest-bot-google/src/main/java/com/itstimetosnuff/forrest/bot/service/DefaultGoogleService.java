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

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class DefaultGoogleService implements GoogleService {

    private static final String LOCATION = "For.Rest, вулиця Кургузова, 11, Вишгород, Київська обл., Украина, 07301";
    private static final String ZERO_VALUE = "0";
    private static final String MISSING_COLUMN ="-";
    private final GoogleConfiguration googleConfiguration;

    @Override
    public void gameCreateEvent(CreateGameDto createGameDto) {
        try {
            String summary = createGameDto.getGameType() + " " + createGameDto.getPeople() + " человек";
            String description = createGameDto.getDescription() + "\n" + createGameDto.getPhone();
            Date startDate = Date.from(
                    ZonedDateTime.of(
                            LocalDateTime.of(
                                    createGameDto.getDate(),
                                    createGameDto.getStartTime()),
                            ZoneId.of("Europe/Kiev")).toInstant()
            );
            Date endDate = Date.from(
                    ZonedDateTime.of(
                            LocalDateTime.of(
                                    createGameDto.getDate(),
                                    createGameDto.getEndTime()),
                            ZoneId.of("Europe/Kiev")).toInstant()
            );
            EventDateTime startTime = new EventDateTime()
                    .setDateTime(new DateTime(startDate)).setTimeZone("Europe/Kiev");
            EventDateTime endTime = new EventDateTime()
                    .setDateTime(new DateTime(endDate)).setTimeZone("Europe/Kiev");
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
            googleConfiguration
                    .getCalendarService()
                    .events()
                    .insert(googleConfiguration.calendarId, event)
                    .execute();

            String range = String.format("'%s Прайс/Записи'!Z2", Year.now().getValue());
            ValueRange data = new ValueRange();
            data
                    .setValues(Collections.singletonList(Collections.singletonList(createGameDto.getPhone())))
                    .setMajorDimension("COLUMNS");
            googleConfiguration
                    .getSheetsService()
                    .spreadsheets()
                    .values()
                    .append(googleConfiguration.spreadsheetsId, range, data)
                    .setValueInputOption("USER_ENTERED")
                    .execute();
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void gameRecordAfter(AfterGameDto afterGameDto) {
        String range = "'Записи после игр'!A:L";
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
                                afterGameDto.getRepair()
                        )
                )
        );
        appendRaw(range, data);
    }

    @Override
    public void warehouseWriteCredit(WarehouseDto warehouseDto) {
        String range = "'Склад'!A:P";
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
        appendRaw(range, data);
    }

    @Override
    public void warehouseWriteDebit(WarehouseDto warehouseDto) {
        String range = "'Склад'!A:P";
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
        appendRaw(range, data);
    }

    @Override
    public WarehouseDto warehouseGetBalance() {
        List<String> ranges = Arrays.asList(
                "'Склад'!R3",
                "'Склад'!S3",
                "'Склад'!T3",
                "'Склад'!U3",
                "'Склад'!V3",
                "'Склад'!W3",
                "'Склад'!X3"
        );
        List<ValueRange> data = bathGet(ranges).getValueRanges();
        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setBalls(parseValues(data, 0,0));
        warehouseDto.setGrenades(parseValues(data, 1,0));
        warehouseDto.setFlashS(parseValues(data, 2,0));
        warehouseDto.setFlashM(parseValues(data, 3,0));
        warehouseDto.setSmokeL(parseValues(data, 4,0));
        warehouseDto.setNaples(parseValues(data, 5,0));
        warehouseDto.setClean(parseValues(data, 6,0));
        return warehouseDto;
    }

    @Override
    public void cashbookWriteDebit(CashbookDto cashbookDto) {
        String range = "'Касса'!A:F";
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
        appendRaw(range, data);
    }

    @Override
    public void cashbookWriteCredit(CashbookDto cashbookDto) {
        String range = "'Касса'!A:F";
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
        appendRaw(range, data);
    }

    @Override
    public String cashbookGetBalance() {
        List<String> ranges = Collections.singletonList("'Касса'!H3");
        List<ValueRange> data = bathGet(ranges).getValueRanges();
        return parseValues(data, 0,0);
    }

    @Override
    public StatisticsDto statisticsGetMonth(LocalDate date) {
        List<String> ranges = Arrays.asList(
                "'Прайс/Статистика'!F41:F52",
                "'Прайс/Статистика'!E41:E52",
                "'Прайс/Статистика'!C41:C52",
                "'Прайс/Статистика'!D41:D52",
                "'Прайс/Статистика'!B7:B18",
                "'Прайс/Статистика'!C7:C18",
                "'Прайс/Статистика'!D7:D18",
                "'Прайс/Статистика'!E7:E18",
                "'Прайс/Статистика'!F7:F18",
                "'Прайс/Статистика'!B24:B35",
                "'Прайс/Статистика'!C24:C35",
                "'Прайс/Статистика'!D24:D35",
                "'Прайс/Статистика'!E24:E35",
                "'Прайс/Статистика'!F24:F35",
                "'Прайс/Статистика'!G24:G35",
                "'Прайс/Статистика'!H24:H35"
        );
        List<ValueRange> data = bathGet(ranges).getValueRanges();
        int secondInd = date.getMonthValue()-1;
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setPeriod(date.format(DateTimeFormatter.ofPattern("MM:yyyy")));
        statisticsDto.setNetIncome(parseValues(data, 0, secondInd));
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

    @Override
    public StatisticsDto statisticsGetYear(LocalDate date) {
        List<String> ranges = Arrays.asList(
                "'Прайс/Статистика'!F53",
                "'Прайс/Статистика'!E53",
                "'Прайс/Статистика'!C53",
                "'Прайс/Статистика'!D53",
                "'Прайс/Статистика'!B19",
                "'Прайс/Статистика'!C19",
                "'Прайс/Статистика'!D19",
                "'Прайс/Статистика'!E19",
                "'Прайс/Статистика'!F19",
                "'Прайс/Статистика'!B36",
                "'Прайс/Статистика'!C36",
                "'Прайс/Статистика'!D36",
                "'Прайс/Статистика'!E36",
                "'Прайс/Статистика'!F36",
                "'Прайс/Статистика'!G36",
                "'Прайс/Статистика'!H36"
        );
        List<ValueRange> data = bathGet(ranges).getValueRanges();
        StatisticsDto statisticsDto = new StatisticsDto();
        statisticsDto.setPeriod(date.format(DateTimeFormatter.ofPattern("yyyy")));
        statisticsDto.setNetIncome(parseValues(data, 0, 0));
        statisticsDto.setRevenue(parseValues(data, 1, 0));
        statisticsDto.setEarnMoney(parseValues(data, 2, 0));
        statisticsDto.setSpendMoney(parseValues(data, 3, 0));
        statisticsDto.setTotalGames(parseValues(data, 4, 0));
        statisticsDto.setPokuponGames(parseValues(data, 5, 0));
        statisticsDto.setUsualGame(parseValues(data, 6, 0));
        statisticsDto.setTotalPeople(parseValues(data, 7, 0));
        statisticsDto.setGazeboTimes(parseValues(data, 8, 0));
        statisticsDto.setSpendBalls(parseValues(data, 9, 0));
        statisticsDto.setSpendGrenades(parseValues(data, 10, 0));
        statisticsDto.setSpendFlashS(parseValues(data, 11, 0));
        statisticsDto.setSpendFlashM(parseValues(data, 12, 0));
        statisticsDto.setSpendSmokeL(parseValues(data, 13, 0));
        statisticsDto.setSpendNapkins(parseValues(data, 14, 0));
        statisticsDto.setSpendClean(parseValues(data, 15, 0));
        return statisticsDto;
    }

    private void appendRaw(String range, ValueRange data) {
        try {
            data.setMajorDimension("ROWS");
            googleConfiguration
                    .getSheetsService()
                    .spreadsheets()
                    .values()
                    .append(googleConfiguration.spreadsheetsId, range, data)
                    .setValueInputOption("USER_ENTERED")
                    .execute()
            ;
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    private BatchGetValuesResponse bathGet(List<String> ranges) {
        try {
            return googleConfiguration
                    .getSheetsService()
                    .spreadsheets()
                    .values()
                    .batchGet(googleConfiguration.spreadsheetsId)
                    .setRanges(ranges)
                    .execute();

        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String parseValues(List<ValueRange> data, int firstInd, int secondInd) {
        return data.get(firstInd).getValues().get(secondInd).get(0).toString();
    }
}
