package com.tuco.checker;

import jade.core.AID;
import jade.core.Agent;

import java.util.Map;

public class Checker extends Agent {

    private CheckerGui checkerGui;
    private AID[] temperatureAgents;
    public boolean searching = false;

    protected void setup() {
        checkerGui = new CheckerGui(new Checker());
        checkerGui.display();

        int interval = 10000;
        addBehaviour(new CyclicTemperatureBehaviour(this, interval));
    }

    public void updateTemperatures(Map<String, Float> temperatures) {
        checkerGui.updateTemperatureList(temperatures);
    }

    protected void takeDown() {
    }

    public AID[] getTemperatureAgents() {
        return temperatureAgents;
    }

    public AID getTemperatureAgent(int position) {
        return temperatureAgents[position];
    }

    public void setTemperatureAgents(AID[] temperatureAgents) {
        this.temperatureAgents = temperatureAgents;
    }

    public void setTemperatureAgent(int position, AID temperatureAgents) {
        this.temperatureAgents[position] = temperatureAgents;
    }
}