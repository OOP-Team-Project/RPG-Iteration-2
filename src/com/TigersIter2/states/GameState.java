package com.TigersIter2.states;

import com.TigersIter2.assets.sprites.SmasherSprite;
import com.TigersIter2.assets.sprites.SneakSprite;
import com.TigersIter2.assets.sprites.TerrainSprite;
import com.TigersIter2.assets.sprites.WizardSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Smasher;
import com.TigersIter2.maps.Map;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.states.State;
import com.TigersIter2.views.AreaView;
import com.sun.javafx.geom.Area;

import java.awt.*;

//GameState should initialize everything that is needed in GameState. This is because if you go back to the main menu for ex, and wish to start a new game
//when GameState gets reinitialized, this would all be possible - Sam
public class GameState extends State {


    private TerrainMap map;
    private Avatar avatar;
    //private EntityManager entityManager;
    //private ItemManager itemManager;


    public GameState(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public void init() {
        map = new TerrainMap();
        avatar = new Avatar();
        avatar.setOccupation(new Smasher());
        //pull in all pictures for GameState
        TerrainSprite.init();
        //Technically only one of these will need to be initialized
        WizardSprite.init();
        SmasherSprite.init();
        SneakSprite.init();
    }

    @Override
    public void update() {
        map.update();
        //avatar.update();
    }

    @Override
    public void paintComponent(Graphics g) {

    }

//    @Override
//    public void draw(Graphics g) {
//        //areaView.
//    }

    @Override
    public void handleInput() {
    }
}
