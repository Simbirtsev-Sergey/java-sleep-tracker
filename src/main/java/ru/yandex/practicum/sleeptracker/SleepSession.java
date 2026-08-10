package ru.yandex.practicum.sleeptracker;

import enums.SleepAssessment;

import java.time.Duration;
import java.time.LocalDateTime;

public class SleepSession {
    private final LocalDateTime beginSleepSession;
    private final LocalDateTime endSleep;
    private final Duration durationSleep;
    // Оценка сна
    private final SleepAssessment sleepAssessment;

    public SleepSession(final LocalDateTime beginSleepSession, final LocalDateTime endSleep,
                        final SleepAssessment sleepAssessment) {
        this.beginSleepSession = beginSleepSession;
        this.endSleep = endSleep;
        this.sleepAssessment = sleepAssessment;
        durationSleep = Duration.between(beginSleepSession, endSleep);
    }

    public long getDurationSleepInMinutes() {
        return durationSleep.toMinutes();
    }

    public SleepAssessment getSleepAssessment() {
        return sleepAssessment;
    }

    public LocalDateTime getBeginSleepSession() {
        return beginSleepSession;
    }

    public LocalDateTime getEndSleep() {
        return endSleep;
    }
}
