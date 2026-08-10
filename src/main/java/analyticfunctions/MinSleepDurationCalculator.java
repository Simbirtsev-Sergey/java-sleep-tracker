package analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class MinSleepDurationCalculator implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(final List<SleepSession> sleepingSessions) {
        final long minSleepSession = sleepingSessions.stream()
                .mapToLong(SleepSession::getDurationSleepInMinutes)
                .min()
                .orElse(0L);

        return new SleepAnalysisResult("Минимальная продолжительность", (int) minSleepSession);
    }
}