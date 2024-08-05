package ru.yanmayak.TelegramNotifyBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yanmayak.TelegramNotifyBot.command.serivce.SendBotMessageService;

public class StartCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    public final static String START_MESSAGE = "Добрый день! Данный бот позволяет отправлять уведомления о том, " +
            "что кто-то из участников сервера Discord зашел в голосовой канал.";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), START_MESSAGE);
    }
}
