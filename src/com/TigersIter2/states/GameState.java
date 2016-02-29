package com.TigersIter2.states;

import com.TigersIter2.maps.Map;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.states.State;


public class GameState extends State {

    private TerrainMap map;
    //private EntityManager entityManager;
    //private ItemManager itemManager;

    //private Player player;

    public GameState(){
        map = new TerrainMap();
        //itemManager = new ItemManager();
    }

    @Override
    public void update() {

    }

    @Override
    public void draw() {

    }
}
