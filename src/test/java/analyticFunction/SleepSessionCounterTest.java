package analyticFunction;

import analyticFunctions.SleepAnalysisResult;
import analyticFunctions.SleepSessionCounter;
import enums.SleepAssassment;
import org.junit.jupiter.api.BeforeEach;
import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.SleepSessions;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static java.time.Month.OCTOBER;

public class SleepSessionCounterTest {
    private static SleepSessionCounter sleepSessionCounter;
    private static SleepSessions sleepSessions;

    @BeforeEach
    public void beforeEach() {
        sleepSessions = new SleepSessions();
        sleepSessionCounter = new SleepSessionCounter();
    }

    @Test
    public void emptySleepSessionIsCreated() {
        assertEquals(new SleepAnalysisResult("Количество сессий сна", 0),
                sleepSessionCounter.apply(sleepSessions.getSleepSessions()));
    }

    @Test
    public void nonEmptySleepSessionIsCreated() {
        SleepSession session1 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 1, 23, 15),
                LocalDateTime.of(2025, OCTOBER, 2, 7, 30), SleepAssassment.GOOD);
        SleepSession session2 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 2, 23, 50),
                LocalDateTime.of(2025, OCTOBER, 3, 6, 40), SleepAssassment.NORMAL);
        SleepSession session3 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 3, 14, 10),
                LocalDateTime.of(2025, OCTOBER, 3, 15, 30), SleepAssassment.GOOD);

        sleepSessions.addSleepSession(session1);
        sleepSessions.addSleepSession(session2);
        sleepSessions.addSleepSession(session3);

        assertEquals(new SleepAnalysisResult("Количество сессий сна", 3),
                sleepSessionCounter.apply(sleepSessions.getSleepSessions()));
    }
}