package ru.yanmayak.TelegramNotifyBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface Command {
    String type();

    void execute(Update update);
}
