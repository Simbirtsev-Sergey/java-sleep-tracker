package analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsCounter implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(final List<SleepSession> sleepingSessions) {

        if (sleepingSessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0);
        }
        final long countNights = countNight(sleepingSessions);

        final long countSleeplessNights = sleepingSessions
                .stream()
                .filter(session -> !session.getBeginSleepSession().toLocalDate()
                        .equals(session.getEndSleep().toLocalDate()) ||
                        session.getBeginSleepSession().toLocalTime().isBefore(LocalTime.of(6, 0)))
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей",
                (int) (countNights - countSleeplessNights));
    }


    private long countNight(final List<SleepSession> sleepingSessions) {
        final LocalDateTime start = sleepingSessions.getFirst().getBeginSleepSession();
        final LocalDateTime end = sleepingSessions.getLast().getEndSleep();

        final LocalDate first = start.toLocalTime().equals(LocalTime.MIDNIGHT) ?
                start.toLocalDate() : start.toLocalDate().plusDays(1);

        final LocalDate last = !end.toLocalTime().isBefore(LocalTime.of(6, 0))
                ? end.toLocalDate()
                : end.toLocalDate().minusDays(1);

        final long countNights = ChronoUnit.DAYS.between(first, last) + 1;

        return start.toLocalTime().isBefore(LocalTime.of(12, 0)) ? countNights + 1 : countNights;
    }
}
// Он должен начать спать с 0:00 до 6:00

// В файлике 10 сонных ночей
// и 20 бессонных ночей

// Сначала вычислим количество ночей
// Сейчас вычислим количество ночей, когда пользователь спал
// Потом из общего количества ночей вычьтем количество ночей, когда пользователь спал