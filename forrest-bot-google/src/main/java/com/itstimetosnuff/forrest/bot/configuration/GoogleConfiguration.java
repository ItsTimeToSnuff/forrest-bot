package com.itstimetosnuff.forrest.bot.configuration;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
public final class GoogleConfiguration {

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final List<String> SCOPES = Arrays.asList(
            CalendarScopes.CALENDAR,
            SheetsScopes.SPREADSHEETS,
            SheetsScopes.DRIVE
    );

    private final transient String credentialPath;
    private final transient String appName;
    private final transient String calendarId;
    private final transient String spreadsheetsId;

    public String getSpreadsheetsId() {
        return this.spreadsheetsId;
    }

    public String getCalendarId() {
        return this.calendarId;
    }

    private Credential getCredentials() throws IOException {
        try(InputStream in = new FileInputStream(new File(credentialPath))) {
            return GoogleCredential.fromStream(in).createScoped(SCOPES);
        }
    }

    public Calendar getCalendarService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Calendar
                .Builder(httpTransport, JSON_FACTORY, getCredentials())
                .setApplicationName(appName)
                .build();
    }

    public Sheets getSheetsService() throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        return new Sheets
                .Builder(httpTransport, JSON_FACTORY, getCredentials())
                .setApplicationName(appName)
                .build();
    }
}
