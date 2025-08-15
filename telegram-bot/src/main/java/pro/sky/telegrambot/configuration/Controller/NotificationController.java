package pro.sky.telegrambot.configuration.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.configuration.Service.NotificationTaskService;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationTaskService notificationTaskService;

    @Autowired
    public NotificationController(NotificationTaskService notificationTaskService) {
        this.notificationTaskService = notificationTaskService;
    }

    @PostMapping("/add")
    public String addNotification(@RequestBody String message) {
        try {
            notificationTaskService.parseAndSaveNotification(message);
            return "Уведомление успешно добавлено!";
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}