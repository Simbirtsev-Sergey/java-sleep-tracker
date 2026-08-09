package analyticFunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class MinSleepDurationCalculator implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepingSessions) {
        long min = sleepingSessions.stream()
                .mapToLong(SleepSession::getDurationSleepInMinutes)
                .min()
                .orElse(0L);

        return new SleepAnalysisResult("Минимальная продолжительность", (int) min);
    }
}
