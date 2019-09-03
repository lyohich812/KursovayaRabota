package ru.eltech.pavlov.model;

import java.util.Random;

public class Model {

    private int scale;
    private int queueLimit;
    private long scaledMillisFromStart;
    private long lastStepMillis;
    private Queue queue;
    private int upcomingTime;

    public Model(int scale, int queueLimit, int serviceTime, int upcomingTime){
        long millisServiceTime = (long)(serviceTime * 60 * 1000);
        this.scale = scale;
        this.queueLimit = queueLimit;
        this.upcomingTime = upcomingTime;
        queue = new Queue(this.queueLimit, millisServiceTime);
        lastStepMillis = 0L;
    }

    public void step(long millisFromStart){
        this.scaledMillisFromStart = millisFromStart * scale;

        while (!EventMap.isEmpty() && EventMap.getFirstEventTimeEpochMillis() <= scaledMillisFromStart){
            Event e = EventMap.getEvent();

            switch (e.getThisEventName()){
                case E1: {
                    if(queue.addToQueue()){
                        Engine.sendEventToGUI(e);
                    }
                    break;
                }
                case E2: {
                    break;
                }
                case E3: {
                    break;
                }
                case E4: {
                    queue.makeServiceFree(e);
                    break;
                }
                case E5: {
                    queue.makeServiceFree(e);
                    break;
                }
                default: break;
            }

            queue.toService(scaledMillisFromStart);
        }

        long interval = scaledMillisFromStart - lastStepMillis;
        lastStepMillis = scaledMillisFromStart;

        double intervalToSecs = (double)interval/1000;

        double probability = intervalToSecs/60
                * (1/(double)upcomingTime)
                * Math.exp(
                        -(intervalToSecs/60 * (1/(double)upcomingTime)));

        //double randomNumber = Math.abs(((double)(new Random().nextInt()%100))/100);

        double randomNumber = new Random().nextDouble();

        if (probability > randomNumber){
            EventMap.addEvent(new Event(scaledMillisFromStart, Event.eventName.E1));
        }

    }

}
