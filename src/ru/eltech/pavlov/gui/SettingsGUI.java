package ru.eltech.pavlov.gui;

import ru.eltech.pavlov.model.Engine;
import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class SettingsGUI extends JFrame {
    private JLabel runTimeLabel;
    private JLabel scaleTimeLabel;
    private JLabel upTimeLabel;
    private JLabel serveTimeLabel;
    private JLabel queueLimitLabel;
    private JTextField runTime;
    private JTextField scaleTime;
    private JTextField averageUpcomingTime;
    private JTextField averageServeTime;
    private JTextField queueLimit;
    private JButton saveChanges;
    private JTextArea errors;
    private JScrollPane errorsPane;

    private Engine engine;


    public SettingsGUI(){
        super("Settings");

        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);


        runTimeLabel = new JLabel("Время работы программы (сек)");
        scaleTimeLabel = new JLabel("Масштаб времени");
        upTimeLabel = new JLabel("Среднее время прихода нового рабочего (мин)");
        serveTimeLabel = new JLabel("Среднее время обслуживание рабочего (мин)");
        queueLimitLabel = new JLabel("Макс. человек в очереди");

        runTime = new JTextField("10");
        scaleTime = new JTextField("3600");
        averageUpcomingTime = new JTextField("10");
        averageServeTime = new JTextField("12");
        queueLimit = new JTextField("5");

        saveChanges = new JButton("Сохранить");

        errors = new JTextArea("ERRORS:\n");
        DefaultCaret caret = (DefaultCaret) errors.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        errors.setEditable(false);

        errorsPane = new JScrollPane();
        errorsPane.setPreferredSize(new Dimension(350, 150));
        errorsPane.setViewportView(errors);

        JSplitPane splitPanel = new JSplitPane();
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel inputPanel = new JPanel();

        getContentPane().setLayout(new GridLayout());

        getContentPane().add(splitPanel);

        splitPanel.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        splitPanel.setDividerLocation(0.5);
        splitPanel.setLeftComponent(leftPanel);
        splitPanel.setRightComponent(rightPanel);

        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(runTimeLabel);
        leftPanel.add(runTime);
        leftPanel.add(scaleTimeLabel);
        leftPanel.add(scaleTime);
        leftPanel.add(upTimeLabel);
        leftPanel.add(averageUpcomingTime);
        leftPanel.add(serveTimeLabel);
        leftPanel.add(averageServeTime);
        leftPanel.add(queueLimitLabel);
        leftPanel.add(queueLimit);

        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.X_AXIS));

        inputPanel.add(saveChanges);

        leftPanel.add(inputPanel);

        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.add(errorsPane);

        saveChanges.addActionListener(new SaveChanges());

        this.pack();
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    private class SaveChanges implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

            int currentAvergeServeTime = engine.getAverageServiceTime();
            int currentAverageUpcomingTime = engine.getAverageUpcomingTime();
            int currentQueueLimit = engine.getQueueLimit();
            int currentRunTime = engine.getRunTime();
            int currentScaleTime = engine.getScaleTime();

            try {

                engine.setAverageServiceTime(Integer.parseInt(averageServeTime.getText()));
                engine.setAverageUpcomingTime(Integer.parseInt(averageUpcomingTime.getText()));
                engine.setQueueLimit(Integer.parseInt(queueLimit.getText()));
                engine.setRunTime(Integer.parseInt(runTime.getText()));
                engine.setScaleTime(Integer.parseInt(scaleTime.getText()));

                errors.append("Настройки успешно сохранены\n");

            } catch (Exception ex) {

                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                ex.printStackTrace(printWriter);
                String stackTrace = stringWriter.toString();
                errors.append(stackTrace);

                engine.setAverageUpcomingTime(currentAverageUpcomingTime);
                engine.setAverageServiceTime(currentAvergeServeTime);
                engine.setQueueLimit(currentQueueLimit);
                engine.setRunTime(currentRunTime);
                engine.setScaleTime(currentScaleTime);

            }
        }
    }

}
