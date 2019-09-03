package ru.eltech.pavlov.model;

public class Main {
    public static void main(String[] args) {
        Engine engine = new Engine();
        GUI app = new GUI();

        app.setMainGUI();
        app.setMainGUIEngine(engine);
        app.setSettingsGUIEngine(engine);

        engine.setModelGUI(app.getModelGUI());

        app.startGUI();
    }
}
