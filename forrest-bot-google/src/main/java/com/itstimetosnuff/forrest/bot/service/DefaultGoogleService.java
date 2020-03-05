package com.itstimetosnuff.forrest.bot.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesRequest;
import com.google.api.services.sheets.v4.model.BatchUpdateValuesResponse;
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
import java.time.LocalDateTime;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class DefaultGoogleService implements GoogleService {

    private static final String LOCATION = "For.Rest, вулиця Кургузова, 11, Вишгород, Київська обл., Украина, 07301";
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
    public void gameWriteAfter(AfterGameDto afterGameDto) {

    }

    @Override
    public void warehouseWriteCredit(WarehouseDto warehouseDto) {

    }

    @Override
    public void warehouseWriteDebit(WarehouseDto warehouseDto) {

    }

    @Override
    public WarehouseDto warehouseGetBalance() {
        return null;
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
                                "0",
                                "-"
                        )
                )
        );
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
                                "0",
                                "-",
                                cashbookDto.getAmount(),
                                cashbookDto.getDescription()
                        )
                )
        );
        appendRaw(range, data);
    }

    @Override
    public String cashbookGetBalance() {
        String balanceColumn = String.format("'%s Касса'!J4", Year.now().getValue());
        List<String> ranges = Collections.singletonList(balanceColumn);
        try {
            BatchGetValuesResponse execute = googleConfiguration
                    .getSheetsService()
                    .spreadsheets()
                    .values()
                    .batchGet(googleConfiguration.spreadsheetsId)
                    .setRanges(ranges)
                    .execute();
            return execute.getValueRanges().get(0).getValues().get(0).get(0).toString();

        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public StatisticsDto statisticsGetMonth() {
        return null;
    }

    @Override
    public StatisticsDto statisticsGetYear() {
        return null;
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
}
