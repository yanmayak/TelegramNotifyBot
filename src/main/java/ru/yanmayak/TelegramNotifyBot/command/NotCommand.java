package ru.yanmayak.TelegramNotifyBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yanmayak.TelegramNotifyBot.command.serivce.SendBotMessageService;

public class NotCommand implements Command {
    private final SendBotMessageService sendBotMessageService;
    public final static String NO_MESSAGE = "Я поддерживаю команды, начинающиеся со слеша (/)." +
            "Чтобы просмотреть список доступных команд, введите /help.";

    public NotCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), NO_MESSAGE);
    }
}
