package analyticfunction;

import analyticfunctions.DetermineTypeUser;
import analyticfunctions.SleepAnalysisResult;
import enums.SleepAssessment;
import enums.TypeUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.SleepSessions;

import java.time.LocalDateTime;

import static java.time.Month.OCTOBER;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DetermineTypeUserTest {
    private static DetermineTypeUser DetermineTypeUser;
    private static SleepSessions sleepSessions;

    @BeforeEach
    public void beforeEach() {
        sleepSessions = new SleepSessions();
        DetermineTypeUser = new DetermineTypeUser();
    }

    @Test
    public void shouldPrintOWL() {
        SleepSession session1 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 1, 23, 15),
                LocalDateTime.of(2025, OCTOBER, 2, 10, 30), SleepAssessment.GOOD);
        SleepSession session2 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 2, 23, 50),
                LocalDateTime.of(2025, OCTOBER, 3, 10, 40), SleepAssessment.NORMAL);
        SleepSession session3 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 3, 21, 10),
                LocalDateTime.of(2025, OCTOBER, 4, 6, 30), SleepAssessment.GOOD);

        sleepSessions.addSleepSession(session1);
        sleepSessions.addSleepSession(session2);
        sleepSessions.addSleepSession(session3);

        assertEquals(new SleepAnalysisResult("По типу сна вы", TypeUser.OWL),
                DetermineTypeUser.apply(sleepSessions.getSleepSessions()));
    }

    @Test
    public void shouldPrintLARK() {
        SleepSession session1 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 2, 23, 50),
                LocalDateTime.of(2025, OCTOBER, 3, 10, 40), SleepAssessment.NORMAL);
        SleepSession session2 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 3, 21, 10),
                LocalDateTime.of(2025, OCTOBER, 4, 6, 30), SleepAssessment.GOOD);
        SleepSession session3 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 4, 20, 45),
                LocalDateTime.of(2025, OCTOBER, 5, 2, 30), SleepAssessment.GOOD);

        sleepSessions.addSleepSession(session1);
        sleepSessions.addSleepSession(session2);
        sleepSessions.addSleepSession(session3);

        assertEquals(new SleepAnalysisResult("По типу сна вы", TypeUser.LARK),
                DetermineTypeUser.apply(sleepSessions.getSleepSessions()));
    }


    @Test
    public void shouldPrintPIGEON() {
        SleepSession session1 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 1, 23, 15),
                LocalDateTime.of(2025, OCTOBER, 2, 10, 30), SleepAssessment.GOOD);
        SleepSession session2 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 2, 23, 50),
                LocalDateTime.of(2025, OCTOBER, 3, 10, 40), SleepAssessment.NORMAL);
        SleepSession session3 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 3, 21, 10),
                LocalDateTime.of(2025, OCTOBER, 4, 6, 30), SleepAssessment.GOOD);
        SleepSession session4 = new SleepSession(LocalDateTime.of(2025, OCTOBER, 4, 20, 45),
                LocalDateTime.of(2025, OCTOBER, 5, 2, 30), SleepAssessment.GOOD);

        sleepSessions.addSleepSession(session1);
        sleepSessions.addSleepSession(session2);
        sleepSessions.addSleepSession(session3);
        sleepSessions.addSleepSession(session4);
        assertEquals(new SleepAnalysisResult("По типу сна вы", TypeUser.PIGEON),
                DetermineTypeUser.apply(sleepSessions.getSleepSessions()));
    }
}