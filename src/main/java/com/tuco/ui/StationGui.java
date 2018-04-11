package com.tuco.ui;

import com.tuco.server.Station;

import javax.swing.*;
import java.awt.*;

public class StationGui extends JFrame {
    private Station station;
    private JLabel valueLabel;
    private static int stationNumber = 0;


    public StationGui(Station s) throws HeadlessException {
        super(s.getLocalName());
        this.station = s;

        valueLabel = new JLabel();

        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 2));

        p.add(new JLabel("Temperature:"));
        p.add(valueLabel);
        getContentPane().add(p, BorderLayout.CENTER);

        setTitle(station.getStationName());
        setResizable(false);
    }

    public void display() {
        setSize(250, 75);
        centerWindow();
        setVisible(true);

    }

    private void centerWindow() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) screenSize.getWidth() / 2;
        int centerY = (int) screenSize.getHeight() / 2;
        setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
    }

    public String getTemperature() {
        return valueLabel.getText();
    }

    public void setTemperature(String newTemperature) {
        valueLabel.setText(newTemperature);
    }
}