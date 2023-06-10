package SMA;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;


import java.util.Random;

import static SMA.Utils.*;

public class LearningAgent extends Agent {


    ACLMessage aclMessage=new ACLMessage(ACLMessage.INFORM);



    @Override
    protected void setup() {

        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour();
        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                runQLearning();

            }
        });

        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                DFAgentDescription dfAgentDescription=new DFAgentDescription();
                ServiceDescription serviceDescription=new ServiceDescription();
                serviceDescription.setType("ql");
                dfAgentDescription.addServices(serviceDescription);
                DFAgentDescription[] dfAgentDescriptions ;
                try {
                    dfAgentDescriptions= DFService.search(getAgent(), dfAgentDescription);
                } catch (FIPAException e) {
                    throw new RuntimeException(e);
                }
                aclMessage.addReceiver(dfAgentDescriptions[0].getName());
                showResult();

            }
        });
        addBehaviour(sequentialBehaviour);
    }


    @Override
    protected void takeDown() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            throw new RuntimeException(e);
        }

    }


    private void resetState() {
        stateI = 0;
        stateJ = 0;
    }

    private int chooseAction(double eps) {
        Random random = new Random();
        int action = 0;
        double bestQvalue = 0;
        if (random.nextDouble() < eps) {
            action = random.nextInt(ACTION_SIZE);

        } else {

            // ** here we exploit our QTable to choose the best action **

            // calculating the index of the state
            int state = stateI * GRID_SIZE + stateJ;

            //choosing the best action
            for (int i = 0; i < ACTION_SIZE; i++) {
                if (QTABLE[state][i] > bestQvalue) {
                    bestQvalue = QTABLE[state][i];
                    action = i;
                }

            }

        }
        return action;
    }

    private int executeAction(int action) {
        stateI = Math.max(0,Math.min(ACTIONS[action][0] + stateI, GRID_SIZE -1));
        stateJ = Math.max(0,Math.min(ACTIONS[action][1] + stateJ, GRID_SIZE -1));

        //return next state
        return stateI * GRID_SIZE + stateJ;
    }

    public void runQLearning() {
        int it = 0;
        int currentState;
        int nextState;
        int action;
        int action2;


        while (it < MAX_EPOCH) {

            resetState();
            while (!isfinished()) {
                currentState = stateI * GRID_SIZE + stateJ;
                action = chooseAction(0.4);
                nextState = executeAction(action);

                action2 = chooseAction(0);

                QTABLE[currentState][action] = QTABLE[currentState][action] + ALPHA * (GRID[stateI][stateJ] + GAMMA * QTABLE[nextState][action2] - QTABLE[currentState][action]);
            }
            it++;
        }


    }

    private boolean isfinished() {
        return GRID[stateI][stateJ] == 1;
    }

    private void showResult() {

        resetState();
        while (!isfinished()) {
            int act = chooseAction(0);
            if (act==0){
                aclMessage.setContent("State : (" + stateI+","+stateJ + ") Action : " + act+" --> Gauche");
            }else if (act==1){
                aclMessage.setContent("State : (" + stateI+","+stateJ + ") Action : " + act+" --> Droite");
            }else if (act==2){
                aclMessage.setContent("State : (" + stateI+","+stateJ + ") Action : " + act+" --> Bas");
            }else{
                aclMessage.setContent("State : (" + stateI+","+stateJ + ") Action : " + act+" --> Haut");
            }

            send(aclMessage);
            executeAction(act);
        }
        aclMessage.setContent("point But : ("+stateI+","+stateJ+")");
        send(aclMessage);
        aclMessage.setContent("\n\n");
    }

}

