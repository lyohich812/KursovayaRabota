package ru.eltech.pavlov.model;

import java.util.Random;

public class Service {

    private Worker worker1;
    private Worker worker2;
    private long millisServiceTime;

    private Random random = new Random();

    public Service(long millisServiceTime){
        worker1 = new Worker();
        worker2 = new Worker();
        this.millisServiceTime = millisServiceTime;
    }

    public boolean isFree(){
        return (worker1.getState() || worker2.getState());
    }

    public void startService(long scaledMllisFromStart){
        if (worker1.getState()){
            worker1.makeBusy();
            //Генерируем время обслуживания и отправляем в EventMap. Так же выводим E2
            double randomNumber = random.nextDouble();

            double serviceTime = -millisServiceTime * Math.log(randomNumber);
            long serviceFinishTime = scaledMllisFromStart + Math.round(serviceTime);

            EventMap.addEvent(new Event(scaledMllisFromStart, Event.eventName.E2));
            EventMap.addEvent(new Event(serviceFinishTime, Event.eventName.E4));

            Statistic.addToList((long)serviceTime);

        } else {
            worker2.makeBusy();
            //То же самое, но с E3
            double randomNumber = random.nextDouble();

            double serviceTime = -millisServiceTime * Math.log(randomNumber);
            long serviceFinishTime = scaledMllisFromStart + Math.round(serviceTime);

            EventMap.addEvent(new Event(scaledMllisFromStart, Event.eventName.E3));
            EventMap.addEvent(new Event(serviceFinishTime, Event.eventName.E5));

            Statistic.addToList((long)serviceTime);
        }
    }

    public void endService(Event.eventName e){
        if (e == Event.eventName.E4){
            worker1.makeFree();
        } else if (e == Event.eventName.E5){
            worker2.makeFree();
        }
    }
}
