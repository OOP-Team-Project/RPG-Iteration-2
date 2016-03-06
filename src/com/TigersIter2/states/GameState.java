package com.TigersIter2.states;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.SmasherSprite;
import com.TigersIter2.assets.sprites.SneakSprite;
import com.TigersIter2.assets.sprites.TerrainSprite;
import com.TigersIter2.assets.sprites.WizardSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Smasher;
import com.TigersIter2.entities.Sneak;
import com.TigersIter2.entities.Summoner;
import com.TigersIter2.main.Controller;
import com.TigersIter2.maps.Map;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.states.State;
import com.TigersIter2.views.AreaView;
import com.TigersIter2.views.AvatarView;
import com.TigersIter2.views.MapView;
import com.sun.javafx.geom.Area;
import com.sun.javafx.runtime.SystemProperties;

import javax.swing.*;
import java.awt.*;

//GameState should initialize everything that is needed in GameState. This is because if you go back to the main menu for ex, and wish to start a new game
//when GameState gets reinitialized, this would all be possible - Sam
public class GameState extends State {

    private final String name = "GameState";

    //Model Data
    private TerrainMap map;
    private Avatar avatar;

    //Views
    private AvatarView avatarView;
    private MapView mapView;
    private AreaView areaView;
    //private EntityManager entityManager;
    //private ItemManager itemManager;
    private int counter = 0;

    //private int testX, testY;


    public GameState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {

        map = new TerrainMap();
        avatar = new Avatar();
        avatar.setOccupation(new Summoner());
        //pull in all pictures for GameState
        TerrainSprite.init();
        //Technically only one of these will need to be initialized
        WizardSprite.init();
        SmasherSprite.init();
        SneakSprite.init();

        avatarView = new AvatarView(avatar);
        mapView = new MapView(map, avatar);
        areaView =  new AreaView(mapView,avatarView);


        this.add(areaView);

        System.out.println("GameState initialized");

    }

    @Override
    public void update() {
        counter++;
        map.update();
        //System.out.println(controller.getXMovement() + ", " + controller.getyMovement());
        //testX++;
        //testY++;
        avatar.update(controller.getXMovement(),controller.getyMovement(),0);

        if (counter >= 200) {
            stateManager.setState(stateManager.INTRO);
            counter = 0;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, this.getWidth(), this.getHeight());//getHeight
        g2d.setColor(Color.BLUE);
        g2d.drawString("GameState paintComponent. Components: " + this.getComponentCount(), 260, 150);
        g2d.drawString("GetXmovement: " + controller.getXMovement(), 300, 300);
        g2d.drawString("GetYmovement: " + controller.getyMovement(), 300, 320);
        g2d.dispose();
    }

    @Override
    public void handleInput() {
    }

    @Override
    public String returnName() {
        return name;
    }
}
