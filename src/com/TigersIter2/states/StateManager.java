package com.TigersIter2.states;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/29/2016.
 */

// TODO: add ability to pause
public class StateManager extends JPanel{

    private State state;

    private static final int NUMBER_OF_STATES = 2;
    private static final int INTRO = 0;
    public static final int GAME = 1;



    public StateManager(){
        setState(INTRO);
    }

    //kill state and set to new State (SL)
    public void setState(int nextState){

        state = null;

        if(nextState == INTRO){
            state = new IntroState(this);
            state.init();
        }
        else if (nextState == GAME){
            state = new GameState(this);
            state.init();
        }
    }

    public void update(){
        if (state!=null) state.update();
    }

    public void draw(Graphics g){
        if (state!=null) state.draw(g);
    }


}
