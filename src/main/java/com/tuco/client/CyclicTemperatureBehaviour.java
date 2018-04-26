package com.tuco.client;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

public class CyclicTemperatureBehaviour extends TickerBehaviour{

    private static final String SERVICE_TYPE = "weather-station";

    private Checker checker;

    public CyclicTemperatureBehaviour(Checker checker, long period) {
        super(checker, period);
        this.checker=checker;
    }

    @Override
    protected void onTick() {
        if (!checker.searching) {
            checker.searching=true;
            System.out.println("Szukam temperatur ");
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType(SERVICE_TYPE);
            template.addServices(sd);
            try {
                DFAgentDescription[] result = DFService.search(myAgent, template);
                checker.setTemperatureAgents(new AID[result.length]);
                for (int i = 0; i < result.length; ++i) {
                    checker.setTemperatureAgent(i, result[i].getName());
                }
            } catch (FIPAException fe) {
                fe.printStackTrace();
            }

            checker.addBehaviour(new FindTemperaturesBehaviour(checker));
        }
    }
}