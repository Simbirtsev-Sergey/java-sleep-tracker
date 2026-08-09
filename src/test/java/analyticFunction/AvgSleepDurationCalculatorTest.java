package analyticFunction;

import analyticFunctions.AvgSleepDurationCalculator;
import analyticFunctions.SleepAnalysisResult;
import enums.SleepAssassment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.SleepSessions;

import java.time.LocalDateTime;

import static java.time.Month.OCTOBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvgSleepDurationCalculatorTest {
    private static AvgSleepDurationCalculator AvgSleepDurationCalculator;
    private static SleepSessions sleepSessions;

    @BeforeEach
    public void beforeEach() {
        sleepSessions = new SleepSessions();
        AvgSleepDurationCalculator = new AvgSleepDurationCalculator();
    }

    @Test
    public void shouldReturnZeroDuration() {
        assertEquals(new SleepAnalysisResult("Средняя продолжительность сна", 0),
                AvgSleepDurationCalculator.apply(sleepSessions.getSleepSessions()));
    }

    @Test
    public void shouldReturnDuration328() {
        SleepSession session1 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 1, 23, 15),
                LocalDateTime.of(2025, OCTOBER, 2, 7, 30), SleepAssassment.GOOD);
        SleepSession session2 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 2, 23, 50),
                LocalDateTime.of(2025, OCTOBER, 3, 6, 40), SleepAssassment.BAD);
        SleepSession session3 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 3, 14, 10),
                LocalDateTime.of(2025, OCTOBER, 3, 15, 30), SleepAssassment.BAD);

        sleepSessions.addSleepSession(session1);
        sleepSessions.addSleepSession(session2);
        sleepSessions.addSleepSession(session3);

        assertEquals(new SleepAnalysisResult("Средняя продолжительность сна", 328),
                AvgSleepDurationCalculator.apply(sleepSessions.getSleepSessions()));
    }
}
