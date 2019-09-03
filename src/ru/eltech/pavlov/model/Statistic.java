package ru.eltech.pavlov.model;

import java.util.ArrayList;
import java.util.List;

public class Statistic {
    private static List<Long> upcomingTimeList = new ArrayList<>();
    private static long mExp = 0;

    public static long mathExp(){
        mExp = 0;
        long listSum = upcomingTimeList.get(0);

        for (int i = 1; i < upcomingTimeList.size(); i++) {
            listSum += upcomingTimeList.get(i)/* - upcomingTimeList.get(i-1)*/;
        }

        mExp = listSum/upcomingTimeList.size();

        return mExp;
    }

    public static double stDeviation(){

        double deviation = Math.pow((double)upcomingTimeList.get(0) - mExp , 2);

        for (int i = 1; i < upcomingTimeList.size(); i++){
            deviation += Math.pow(((double)upcomingTimeList.get(i) - mExp ), 2);
        }

        deviation = deviation/upcomingTimeList.size();
        deviation = Math.sqrt(deviation);
        return deviation;
    }

    public static void addToList(long l){
            upcomingTimeList.add(l);
    }


    public static void clear(){
        upcomingTimeList.clear();
    }

    public static boolean isEmpty(){
        return upcomingTimeList.isEmpty();
    }
}
