package ru.eltech.pavlov.model;

public class Queue {

    private int queueLimit;
    private int currentSize;
    private Service service;

    public Queue(int queueLimit, long serviceTime){
        this.queueLimit = queueLimit;
        currentSize = 0;
        service = new Service(serviceTime);
    }

    public boolean isFull(){
        return (queueLimit > currentSize ? false : true);
    }

    public boolean isEmpty(){
        return (currentSize > 0 ? false : true);
    }

    public boolean addToQueue(){
        if (!isFull()){
            currentSize++;
            return true;
        }
        return false;
    }

    public boolean removeFromQueue(){
        if (!isEmpty()){
            currentSize--;
            return true;
        }
        return false;
    }

    public boolean toService(long scaledMillisFromStart){
        if(service.isFree() && !isEmpty()){
            removeFromQueue();
            service.startService(scaledMillisFromStart);
            return true;
        }
        return false;
    }

    public void makeServiceFree(Event e){
        service.endService(e.getThisEventName());
    }


}
