package com.TigersIter2.states;


import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.main.Controller;

import javax.swing.*;
import java.awt.*;

public abstract class State extends JComponent{

    protected StateManager stateManager;
    protected Controller controller;

    public State(StateManager stateManager, Controller controller){
        this.setLayout(new OverlayLayout(this));
        this.setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        this.stateManager = stateManager;
        this.controller = controller;
    }

    //initializes state
    public abstract void init();

    //update of State should call the updates of everything state is responsible for
    //public abstract void update();
    public abstract void update(long elapsed);

    //takes in input for specific state
    public abstract void handleInput();

    //for testing purposes
    public abstract String returnName();

}
