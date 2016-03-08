package com.TigersIter2.states;

import com.TigersIter2.managers.StateManager;
import com.TigersIter2.assets.sprites.*;
import com.TigersIter2.entities.*;
import com.TigersIter2.items.Potion;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.main.Controller;
import com.TigersIter2.managers.AvatarNPCInteract;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.views.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

//GameState should initialize everything that is needed in GameState. This is because if you go back to the main menu for ex, and wish to start a new game
//when GameState gets reinitialized, this would all be possible - Sam
public class GameState extends State {

    private final String name = "GameState";

    //Model Data
    private TerrainMap map;
    private Avatar avatar;
    private Vehicle vehicle;
    private AvatarNPCInteract ant;

    //Views
    private AvatarView avatarView;
    private MapView mapView;
    private AreaView areaView;
    private List<VehicleView> vehicleViews;
    private FooterView footerView;
    //private EntityManager entityManager;
    //private ItemManager itemManager;


    public GameState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {

        footerView = new FooterView();
        map = new TerrainMap();
        avatar = new Avatar();
        avatar.setOccupation(new Summoner());
        avatar.getInventory().addItem(new Potion("Health Potion"));
        ant = new AvatarNPCInteract(avatar, footerView);
        vehicleViews = new ArrayList<VehicleView>();

        //THIS IS ALL FOR TESTING. WILL NOT STAY HERE
        ant.addVehicle(new Vehicle("Turtle", 5, true, true));
        ant.addVehicle(new Vehicle("Turtle2", 2, false, true));
        //ant.addMonster();
        List<String> list = new ArrayList<String>();
        list.add("My name is John Cena. I'm an internet sensation.");
        list.add("What does anyone do anywhere?");
        list.add("The Detroit Tigers?");
        list.add("So many things.");
        list.add("I suppose so.");
        ant.addVillager(list, true, true, false);


        //pull in all pictures for GameState

        //Technically only one of these will need to be initialized
        WizardSprite.init();
        SmasherSprite.init();
        SneakSprite.init();
        VehicleSprite.init();

        avatarView = new AvatarView(avatar);
        for(Vehicle vv : ant.getVehicleList()) {
            vehicleViews.add(new VehicleView(vv, avatar, map));
        }
        mapView = new MapView(map, avatar);
        areaView =  new AreaView(mapView,avatarView, vehicleViews, footerView);


        this.add(areaView);

        System.out.println("GameState initialized");

    }

    private void handleControllerInput(){
        int optionSelected = controller.getOptionSelected();
        switch(optionSelected){
            case 0:
                System.out.println("Attacking");
               // ant.attack();
                break;
            case 6:
                ant.mountVehicle();
            case -1:
                break;
            default:
                ant.chooseOption(optionSelected);
                break;
        }
        controller.resetOptionSelected();
    }

    @Override
    public void update(long elapsed) {
        map.update();
        avatar.update(controller.getXMovement(),controller.getyMovement(), elapsed);
        View.update(controller.getCameraXMovement(), controller.getCameraYMovement(), elapsed);
        ant.checkTile();
        handleControllerInput();

        if (controller.getKeyPressed() == KeyEvent.VK_SPACE) {
            stateManager.setState(StateManager.INTRO);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        //setting background to gray somehow eliminates tile tearing caused by non-perfect hexagons(hexagons can't really by represented perfectly with pixels)
        g2d.setColor(Color.RED);
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
