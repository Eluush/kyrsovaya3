package pro.sky.telegrambot.configuration.model;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Scheduled(fixedDelay = 1_000L)
    public void runEverySecond() {
        System.out.println("Запуск метода раз в секунду: " + System.currentTimeMillis());
    }

    @Scheduled(cron = "0 * * * * *")
    public void runEveryMinute() {
        System.out.println("Запуск метода в начале каждой минуты: " + System.currentTimeMillis());
    }

    @Scheduled(cron = "0 12 12 ? * MON")
    public void runAtSpecificTime() {
        System.out.println("Запуск метода в 12:12 по понедельникам в январе: " + System.currentTimeMillis());
    }
}
