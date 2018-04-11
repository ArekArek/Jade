package com.tuco.ui;

import com.tuco.client.Checker;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class CheckerGui extends JFrame {
    private Checker checker;
    private DefaultListModel<String> temperaturesListModel;

    public CheckerGui(Checker c) throws HeadlessException {
        super(c.getLocalName());
        this.checker = c;

        initializeTemperaturesList();

        setTitle("Weather checker");
    }

    private void initializeTemperaturesList() {
        temperaturesListModel = new DefaultListModel<>();
        JList temperatures = new JList<>(temperaturesListModel);
        temperatures.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        temperatures.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        JScrollPane listScroller = new JScrollPane(temperatures);
        getContentPane().add(listScroller, BorderLayout.CENTER);
    }

    public void display() {
        setSize(200, 150);
        centerWindow();
        setVisible(true);
    }

    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
    }

    public void updateTemperatureList(Map<String, Float> t) {
        temperaturesListModel.clear();
        for (Map.Entry<String, Float> entry : t.entrySet()) {
            temperaturesListModel.addElement(entry.getKey() + " : " + entry.getValue());
        }
    }
}