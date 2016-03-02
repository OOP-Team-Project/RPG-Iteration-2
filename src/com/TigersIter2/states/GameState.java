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
import com.TigersIter2.views.AvatarView;
import com.TigersIter2.views.MapView;
import com.sun.javafx.geom.Area;
import com.sun.javafx.runtime.SystemProperties;

import java.awt.*;

//GameState should initialize everything that is needed in GameState. This is because if you go back to the main menu for ex, and wish to start a new game
//when GameState gets reinitialized, this would all be possible - Sam
public class GameState extends State {


    private TerrainMap map;
    private Avatar avatar;
    private AvatarView avatarView;
    private MapView mapView;
    private AreaView areaView;
    //private EntityManager entityManager;
    //private ItemManager itemManager;

    private int testX, testY;


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
        testX = 0;
        testY=0;

        avatarView = new AvatarView(avatar);
        mapView = new MapView(map, avatar);
        areaView =  new AreaView(mapView,avatarView);
        this.add(areaView);

        System.out.println("GameState initialized");
    }

    @Override
    public void update() {
        map.update();
        testX++;
        testY++;
        avatar.update(testX, testY);
        System.out.println("Gamestate testX: " + testX);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.drawImage(WizardSprite.wizardDown1,360,360,null);
        g2d.dispose();
    }

    @Override
    public void draw(Graphics g) {
        //areaView.
    }

    @Override
    public void handleInput() {
    }
}
