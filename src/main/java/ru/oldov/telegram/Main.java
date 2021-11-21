package ru.oldov.telegram;


import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;


public class Main {
    public static void main(String[] args){
        ApiContextInitializer.init();

        PaideumaAlertBot paideumaAlertBot = new PaideumaAlertBot();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try{
            botsApi.registerBot(paideumaAlertBot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

        UpdateCheck updateCheck = new UpdateCheck();
        updateCheck.start();
    }
}