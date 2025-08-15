package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import pro.sky.telegrambot.configuration.Service.TelegramMessageService;

import java.util.List;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    @Autowired
    private TelegramBot telegramBot;

    @Autowired
    private TelegramMessageService telegramMessageService;

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();

                if (messageText.equals("/start")) {
                    long chatId = update.message().chat().id();
                    telegramMessageService.sendResponse(chatId, "Добро пожаловать в нашего бота! Как я могу помочь вам сегодня?");
                }
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}

