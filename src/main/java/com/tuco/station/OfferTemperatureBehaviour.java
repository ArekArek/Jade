package com.tuco.station;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class OfferTemperatureBehaviour extends CyclicBehaviour {
    private Station station;

    public OfferTemperatureBehaviour(Station station) {
        this.station = station;
    }

    @Override
    public void action() {
        MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.REQUEST);
        ACLMessage msg = myAgent.receive(mt);
        if (msg != null) {
            processMessage(msg);
        } else {
            block();
        }
    }

    private void processMessage(ACLMessage msg) {
        ACLMessage reply = msg.createReply();
        reply.setPerformative(ACLMessage.CONFIRM);
        reply.setContent(station.getStationName() + ";" + station.getTemperature());
        myAgent.send(reply);
    }
}