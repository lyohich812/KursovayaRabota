package ru.eltech.pavlov.model;

public class Event implements Cloneable{

    public static enum eventName {E0, E1, E2, E3, E4, E5}

    private eventName thisEventName;
    private long millis;

    Event(long millis, eventName thisEventName){
        this.millis = millis;
        this.thisEventName = thisEventName;
    }

    public long getInstant() {
        return millis;
    }

    public eventName getThisEventName() {
        return thisEventName;
    }

    @Override
    public Event clone(){
        try {
            return (Event)super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
    }

    @Override
    public String toString() {
        return "Event{" +
                "thisEventName=" + thisEventName +
                ", millis=" + millis +
                '}';
    }

}
