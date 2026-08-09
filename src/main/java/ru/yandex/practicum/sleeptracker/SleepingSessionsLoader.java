package ru.yandex.practicum.sleeptracker;

import enums.SleepAssessment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;


public class SleepingSessionsLoader {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private final String fileName;
    private final SleepSessions sleepSessions;

    public SleepingSessionsLoader(final String fileName, final SleepSessions sleepSessions) {
        this.fileName = fileName;
        this.sleepSessions = sleepSessions;
    }

    public void loaderSessions() {
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            lines.forEach(line -> {
                final String[] sleepData = line.split(";");
                final LocalDateTime beginSleepSession = LocalDateTime.parse(sleepData[0], FORMATTER);
                final LocalDateTime endSleep = LocalDateTime.parse(sleepData[1], FORMATTER);
                final SleepAssessment assessment = SleepAssessment.valueOf(sleepData[2]);

                sleepSessions.addSleepSession(new SleepSession(beginSleepSession, endSleep, assessment));
            });
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла.");
        }
    }
}
