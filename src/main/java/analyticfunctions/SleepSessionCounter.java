package analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.util.List;
import java.util.function.Function;

public class SleepSessionCounter implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(final List<SleepSession> sleepingSessions) {
        return new SleepAnalysisResult("Количество сессий сна", sleepingSessions.size());
    }
}
