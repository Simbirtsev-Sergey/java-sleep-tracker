package analyticFunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class AvgSleepDurationCalculator implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepSession> sleepingSessions) {
        double avg = sleepingSessions.stream()
                .mapToLong(SleepSession::getDurationSleepInMinutes)
                .average()
                .orElse(0);


        return new SleepAnalysisResult("Средняя продолжительность сна", (int) avg);
    }
}
