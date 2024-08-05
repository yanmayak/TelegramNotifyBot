package ru.yanmayak.TelegramNotifyBot.command;


import com.google.common.collect.ImmutableMap;
import ru.yanmayak.TelegramNotifyBot.command.serivce.SendBotMessageService;

public class CommandContainer {
    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {
        unknownCommand = new UnknownCommand(sendBotMessageService);
        commandMap = ImmutableMap.<String, Command>builder()
                .put(CommandName.START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(CommandName.STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(CommandName.HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(CommandName.NO.getCommandName(), new NotCommand(sendBotMessageService))
                .build();
    }

    public Command retrieveCommand(String commandIdentifier) {
        return commandMap.getOrDefault(commandIdentifier, unknownCommand);
    }
}
