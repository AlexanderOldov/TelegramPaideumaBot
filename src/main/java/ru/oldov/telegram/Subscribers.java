package ru.oldov.telegram;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;

public class Subscribers {

    private ArrayList<Long> listOfSubscribers = new ArrayList<>();
    private int subscribersTotal = 0;
    PaideumaAlertBot paideumaAlertBot = new PaideumaAlertBot();
    private Scraper scraper = new Scraper();
    private boolean alreadySubscribed = false;
    private ArrayList<String> oldNews = new ArrayList<>();

    public void setSubscriber(long subscriber) {

        for (long s : listOfSubscribers) {
            if (s == subscriber) {
                alreadySubscribed = true;
            }
        }
        if (!alreadySubscribed) {
            listOfSubscribers.add(subscribersTotal, subscriber);
            subscribersTotal++;

        }
        alreadySubscribed = false;
    }

    public void unsubscribe(long subscriber){
        listOfSubscribers.remove(subscriber);
        subscribersTotal--;

    }
    public void sendNewToAllSubscribers() {
        for (long subscriber : listOfSubscribers) {
            if (subscriber != 0 && !oldNews.contains(scraper.getVideoTitle())) {
                try {
                    paideumaAlertBot.execute(new SendMessage(subscriber, scraper.getVideoTitle()));
                    if (subscriber == listOfSubscribers.get(subscribersTotal - 1)) {
                        paideumaAlertBot.setLastMessage(scraper.getVideoTitle());
                    }
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }oldNews.add(paideumaAlertBot.getLastMessage());
    }
}