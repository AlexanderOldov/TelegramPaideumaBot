package ru.oldov.telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


public class PaideumaAlertBot extends TelegramLongPollingBot {
    private static final String TOKEN = "2118486144:AAEJ7BDrVcJ2hTz8BHJZUJcU_ofO1LdSHYQ";
    private static final String USERNAME = "paideumaAlert_bot";

    private String lastMessage = "";
    private Scraper scraper = new Scraper();

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }


    @Override
    public void onUpdateReceived(Update update) {

            long chat_id = update.getMessage().getChatId();

            try{

                if(update.getMessage().getText().equals("/connect")){
                    execute(new SendMessage(chat_id,"You are connected"));
                    UpdateCheck.newSubscriber(chat_id);
                } else{
                    if(!update.getMessage().getText().equals("/disconnect")){
                        execute(new SendMessage(chat_id,"Write /connect to subscribe. Write /disconnect to unsubscribe."));
                    }
                }

                if(update.getMessage().getText().equals("/disconnect")){
                    execute(new SendMessage(chat_id, "You are not connected"));
                    UpdateCheck.unsubscribe(chat_id);
                    }
                } catch (TelegramApiException telegramApiException) {
                telegramApiException.printStackTrace();
            }

    }
}



