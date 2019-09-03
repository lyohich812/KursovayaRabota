package ru.eltech.pavlov.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class ModelGUI extends JFrame{
    private JTextArea outputText;
    private JScrollPane scrollPane;
    private JLabel mathExp;
    private JLabel standardDeviation;

    public ModelGUI(){
        super("Model");

        clear();

        JPanel topPane = new JPanel();
        JPanel bottomPane = new JPanel();
        JSplitPane splitPane = new JSplitPane();

        topPane.setPreferredSize(new Dimension(750,250));
        bottomPane.setPreferredSize(new Dimension(750,50));

        splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(0.5);
        splitPane.setTopComponent(topPane);
        splitPane.setBottomComponent(bottomPane);


        getContentPane().setLayout(new GridLayout());
        getContentPane().add(splitPane);

        topPane.setLayout(new BoxLayout(topPane, BoxLayout.PAGE_AXIS));
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.X_AXIS));

        topPane.add(scrollPane);
        bottomPane.add(standardDeviation);
        bottomPane.add(mathExp);

        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.pack();

    }
    
    public void clear(){
        outputText = new JTextArea(10,50);
        DefaultCaret caret = (DefaultCaret) outputText.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        standardDeviation = new JLabel();
        mathExp = new JLabel();

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(outputText);
        scrollPane.setPreferredSize(new Dimension(450,150));

        outputText.setEditable(false);
    }

    public void appendText(String outputText) {
        this.outputText.append(outputText);
    }

    public void setMathExp(String mathExp) {
        this.mathExp.setText(mathExp);
    }

    public void setStandardDeviation(String standardDeviation) {
        this.standardDeviation.setText(standardDeviation);
    }
}
