package ru.eltech.pavlov.gui;

import ru.eltech.pavlov.model.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainGUI extends JFrame {
    private JButton startModelingButton;
    private JButton stopModelingButton;
    private JButton changeParametersButton;

    private ModelGUI modelGUI;
    private SettingsGUI settingsGUI;
    private Engine engine;
    private boolean isStarted;


    public MainGUI() {
        super("MainGUI");
        startModelingButton = new JButton("start");
        stopModelingButton = new JButton("stop");
        changeParametersButton = new JButton("settings");
        isStarted = false;


        /////////

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        stopModelingButton.setEnabled(false);
        startModelingButton.setEnabled(false);
        changeParametersButton.setEnabled(false);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(3,1));
        container.setPreferredSize(new Dimension(250,100));

        container.add(startModelingButton);
        container.add(stopModelingButton);
        container.add(changeParametersButton);

        startModelingButton.addActionListener(new StartModeling());
        stopModelingButton.addActionListener(new StopModeling());
        changeParametersButton.addActionListener(new ChangeParameters());


        this.pack();

    }

    private Thread runModel;

    private class StartModeling implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            startModelingButton.setEnabled(false);
            stopModelingButton.setEnabled(true);
            changeParametersButton.setEnabled(false);
            settingsGUI.setVisible(false);
            modelGUI.setVisible(true);
            runModel = new Thread(engine);
            runModel.start();
        }
    }

    private class StopModeling implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            startModelingButton.setEnabled(true);
            stopModelingButton.setEnabled(false);
            changeParametersButton.setEnabled(true);
            modelGUI.appendText("////////////////////////////////////////////////////////\n\n");
            engine.stop();
            engine = new Engine();
            EventMap.clear();
            Statistic.clear();
            GUI.setSettingsGUIEngine(engine);
            GUI.setMainGUIEngine(engine);
        }
    }

    private class ChangeParameters implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            settingsGUI.setVisible(true);
        }
    }

    public void setModelGUI(ModelGUI modelGUI) {
        this.modelGUI = modelGUI;
    }

    public void setSettingsGUI(SettingsGUI settingsGUI) {
        this.settingsGUI = settingsGUI;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void readyToGo(){
        startModelingButton.setEnabled(true);
        changeParametersButton.setEnabled(true);
        this.setVisible(true);
    }

}
