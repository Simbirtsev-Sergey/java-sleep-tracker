package analyticfunction;

import analyticfunctions.MaxSleepDurationCalculator;
import analyticfunctions.SleepAnalysisResult;
import enums.SleepAssessment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.SleepSessions;

import java.time.LocalDateTime;

import static java.time.Month.OCTOBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxSleepDurationCalculatorTest {
    private static MaxSleepDurationCalculator MaxSleepDurationCalculator;
    private static SleepSessions sleepSessions;

    @BeforeEach
    public void beforeEach() {
        sleepSessions = new SleepSessions();
        MaxSleepDurationCalculator = new MaxSleepDurationCalculator();
    }

    @Test
    public void shouldReturnZeroDuration() {
        assertEquals(new SleepAnalysisResult("Максимальная продолжительность", 0),
                MaxSleepDurationCalculator.apply(sleepSessions.getSleepSessions()));
    }

    @Test
    public void shouldReturnDuration495Minutes() {
        SleepSession session1 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 1, 23, 15),
                LocalDateTime.of(2025, OCTOBER, 2, 7, 30), SleepAssessment.GOOD);
        SleepSession session2 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 2, 23, 50),
                LocalDateTime.of(2025, OCTOBER, 3, 6, 40), SleepAssessment.NORMAL);
        SleepSession session3 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 3, 14, 10),
                LocalDateTime.of(2025, OCTOBER, 3, 15, 30), SleepAssessment.GOOD);

        sleepSessions.addSleepSession(session1);
        sleepSessions.addSleepSession(session2);
        sleepSessions.addSleepSession(session3);

        assertEquals(new SleepAnalysisResult("Максимальная продолжительность", 495),
                MaxSleepDurationCalculator.apply(sleepSessions.getSleepSessions()));
    }
}
