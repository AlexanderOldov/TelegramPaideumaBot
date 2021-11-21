package ru.oldov.telegram;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class UpdateCheck{
    private static Subscribers subscribers = new Subscribers();
    long delay = 10 * 1000; // delay in milliseconds
        LoopTask task = new LoopTask();
        Timer timer = new Timer("TaskName");

        public void start() {
            timer.cancel();
            timer = new Timer("TaskName");
            Date executionDate = new Date(); // no params = now
            timer.scheduleAtFixedRate(task, executionDate, delay);
        }

private class LoopTask extends TimerTask {
    public void run() {
            subscribers.sendNewToAllSubscribers();
    }
}

    public static void newSubscriber(long chat_id){
        subscribers.setSubscriber(chat_id);
    }

    public static void unsubscribe(long chat_id){
            subscribers.unsubscribe(chat_id);
    }
}