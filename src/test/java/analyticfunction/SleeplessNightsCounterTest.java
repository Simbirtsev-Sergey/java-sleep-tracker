package analyticfunction;

import analyticfunctions.SleepAnalysisResult;
import analyticfunctions.SleeplessNightsCounter;
import enums.SleepAssessment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.SleepSessions;

import java.time.LocalDateTime;

import static java.time.Month.OCTOBER;
import static java.time.Month.NOVEMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SleeplessNightsCounterTest {
    private static SleeplessNightsCounter SleeplessNightsCounter;
    private static SleepSessions sleepSessions;

    @BeforeEach
    public void beforeEach() {
        sleepSessions = new SleepSessions();
        SleeplessNightsCounter = new SleeplessNightsCounter();
    }

    @Test
    public void shouldReturnZeroNights() {
        assertEquals(new SleepAnalysisResult("Количество бессонных ночей", 0),
                SleeplessNightsCounter.apply(sleepSessions.getSleepSessions()));
    }

    @Test
    public void nightBetweenTheMonths() {
        SleepSession session1 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 31, 23, 15),
                LocalDateTime.of(2025, NOVEMBER, 1, 7, 30), SleepAssessment.GOOD);
        SleepSession session2 = new SleepSession(LocalDateTime.of(2025, NOVEMBER, 1, 15, 50),
                LocalDateTime.of(2025, NOVEMBER, 1, 17, 40), SleepAssessment.BAD);

        sleepSessions.addSleepSession(session1);
        sleepSessions.addSleepSession(session2);

        assertEquals(new SleepAnalysisResult("Количество бессонных ночей", 0),
                SleeplessNightsCounter.apply(sleepSessions.getSleepSessions()));
    }

    @Test
    public void firstSessionBeforeAt12() {
        SleepSession session1 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 1, 11, 15),
                LocalDateTime.of(2025, OCTOBER, 1, 15, 30), SleepAssessment.GOOD);
        SleepSession session2 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 2, 23, 50),
                LocalDateTime.of(2025, OCTOBER, 3, 6, 0), SleepAssessment.BAD);
        SleepSession session3 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 3, 14, 10),
                LocalDateTime.of(2025, OCTOBER, 3, 15, 30), SleepAssessment.BAD);
        SleepSession session4 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 3, 23, 40),
                LocalDateTime.of(2025, OCTOBER, 4, 8, 0), SleepAssessment.BAD);

        sleepSessions.addSleepSession(session1);
        sleepSessions.addSleepSession(session2);
        sleepSessions.addSleepSession(session3);
        sleepSessions.addSleepSession(session4);

        assertEquals(new SleepAnalysisResult("Количество бессонных ночей", 2),
                SleeplessNightsCounter.apply(sleepSessions.getSleepSessions()));
    }

    @Test
    public void sessionsStartAtNight() {
        SleepSession session1 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 1, 0, 40),
                LocalDateTime.of(2025, OCTOBER, 1, 5, 30), SleepAssessment.GOOD);
        SleepSession session2 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 2, 0, 50),
                LocalDateTime.of(2025, OCTOBER, 2, 5, 40), SleepAssessment.BAD);
        SleepSession session3 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 3, 14, 10),
                LocalDateTime.of(2025, OCTOBER, 3, 23, 1), SleepAssessment.BAD);
        SleepSession session4 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 15, 0, 0),
                LocalDateTime.of(2025, OCTOBER, 15, 6, 0), SleepAssessment.BAD);

        sleepSessions.addSleepSession(session1);
        sleepSessions.addSleepSession(session2);
        sleepSessions.addSleepSession(session3);
        sleepSessions.addSleepSession(session4);

        assertEquals(new SleepAnalysisResult("Количество бессонных ночей", 12),
                SleeplessNightsCounter.apply(sleepSessions.getSleepSessions()));
    }
}