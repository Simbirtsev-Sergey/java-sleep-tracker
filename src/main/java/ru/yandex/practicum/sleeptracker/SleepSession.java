package ru.yandex.practicum.sleeptracker;

import enums.SleepAssassment;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepSession {
    private final LocalDateTime beginSleepSession;
    private final LocalDateTime endSleep;
    private final Duration durationSleep;
    // Оценка сна
    private final SleepAssassment sleepAssassment;

    public SleepSession(final LocalDateTime beginSleepSession, final LocalDateTime endSleep,
                        final SleepAssassment sleepAssassment) {
        this.beginSleepSession = beginSleepSession;
        this.endSleep = endSleep;
        this.sleepAssassment = sleepAssassment;
        durationSleep = Duration.between(beginSleepSession, endSleep);
    }

    public long getDurationSleepInMinutes() {
        return durationSleep.toMinutes();
    }

    public SleepAssassment getSleepAssassment() {
        return sleepAssassment;
    }
}
