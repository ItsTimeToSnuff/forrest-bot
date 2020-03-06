package com.itstimetosnuff.forrest.bot.service;

import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.AppendValuesResponse;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.itstimetosnuff.forrest.bot.configuration.GoogleConfiguration;
import com.itstimetosnuff.forrest.bot.dto.AfterGameDto;
import com.itstimetosnuff.forrest.bot.dto.CashbookDto;
import com.itstimetosnuff.forrest.bot.dto.CreateGameDto;
import com.itstimetosnuff.forrest.bot.dto.StatisticsDto;
import com.itstimetosnuff.forrest.bot.dto.WarehouseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DefaultGoogleServiceTest {

    @Mock
    private GoogleConfiguration mockGoogleConfiguration;
    @Mock
    private Calendar mockCalendar;
    @Mock
    private Calendar.Events mockEvents;
    @Mock
    private Calendar.Events.Insert mockInsert;
    @Mock
    private Event mockEvent;
    @Mock
    private Sheets mockSheets;
    @Mock
    private Sheets.Spreadsheets mockSpreadsheets;
    @Mock
    private Sheets.Spreadsheets.Values mockValues;
    @Mock
    private Sheets.Spreadsheets.Values.Append mockAppend;
    @Mock
    private AppendValuesResponse mockAppendValuesResponse;
    @Mock
    private Sheets.Spreadsheets.Values.BatchGet mockBatchGet;
    @Mock
    private BatchGetValuesResponse mockBatchGetValuesResponse;
    @Mock
    private List<ValueRange> mockValueRanges;
    @Mock
    private ValueRange mockValueRange;
    @Mock
    private List<List<Object>> mockListListValues;
    @Mock
    private List<Object> mockListValues;
    @Mock
    private CreateGameDto mockCreateGameDto;
    @Mock
    private AfterGameDto mockAfterGameDto;
    @Mock
    private WarehouseDto mockWarehouseDto;
    @Mock
    private CashbookDto mockCashbookDto;

    @InjectMocks
    DefaultGoogleService defaultGoogleService;

    @Test
    void whenDefaultGoogleServiceGameCreateEventShouldCreateIt() throws IOException, GeneralSecurityException {
        //given
        initCreateGameDtoMock();
        initCalendarMock();
        initSpreadsheetsAppendMock();
        //when
        defaultGoogleService.gameCreateEvent(mockCreateGameDto);
        //then
        verify(mockInsert, atLeastOnce()).execute();
        verify(mockAppend, atLeastOnce()).execute();
    }

    @Test
    void wheDefaultGoogleServiceGameRecordAfterShouldRecordIt() throws IOException, GeneralSecurityException {
        //given
        initAfterGameDto();
        initSpreadsheetsAppendMock();
        //when
        defaultGoogleService.gameRecordAfter(mockAfterGameDto);
        //then
        verify(mockAppend, atLeastOnce()).execute();
    }

    @Test
    void wheDefaultGoogleServiceWarehouseWriteCreditWriteIt() throws IOException, GeneralSecurityException {
        //given
        initWarehouseDto();
        initSpreadsheetsAppendMock();
        //given
        defaultGoogleService.warehouseWriteCredit(mockWarehouseDto);
        //then
        verify(mockAppend, atLeastOnce()).execute();
    }

    @Test
    void wheDefaultGoogleServiceWarehouseWriteDebitWriteIt() throws IOException, GeneralSecurityException {
        //given
        initWarehouseDto();
        initSpreadsheetsAppendMock();
        //given
        defaultGoogleService.warehouseWriteDebit(mockWarehouseDto);
        //then
        verify(mockAppend, atLeastOnce()).execute();
    }

    @Test
    void wheDefaultGoogleServiceWarehouseGetBalanceThenReturnWarehouseDto() throws IOException, GeneralSecurityException {
        //given
        initSpreadsheetsBatchGetMock();
        initValueRanges(7,0);
        //given
        WarehouseDto warehouseDto = defaultGoogleService.warehouseGetBalance();
        //then
        assertEquals("1", warehouseDto.getBalls());
        verify(mockBatchGet, atLeastOnce()).execute();
    }

    @Test
    void wheDefaultGoogleServiceCashbookWriteDebitWriteIt() throws IOException, GeneralSecurityException {
        //given
        initCashbookDto();
        initSpreadsheetsAppendMock();
        //given
        defaultGoogleService.cashbookWriteDebit(mockCashbookDto);
        //then
        verify(mockAppend, atLeastOnce()).execute();
    }

    @Test
    void wheDefaultGoogleServiceCashbookWriteCreditWriteIt() throws IOException, GeneralSecurityException {
        //given
        initCashbookDto();
        initSpreadsheetsAppendMock();
        //given
        defaultGoogleService.cashbookWriteCredit(mockCashbookDto);
        //then
        verify(mockAppend, atLeastOnce()).execute();
    }

    @Test
    void wheDefaultGoogleServiceCashbookGetBalanceThenReturnBalance() throws IOException, GeneralSecurityException {
        //given
        initSpreadsheetsBatchGetMock();
        initValueRanges(1,0);
        //given
        String balance = defaultGoogleService.cashbookGetBalance();
        //then
        assertEquals("1", balance);
        verify(mockBatchGet, atLeastOnce()).execute();
    }

    @Test
    void wheDefaultGoogleServiceStatisticsGetMonthThenReturnStatisticsDto() throws IOException, GeneralSecurityException {
        //given
        initSpreadsheetsBatchGetMock();
        initValueRanges(16,2);
        //given
        StatisticsDto statisticsDto = defaultGoogleService.statisticsGetMonth(LocalDate.of(2020, 3, 1));
        //then
        assertEquals("1", statisticsDto.getEarnMoney());
        verify(mockBatchGet, atLeastOnce()).execute();
    }

    @Test
    void wheDefaultGoogleServiceStatisticsGetYearThenReturnStatisticsDto() throws IOException, GeneralSecurityException {
        //given
        initSpreadsheetsBatchGetMock();
        initValueRanges(16,0);
        //given
        StatisticsDto statisticsDto = defaultGoogleService.statisticsGetYear(LocalDate.of(2020, 3, 1));
        //then
        assertEquals("1", statisticsDto.getSpendSmokeL());
        verify(mockBatchGet, atLeastOnce()).execute();
    }

    private void initSpreadsheetsAppendMock() throws IOException, GeneralSecurityException {
        when(mockGoogleConfiguration.getSpreadsheetsId()).thenReturn("spreadsheetId");
        when(mockGoogleConfiguration.getSheetsService()).thenReturn(mockSheets);
        when(mockSheets.spreadsheets()).thenReturn(mockSpreadsheets);
        when(mockSpreadsheets.values()).thenReturn(mockValues);
        when(mockValues.append(anyString(), anyString(), any(ValueRange.class))).thenReturn(mockAppend);
        when(mockAppend.setValueInputOption(anyString())).thenReturn(mockAppend);
        when(mockAppend.execute()).thenReturn(mockAppendValuesResponse);
    }

    private void initSpreadsheetsBatchGetMock() throws IOException, GeneralSecurityException {
        when(mockGoogleConfiguration.getSpreadsheetsId()).thenReturn("spreadsheetId");
        when(mockGoogleConfiguration.getSheetsService()).thenReturn(mockSheets);
        when(mockSheets.spreadsheets()).thenReturn(mockSpreadsheets);
        when(mockSpreadsheets.values()).thenReturn(mockValues);
        when(mockValues.batchGet(anyString())).thenReturn(mockBatchGet);
        when(mockBatchGet.setRanges(anyList())).thenReturn(mockBatchGet);
        when(mockBatchGet.execute()).thenReturn(mockBatchGetValuesResponse);
        when(mockBatchGetValuesResponse.getValueRanges()).thenReturn(mockValueRanges);
    }

    private void initValueRanges(int countParseFields, int secondInd) {
        when(mockListValues.get(0)).thenReturn("1");
        when(mockListListValues.get(secondInd)).thenReturn(mockListValues);
        when(mockValueRange.getValues()).thenReturn(mockListListValues);
        when(mockValueRange.getValues()).thenReturn(mockListListValues);
        for (int i = 0; i < countParseFields; i++) {
            doReturn(mockValueRange).when(mockValueRanges).get(i);
        }
    }

    private void initCalendarMock() throws GeneralSecurityException, IOException {
        when(mockGoogleConfiguration.getCalendarId()).thenReturn("calendarId");
        when(mockGoogleConfiguration.getCalendarService()).thenReturn(mockCalendar);
        when(mockCalendar.events()).thenReturn(mockEvents);
        when(mockEvents.insert(anyString(), any(Event.class))).thenReturn(mockInsert);
        when(mockInsert.execute()).thenReturn(mockEvent);
    }

    private void initCreateGameDtoMock() {
        when(mockCreateGameDto.getDescription()).thenReturn("test");
        when(mockCreateGameDto.getGameType()).thenReturn("type");
        when(mockCreateGameDto.getPeople()).thenReturn("1");
        when(mockCreateGameDto.getStartTime()).thenReturn(LocalTime.now());
        when(mockCreateGameDto.getEndTime()).thenReturn(LocalTime.now());
        when(mockCreateGameDto.getDate()).thenReturn(LocalDate.now());
        when(mockCreateGameDto.getPhone()).thenReturn("phone");
    }

    private void initAfterGameDto() {
        when(mockAfterGameDto.getAuthor()).thenReturn("author");
        when(mockAfterGameDto.getDate()).thenReturn(LocalDate.now());
        when(mockAfterGameDto.getStartTime()).thenReturn(LocalTime.now());
        when(mockAfterGameDto.getGameType()).thenReturn("type");
        when(mockAfterGameDto.getPeople()).thenReturn("1");
        when(mockAfterGameDto.getBalls()).thenReturn("1");
        when(mockAfterGameDto.getGrenades()).thenReturn("1");
        when(mockAfterGameDto.getFlashS()).thenReturn("1");
        when(mockAfterGameDto.getFlashM()).thenReturn("1");
        when(mockAfterGameDto.getSmokeL()).thenReturn("1");
        when(mockAfterGameDto.getGazebo()).thenReturn("1");
        when(mockAfterGameDto.getRepair()).thenReturn("1");
    }

    private void initWarehouseDto() {
        when(mockWarehouseDto.getRecordDate()).thenReturn(LocalDate.now());
        when(mockWarehouseDto.getAuthor()).thenReturn("author");
        when(mockWarehouseDto.getBalls()).thenReturn("1");
        when(mockWarehouseDto.getGrenades()).thenReturn("1");
        when(mockWarehouseDto.getFlashS()).thenReturn("1");
        when(mockWarehouseDto.getFlashM()).thenReturn("1");
        when(mockWarehouseDto.getSmokeL()).thenReturn("1");
        when(mockWarehouseDto.getNaples()).thenReturn("1");
        when(mockWarehouseDto.getClean()).thenReturn("1");
    }

    private void initCashbookDto() {
        when(mockCashbookDto.getRecordDate()).thenReturn(LocalDate.now());
        when(mockCashbookDto.getAmount()).thenReturn("author");
        when(mockCashbookDto.getAmount()).thenReturn("1");
        when(mockCashbookDto.getDescription()).thenReturn("description");
    }
}
