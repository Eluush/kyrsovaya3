package pro.sky.telegrambot.configuration.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailNotificationService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendNotification(String recipient, String message) {

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipient);
        mailMessage.setSubject("Уведомление");
        mailMessage.setText(message);


        mailSender.send(mailMessage);

        System.out.println("Отправка email на " + recipient + ": " + message);
    }
}
