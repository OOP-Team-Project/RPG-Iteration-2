package com.TigersIter2.states;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.*;
import com.TigersIter2.entities.*;
import com.TigersIter2.main.Controller;
import com.TigersIter2.maps.Map;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.states.State;
import com.TigersIter2.views.AreaView;
import com.TigersIter2.views.AvatarView;
import com.TigersIter2.views.MapView;
import com.TigersIter2.views.VehicleView;
import com.sun.javafx.geom.Area;
import com.sun.javafx.runtime.SystemProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

//GameState should initialize everything that is needed in GameState. This is because if you go back to the main menu for ex, and wish to start a new game
//when GameState gets reinitialized, this would all be possible - Sam
public class GameState extends State {

    private final String name = "GameState";

    //Model Data
    private TerrainMap map;
    private Avatar avatar;
    private Vehicle vehicle;
    private NPC monster;
    private AvatarNPCInteract ant;

    //Views
    private AvatarView avatarView;
    private MapView mapView;
    private AreaView areaView;
    private VehicleView vehicleView;
    //private EntityManager entityManager;
    //private ItemManager itemManager;


    public GameState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {

        map = new TerrainMap();
        avatar = new Avatar();
        avatar.setOccupation(new Summoner());
        vehicle = new Vehicle("Turtle", 5, false, true);
        avatar.setVehicle(vehicle);
        monster = new Monster();
        //pull in all pictures for GameState

        //Technically only one of these will need to be initialized
        WizardSprite.init();
        SmasherSprite.init();
        SneakSprite.init();
        VehicleSprite.init();

        avatarView = new AvatarView(avatar);
        vehicleView = new VehicleView(vehicle);
        mapView = new MapView(map, avatar);
        areaView =  new AreaView(mapView,avatarView, vehicleView);


        this.add(areaView);

        System.out.println("GameState initialized");

    }

    private void touchingNPC(){
        // for all entities in the list
        if(avatar.getLocation().getX() == monster.getLocation().getX() && avatar.getLocation().getY() == monster.getLocation().getY()){
            if(ant == null) {
                System.out.println("Touching an NPC");
                ant = new AvatarNPCInteract(avatar, monster);
            }
            else{
                if (controller.getKeyPressed() == KeyEvent.VK_F) {
                    ant.talk("HELLO");
                    controller.setKeyPressed(0);
                }
            }
        }
        else{
            ant = null;
        }
    }

    @Override
    public void update() {
        map.update();
        avatar.update(controller.getXMovement(),controller.getyMovement(),0);
        touchingNPC();

        if (controller.getKeyPressed() == KeyEvent.VK_SPACE) {
            stateManager.setState(StateManager.INTRO);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        //setting background to gray somehow eliminates tile tearing caused by non-perfect hexagons(hexagons can't really by represented perfectly with pixels)
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,0, this.getWidth(), this.getHeight());//getHeight
//        g2d.setColor(Color.BLUE);
//        g2d.drawString("GameState paintComponent. Components: " + this.getComponentCount(), 260, 150);
//        g2d.drawString("GetXmovement: " + controller.getXMovement(), 300, 300);
//        g2d.drawString("GetYmovement: " + controller.getyMovement(), 300, 320);
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
