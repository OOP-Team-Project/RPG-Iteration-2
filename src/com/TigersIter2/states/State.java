package com.TigersIter2.states;


import java.awt.*;

public abstract class State{

    protected StateManager stateManager;

    public State(StateManager stateManager){
        this.stateManager = stateManager;
    }

    //initializes state
    public abstract void init();

    //update of State should call the updates of everything state is responsible for
    public abstract void update();

    //draw of State should draw everything the State is responsible for
    public abstract void draw(Graphics g);

    //takes in input for specific state
    public abstract void handleInput();

}
