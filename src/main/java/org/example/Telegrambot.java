package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Telegrambot extends TelegramLongPollingBot
{

    @Override
    public String getBotUsername()
    {
        return "B0T_T3STbot";
    }

    @Override
    public String getBotToken()
    {
        return "7359784003:AAHOIEPggFESQEc-WZCOX80hOyqtx1YkVt0";
    }

    @Override
    public void onUpdateReceived(Update update)
    {

        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();

        String newText = new StringBuilder(text).reverse().toString();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(newText);

        try
        {
            this.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}