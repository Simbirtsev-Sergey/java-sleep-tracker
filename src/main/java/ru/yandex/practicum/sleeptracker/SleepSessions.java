package ru.yandex.practicum.sleeptracker;

import java.util.ArrayList;
import java.util.List;

public class SleepSessions {
    private final List<SleepSession> sleepSessions;

    public SleepSessions() {
        sleepSessions = new ArrayList<>();
    }

    public void addSleepSession(SleepSession sleepSession) {
        sleepSessions.add(sleepSession);
    }


    public List<SleepSession> getSleepSessions() {
        return sleepSessions;
    }
}
