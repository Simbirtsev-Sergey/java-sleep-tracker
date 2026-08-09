package analyticFunctions;

import enums.TypeUser;
import ru.yandex.practicum.sleeptracker.SleepSession;
import ru.yandex.practicum.sleeptracker.SleepSessions;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class DetermineTypeUser implements Function<List<SleepSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(final List<SleepSession> sleepSessions) {
        final long countOwls = sleepSessions
                .stream()
                .filter(session -> session.getBeginSleepSession().toLocalTime()
                        .isBefore(LocalTime.of(22, 0)) &&
                        session.getEndSleep().toLocalTime().isAfter(LocalTime.of(7, 0)))
                .count();

        final long countLark = sleepSessions
                .stream()
                .filter(session -> session.getEndSleep().toLocalTime().isAfter(LocalTime.of(9, 0)) &&
                        (session.getBeginSleepSession().toLocalTime().isBefore(LocalTime.of(23, 0)) ||
                                session.getBeginSleepSession().toLocalTime().isBefore(LocalTime.of(0, 0))))
                .count();

        final long countPigeon = sleepSessions.size() - countOwls - countLark;

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

}