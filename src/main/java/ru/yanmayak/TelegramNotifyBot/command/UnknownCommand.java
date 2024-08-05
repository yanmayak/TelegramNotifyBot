package ru.yanmayak.TelegramNotifyBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yanmayak.TelegramNotifyBot.command.serivce.SendBotMessageService;

public class UnknownCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    public static final String UNKNOWN_COMMAND = "Не понимаю вас. Напишите /help, чтобы узнать список доступных команд";

    public UnknownCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), UNKNOWN_COMMAND);
    }
}
