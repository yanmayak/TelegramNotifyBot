package ru.yanmayak.TelegramNotifyBot.command;

import lombok.Getter;

@Getter
public enum CommandName {
    START("/start"),
    STOP("/stop"),
    HELP("/help"),
    NO("nocommand");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

}
