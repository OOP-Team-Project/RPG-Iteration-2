package com.TigersIter2.states;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.maps.Map;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.states.State;
import com.TigersIter2.views.AreaView;
import com.sun.javafx.geom.Area;

import java.awt.*;


public class GameState extends State {


    private TerrainMap map;
    //private EntityManager entityManager;
    //private ItemManager itemManager;


    public GameState(StateManager stateManager){
        super(stateManager);
        map = new TerrainMap();
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
    public void draw(Graphics g) {
        //areaView.
    }

    @Override
    public void handleInput() {

    }
}
