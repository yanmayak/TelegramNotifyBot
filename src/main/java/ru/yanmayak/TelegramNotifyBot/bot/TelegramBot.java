package ru.yanmayak.TelegramNotifyBot.bot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yanmayak.TelegramNotifyBot.command.CommandContainer;
import ru.yanmayak.TelegramNotifyBot.command.CommandName;
import ru.yanmayak.TelegramNotifyBot.command.serivce.SendBotMessageServiceImpl;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {
    public static String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;
    private final String botToken;
    private final String botUsername;

    public TelegramBot(@Value("${bot.token}") String botToken, @Value("${bot.username}") String botUsername) {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
        this.botToken = botToken;
        this.botUsername = botUsername;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText().trim();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(CommandName.NO.getCommandName()).execute(update);
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