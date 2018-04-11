package com.tuco.client;

import com.tuco.ui.CheckerGui;
import jade.core.AID;
import jade.core.Agent;

import java.util.HashMap;
import java.util.Map;

public class Checker extends Agent {

    private CheckerGui checkerGui;
    private AID[] temperatureAgents;
    public boolean searching = false;

    protected void setup() {
        checkerGui = new CheckerGui(new Checker());
        Map<String, Float> mapa = new HashMap<>();
        mapa.put("plple", 12.12f);
        mapa.put("fofofof", 35.1f);
        mapa.put("qwsda", -1.34f);
        checkerGui.display();

        checkerGui.updateTemperatureList(mapa);

        int interval = 10000;
        addBehaviour(new CyclicTemperatureBehaviour(this, interval));
    }

    public void updateTemperatures(Map<String, Float> temperatures){
        checkerGui.updateTemperatureList(temperatures);
    }

    protected void takeDown() { //opcjonalnie
        // operacje wykonywane bezpośrednio przed usunięciem agenta
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