package pro.sky.telegrambot.configuration.Service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramMessageService {

    private final TelegramBot telegramBot;

    @Autowired
    public TelegramMessageService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendResponse(long chatId, String message) {
        SendMessage sendMessage = new SendMessage(chatId, message);
        telegramBot.execute(sendMessage);
    }

}
