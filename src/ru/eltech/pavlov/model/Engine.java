package ru.eltech.pavlov.model;

import ru.eltech.pavlov.gui.*;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Engine implements Cloneable, Runnable{

    private static int averageUpcomingTime;
    private static int averageServiceTime;
    private static int queueLimit;
    private static int scaleTime;
    private static int runTime;
    private static Instant startTime;

    private static ModelGUI modelGUI;


    public Engine(){
        runTime = 10;
        scaleTime = 3600;
        averageUpcomingTime = 10;
        averageServiceTime = 12;
        queueLimit = 5;
        System.out.println(toString());
    }

    public void setAverageUpcomingTime(int averageUpcomingTime) {
        this.averageUpcomingTime = averageUpcomingTime;
    }

    public void setAverageServiceTime(int averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }

    public void setQueueLimit(int queueLimit) {
        this.queueLimit = queueLimit;
    }

    public void setScaleTime(int scaleTime) {
        this.scaleTime = scaleTime;
    }

    public void setRunTime(int runTime) {
        this.runTime = runTime;
    }

    public void setModelGUI(ModelGUI modelGUI){
        this.modelGUI = modelGUI;
    }

    public int getAverageUpcomingTime() {
        return averageUpcomingTime;
    }

    public int getAverageServiceTime() {
        return averageServiceTime;
    }

    public int getQueueLimit() {
        return queueLimit;
    }

    public int getScaleTime() {
        return scaleTime;
    }

    public int getRunTime() {
        return runTime;
    }

    @Override
    public String toString() {
        return "Engine{" +
                "averageUpcomingTime=" + averageUpcomingTime +
                ", averageServiceTime=" + averageServiceTime +
                ", queueLimit=" + queueLimit +
                ", scaleTime=" + scaleTime +
                ", runTime=" + runTime +
                '}';
    }

    private boolean isRunnable = true;

    public void start(){

        Model model = new Model(scaleTime, queueLimit, averageServiceTime, averageUpcomingTime);

        EventMap.addEvent(new Event(0, Event.eventName.E0));

        long currentTime = 0;

        startTime = Instant.now();
        while (currentTime != runTime * 1000 && isRunnable){
            currentTime = Instant.now().toEpochMilli()-startTime.toEpochMilli();

            model.step(currentTime);

        }
    }

    public void stop(){
        isRunnable = false;
    }

    public static void sendEventToGUI(Event e){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");


        if (!Statistic.isEmpty()) {
            modelGUI.setMathExp("M(мин): " + Statistic.mathExp() / 1000 / 60 + "\t");
            modelGUI.setStandardDeviation("S(мин): " + (Math.round((Statistic.stDeviation() / 1000 / 60) * 100)) / 100 + "\t");
        }

        switch (e.getThisEventName()){
            case E0: {
                modelGUI.appendText(dtf.format(
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(e.getInstant()), ZoneId.of("GMT"))) +
                        " - E0 - Начало моделирования\n" );
                break;
            }
            case E1: {
                modelGUI.appendText(dtf.format(
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(e.getInstant()), ZoneId.of("GMT"))) +
                         "  - E1 - Приход нового рабочего\n");
                break;
            }
            case E2 : {
                modelGUI.appendText(dtf.format(
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(e.getInstant()), ZoneId.of("GMT"))) +
                        "  - E2 - Рабочий обслуживается на складе 1\n");

                break;
            }
            case E3: {
                modelGUI.appendText(dtf.format(
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(e.getInstant()), ZoneId.of("GMT"))) +
                        "  - E3 - Рабочий обслуживается на складе 2\n");
                break;
            }
            case E4: {
                modelGUI.appendText(dtf.format(
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(e.getInstant()), ZoneId.of("GMT"))) +
                        "  - E4 - Завершение обслуживания рабочего на складе 1\n");
                break;
            }
            case E5: {
                modelGUI.appendText(dtf.format(
                        LocalDateTime.ofInstant(
                                Instant.ofEpochMilli(e.getInstant()), ZoneId.of("GMT"))) +
                        "  - E5 - Завершение обслуживания рабочего на складе 2\n");
                break;
            }
            default: break;
        }

    }

    public static void sendEventToGUI(String s){
        modelGUI.appendText(s);
    }

    @Override
    public void run() {
        System.out.println("Поток открыт\n");
        System.out.println(toString());
        start();
        System.out.println("Поток закрыт\n");
    }
}
