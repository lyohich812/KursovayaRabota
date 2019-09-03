package ru.eltech.pavlov.model;

import ru.eltech.pavlov.gui.MainGUI;
import ru.eltech.pavlov.gui.ModelGUI;
import ru.eltech.pavlov.gui.SettingsGUI;

public class GUI {
    private static MainGUI mainGUI;
    private static ModelGUI modelGUI;
    private static SettingsGUI settingsGUI;

    public GUI(){
        mainGUI = new MainGUI();
        modelGUI = new ModelGUI();
        settingsGUI = new SettingsGUI();
    }

    public static void setMainGUI() {
        mainGUI.setModelGUI(modelGUI);
        mainGUI.setSettingsGUI(settingsGUI);
    }

    public static void setMainGUIEngine(Engine engine) {
        mainGUI.setEngine(engine);
    }

    public static void setSettingsGUIEngine(Engine engine) {
        settingsGUI.setEngine(engine);
    }

    public static ModelGUI getModelGUI() {
        return modelGUI;
    }

    public static void startGUI(){
        mainGUI.readyToGo();
    }
}
