package analyticFunctions;

import enums.SleepAssassment;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class BadQualitySessionsCounter implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepingSessions) {
        long cnt = sleepingSessions
                .stream()
                .filter(session -> session.getSleepAssassment() == SleepAssassment.BAD)
                .count();

        return new SleepAnalysisResult("Количество сессий с плохим качеством сна", (int) cnt);
    }
}