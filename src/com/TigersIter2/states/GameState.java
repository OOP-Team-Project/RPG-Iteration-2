package com.TigersIter2.states;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.maps.Map;
import com.TigersIter2.states.State;
import com.TigersIter2.views.AreaView;
import com.sun.javafx.geom.Area;


public class GameState extends State {


    public GameState(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        //map.update();
        //avatar.update();
    }

    @Override
    public void draw() {
        //areaView.
    }

    @Override
    public void handleInput() {

    }
}
