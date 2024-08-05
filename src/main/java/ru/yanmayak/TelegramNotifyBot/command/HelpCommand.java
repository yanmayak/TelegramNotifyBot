package ru.yanmayak.TelegramNotifyBot.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.yanmayak.TelegramNotifyBot.command.serivce.SendBotMessageService;

public class HelpCommand implements Command{
    private final SendBotMessageService sendBotMessageService;
    public final static String HELP_MESSAGE = String.format("Список доступных команд: \n" +
            "%s - Начать работу с ботом; \n" +
            "%s - Закончить работу с ботом; \n" +
            "%s - Получить помощь в работе с ботом. \n", CommandName.START.getCommandName(),
            CommandName.STOP.getCommandName(), CommandName.HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
