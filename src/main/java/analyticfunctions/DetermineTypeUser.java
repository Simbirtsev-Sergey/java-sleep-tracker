package analyticfunctions;

import enums.TypeUser;
import ru.yandex.practicum.sleeptracker.SleepSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class DetermineTypeUser implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(final List<SleepSession> sleepSessions) {
        List<SleepSession> nightSessions = sleepSessions
                .stream().
                filter(this::isNightSession)
                .toList();

        final long countOwls = nightSessions.stream()
                .filter(session -> session.getBeginSleepSession().toLocalTime().isAfter(LocalTime.of(23, 0)) &&
                        session.getEndSleep().toLocalTime().isAfter(LocalTime.of(9, 0)))
                .count();

        final long countLark = nightSessions.stream()
                .filter(session -> session.getBeginSleepSession().toLocalTime().isBefore(LocalTime.of(22, 0)) &&
                        session.getEndSleep().toLocalTime().isBefore(LocalTime.of(7, 0)))
                .count();

        final long countPigeon = nightSessions.size() - countOwls - countLark;

        final TypeUser typeUser;

        if (countOwls > countLark && countOwls > countPigeon) {
            typeUser = TypeUser.OWL;
        } else if (countLark > countOwls && countLark > countPigeon) {
            typeUser = TypeUser.LARK;
        } else {
            typeUser = TypeUser.PIGEON;
        }

        return new SleepAnalysisResult("По типу сна вы", typeUser);
    }

    private boolean isNightSession(final SleepSession session) {
        final LocalDate beginDate = session.getBeginSleepSession().toLocalDate();
        final LocalDate endDate = session.getEndSleep().toLocalDate();
        final LocalTime beginTime = session.getBeginSleepSession().toLocalTime();

        return !beginDate.equals(endDate)
                || (!beginTime.isBefore(LocalTime.MIDNIGHT) && beginTime.isBefore(LocalTime.of(6, 0)));
    }
}