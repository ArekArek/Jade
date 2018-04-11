package com.tuco.server;

import com.tuco.ui.StationGui;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

import java.util.Random;

public class Station extends Agent {

    private static final String SERVICE_NAME = "weather";
    private static final String SERVICE_TYPE = "weather-station";
    private StationGui stationGui;
    private Random random = new Random();
    private String stationName;

    protected void setup() {
        stationName = CountriesConstants.getCountry();
        initializeGUI();

        registerInDF();

        runTemperatureTask();

        addBehaviour(new OfferTemperatureBehaviour(this));
    }

    private void initializeGUI() {
        stationGui = new StationGui(this);
        float initialTemperature = random.nextInt(400) - 100;
        initialTemperature /= 10;
        stationGui.setTemperature(String.valueOf(initialTemperature));
        stationGui.display();
    }

    private void registerInDF() {
        DFAgentDescription agentDescription = new DFAgentDescription();
        agentDescription.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(SERVICE_TYPE);
        sd.setName(SERVICE_NAME);
        agentDescription.addServices(sd);
        try {
            DFService.register(this, agentDescription);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    private void runTemperatureTask() {
        updateTemperature();

        int delay = random.nextInt(4000);
        delay += 1000;

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        runTemperatureTask();
                    }
                },
                delay
        );
    }

    private void updateTemperature() {
        float newTemperature = Float.valueOf(stationGui.getTemperature());
        newTemperature += (random.nextInt(400) - 200) / 100.0;
        stationGui.setTemperature(String.format("%.2f", newTemperature));
    }

    protected void takeDown() {
        deregisterFromDF();
    }

    private void deregisterFromDF() {
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    public String getTemperature() {
        return stationGui.getTemperature();
    }

    public String getStationName() {
        return stationName;
    }
}