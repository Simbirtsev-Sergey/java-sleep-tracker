package analyticfunctions;

import enums.SleepAssessment;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class BadQualitySessionsCounter implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(final List<SleepSession> sleepingSessions) {
        final long cnt = sleepingSessions
                .stream()
                .filter(session -> session.getSleepAssessment() == SleepAssessment.BAD)
                .count();

        return new SleepAnalysisResult("Количество сессий с плохим качеством сна", (int) cnt);
    }
}