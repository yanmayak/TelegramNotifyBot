package ru.yanmayak.TelegramNotifyBot.bot;

import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yanmayak.TelegramNotifyBot.command.CommandName;
import ru.yanmayak.TelegramNotifyBot.command.Container;
import ru.yanmayak.TelegramNotifyBot.command.serivce.SendBotMessageServiceImpl;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    private final String botToken;
    private final String botUsername;
    private final Container container;

    @Autowired
    public TelegramBot(@Value("${bot.token}") String botToken, @Value("${bot.username}") String botUsername, @Lazy Container container) {
        this.container = container;
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().isCommand()) {
                try {
                    String message = update.getMessage().getText().trim();
                    container.provide(message).execute(update);
                } catch (NullPointerException e) {
                    log.error("Message cannot be null", e);
                }
            } else {
                container.provide(CommandName.NO.getCommandName()).execute(update);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
