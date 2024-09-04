package ru.yanmayak.TelegramNotifyBot.command.serivce;

public interface SendBotMessageService {
    void sendMessage(String chatId, String message);
}
