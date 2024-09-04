package ru.yanmayak.TelegramNotifyBot.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class CommandText implements Command {
    private static final Logger log = LoggerFactory.getLogger(CommandText.class);
    private final AbsSender sender;
    private final String type;
    private final String message;

    public CommandText(
        AbsSender sender,
        String type,
        String message
    ) {
        this.sender = sender;
        this.type = type;
        this.message = message;
    }

    @Override
    public String type() {
        return this.type;
    }

    @Override
    public void execute(Update update) {
        try {
            this.sender.execute(
                new SendMessage(
                    update.getMessage().getChatId().toString(),
                    this.message
                )
            );
        } catch (TelegramApiException e) {
            log.error("Something went wrong", e);
        }
    }
}
