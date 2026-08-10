package analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class MaxSleepDurationCalculator implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(final List<SleepSession> sleepingSessions) {
        final long max = sleepingSessions.stream()
                .mapToLong(SleepSession::getDurationSleepInMinutes)
                .max()
                .orElse(0L);

        return new SleepAnalysisResult("Максимальная продолжительность", (int) max);
    }
}