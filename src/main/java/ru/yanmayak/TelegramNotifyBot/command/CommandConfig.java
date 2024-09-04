package ru.yanmayak.TelegramNotifyBot.command;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.bots.AbsSender;

@Configuration
public class CommandConfig {
    @Bean("helpCommand")
    public Command helpCommand(AbsSender sender) {
        return new CommandText(
            sender,
            "/help",
                String.format("""
                                Список доступных команд: \n 
                                %s - Начать работу с ботом; \n
                                %s - Закончить работу с ботом; \n
                                %s - Получить помощь в работе с ботом.""",
                        CommandName.START.getCommandName(),
                        CommandName.STOP.getCommandName(),
                        CommandName.HELP.getCommandName()
                )
        );
    }

    @Bean("startCommand")
    public Command startCommand(AbsSender sender) {
        return new CommandText(
            sender,
            "/start",
            """
                    Добрый день! Данный бот позволяет отправлять уведомления о том,
                    что кто-то из участников сервера Discord зашел в голосовой канал."""
        );
    }

    @Bean("notCommand")
    public Command notCommand(AbsSender sender) {
        return new CommandText(
                sender,
                "noСommand",
                """
            Я поддерживаю команды, начинающиеся со слеша (/).
            Чтобы просмотреть список доступных команд, введите /help."""
        );
    }

    @Bean("stopCommand")
    public Command stopCommand(AbsSender sender) {
        return new CommandText(
                sender,
                "/stop",
                "Уведомления выключены."
        );
    }

    @Bean("unknownCommand")
    public Command unknownCommand(AbsSender sender) {
        return new CommandText(
                sender,
                "unknown",
                "Не понимаю вас. Напишите /help, чтобы узнать список доступных команд."
        );
    }
}
