package pro.sky.telegrambot.configuration.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.configuration.model.NotificationTask;
import pro.sky.telegrambot.configuration.Repository.NotificationTaskRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class NotificationTaskService {

    private final NotificationTaskRepository notificationTaskRepository;

    @Autowired
    public NotificationTaskService(NotificationTaskRepository notificationTaskRepository) {
        this.notificationTaskRepository = notificationTaskRepository;
    }


    public NotificationTask save(NotificationTask task) {
        return notificationTaskRepository.save(task);
    }


    public List<NotificationTask> findAll() {
        return notificationTaskRepository.findAll();
    }


    public Optional<NotificationTask> findById(Long id) {
        return notificationTaskRepository.findById(id);
    }


    public void delete(Long id) {
        notificationTaskRepository.deleteById(id);
    }


    public void parseAndSaveNotification(String message) {
        String regex = "\\d{2}\\.\\d{2}\\.\\d{4}\\s\\d{2}:\\d{2}\\s+(.+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(message);

        if (matcher.matches()) {
            String dateTimeString = matcher.group(1);
            String reminderText = matcher.group(2); // Исправлено на group(2)

            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

            NotificationTask notificationTask = new NotificationTask();
            notificationTask.setDateTime(dateTime);
            notificationTask.setMessage(reminderText);

            save(notificationTask);
        } else {
            throw new IllegalArgumentException("Неверный формат сообщения: " + message);
        }
    }
}