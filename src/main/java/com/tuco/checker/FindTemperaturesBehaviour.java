package com.tuco.checker;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.HashMap;
import java.util.Map;

public class FindTemperaturesBehaviour extends Behaviour {

    private Checker checker;
    private int step = 0;
    private int repliesCnt = 0;
    private Map<String, Float> temperaturesFound = new HashMap<>();

    public FindTemperaturesBehaviour(Checker checker) {
        this.checker = checker;
    }

    @Override
    public void action() {
        switch (step) {
            case 0:
                System.out.println("STEP 0");
                ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                for (int i = 0; i < checker.getTemperatureAgents().length; ++i) {
                    request.addReceiver(checker.getTemperatureAgent(i));
                }
                request.setReplyWith("request_time: " + System.currentTimeMillis());
                myAgent.send(request);

                step = 1;
                break;
            case 1:
                System.out.println("STEP 1");
                MessageTemplate mt = MessageTemplate.MatchPerformative(ACLMessage.CONFIRM);
                ACLMessage msg = myAgent.receive(mt);
                if (msg != null) {
                    if (msg.getPerformative() == ACLMessage.CONFIRM) {
                        String[] parts = msg.getContent().split(";");
                        temperaturesFound.put(parts[0], Float.valueOf(parts[1]));
                    }
                    repliesCnt++;
                    if (repliesCnt >= checker.getTemperatureAgents().length) {
                        step = 2;
                    }
                } else {
                    block();
                }
                break;
            case 2:
                System.out.println("STEP 2");
                checker.updateTemperatures(temperaturesFound);

                checker.searching = false;
                repliesCnt = 0;
                temperaturesFound.clear();
                step = 3;
                break;

        }
    }

    @Override
    public boolean done() {
        return step == 3;
    }
}