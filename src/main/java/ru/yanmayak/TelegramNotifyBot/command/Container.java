package ru.yanmayak.TelegramNotifyBot.command;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Container {
    private final Map<String, Command> command;

    public Container(List<Command> command) {
        this.command = command.stream()
            .collect(
                Collectors.toMap(
                    (cmd) -> cmd.type(),
                    (cmd) -> cmd
                )
            );
    }

    public Command provide(String type) {
        return this.command.get(type);
    }
}
