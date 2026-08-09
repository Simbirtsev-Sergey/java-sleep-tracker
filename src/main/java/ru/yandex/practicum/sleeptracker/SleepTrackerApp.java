package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

import analyticFunctions.*;


public class SleepTrackerApp {
    private static final SleepSessions sleepSessions = new SleepSessions();
    private static final List<Function<List<SleepSession>, SleepAnalysisResult>> analyticFunctions = List.of(
            new SleepSessionCounter(),
            new MaxSleepDurationCalculator(),
            new MinSleepDurationCalculator(),
            new BadQualitySessionsCounter(),
            new AvgSleepDurationCalculator(),
            new SleeplessNightsCounter(),
            new DetermineTypeUser()
    );


    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Файл не был передан");
            return;
        }

        final String fileName = args[0];

        // Создаём объект загрузки
        final SleepingSessionsLoader loader = new SleepingSessionsLoader(fileName, sleepSessions);

        // Загружаем log- файл в sleepSessions
        loader.loaderSessions();

        // Выводим результат аналитических функций
        analyticFunctions.stream()
                .map(function -> function.apply(sleepSessions.getSleepSessions()))
                .forEach(System.out::println);
    }
}