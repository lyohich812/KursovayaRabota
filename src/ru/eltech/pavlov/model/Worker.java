package ru.eltech.pavlov.model;

public class Worker {

    private boolean isBusy;

    public Worker(){
        isBusy = false;
    }

    public void makeBusy(){
        isBusy = true;
    }

    public void makeFree(){
        isBusy = false;
    }

    public boolean getState() {
        return !isBusy;
    }

}
