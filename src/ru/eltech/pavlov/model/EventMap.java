package ru.eltech.pavlov.model;


import java.util.*;

public class EventMap {

    private static List<Event> listOfEvents = new ArrayList<>();

    public static void addEvent(Event currentEvent){

        listOfEvents.add(currentEvent);

        Comparator<Event> eventTimeComparator = (a,b)->(Long.compare(a.getInstant(), b.getInstant()));

        listOfEvents.sort(eventTimeComparator);

    }

    public static Event getEvent(){
        if(listOfEvents.get(0).getThisEventName() == Event.eventName.E1){
            return listOfEvents.remove(0).clone();
        } else {
            Engine.sendEventToGUI(listOfEvents.get(0));
            return listOfEvents.remove(0).clone();
        }
    }

    public static long getFirstEventTimeEpochMillis(){
            return listOfEvents.get(0).getInstant();

    }

    public static boolean isEmpty(){
        return listOfEvents.isEmpty();
    }

    public static void clear(){
        listOfEvents.clear();
    }


}
