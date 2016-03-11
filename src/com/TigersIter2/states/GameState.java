package com.TigersIter2.states;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.items.OneHandedWeapon;
import com.TigersIter2.items.Weapon;
import com.TigersIter2.location.Location;
import com.TigersIter2.managers.StateManager;
import com.TigersIter2.assets.sprites.*;
import com.TigersIter2.entities.*;
import com.TigersIter2.items.*;
import com.TigersIter2.main.Controller;
import com.TigersIter2.managers.AvatarNPCInteract;
import com.TigersIter2.managers.ItemManager;
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
    private ItemManager itemManager;

    //Views
    private AvatarView avatarView;
    private MapView mapView;
    private AreaView areaView;
    private List<VehicleView> vehicleViews;
    private List<NPCView> npcViews;
    private List<ItemView> itemViews;
    private FooterView footerView;
    private StatusView statusView;
    private ControlView controlView;
    //private EntityManager entityManager;
    //private ItemManager itemManager;


    public GameState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {

        footerView = new FooterView();
        controlView = new ControlView(controller);
        vehicleViews = new ArrayList<VehicleView>();
        npcViews = new ArrayList<NPCView>();
        itemViews = new ArrayList<ItemView>();
        map = new TerrainMap(StaticVar.map1);
        avatar = new Avatar();
        avatar.setOccupation(new Sneak());
        TakeableItem potion = new Potion("Health Potion", 10);
        TakeableItem butterKnife = new OneHandedWeapon("Butter Knife", 1);
        ant = new AvatarNPCInteract(avatar, footerView);
        vehicleViews = new ArrayList<VehicleView>();
        itemManager = new ItemManager(avatar);

        avatar.getInventory().addItem(potion);
        avatar.getInventory().addItem(butterKnife);


        avatar.setAttackTime(1000);
        ant = new AvatarNPCInteract(avatar, footerView);

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
        ant.addMonster();

        //testing for item interactions
        Item item = new Key("Key", 1);
        item.setLocation(new Location(10 * StaticVar.terrainImageWidth,10 * StaticVar.terrainImageHeight + 200,0));
        item.setPixelLocation(new Location(10 * StaticVar.terrainImageWidth,10 * StaticVar.terrainImageHeight + 200,0));
        Item obstacle = new Obstacle();
        obstacle.setLocation(new Location(10 * StaticVar.terrainImageWidth + 400,10 * StaticVar.terrainImageHeight,0));
        obstacle.setPixelLocation(new Location(10 * StaticVar.terrainImageWidth + 400,10 * StaticVar.terrainImageHeight,0));
        itemManager.addItem(obstacle);
        itemManager.addItem(item);


        //pull in all pictures for GameState

        //Technically only one of these will need to be initialized
        WizardSprite.init();
        SmasherSprite.init();
        SneakSprite.init();
        VehicleSprite.init();
        VillagerSprite.init();
        MonsterSprite.init();
        ItemSprite.init();

        avatarView = new AvatarView(avatar);
        statusView = new StatusView(avatar.getInventory(), avatar.getStats(), avatar.getEquipment());
        for(Vehicle vv : ant.getVehicleList()) {
            vehicleViews.add(new VehicleView(vv, avatar, map));
        }
        for(NPC n : ant.getNpcList()){
            npcViews.add(new NPCView(n, avatar, map));
        }
        for(Item i : itemManager.getItemList()){
            itemViews.add(new ItemView(i, avatar, map));
        }

        mapView = new MapView(map, avatar);
        areaView =  new AreaView(mapView,avatarView, vehicleViews, footerView, statusView, npcViews, controlView, itemViews);


        this.add(areaView);

        System.out.println("GameState initialized");

    }

    private void handleControllerInput(){
        int optionSelected = controller.getOptionSelected();
        switch(optionSelected){
            case 0:
                System.out.println("Attacking");
                //ant.attack();
                break;
            case 6:
                ant.mountVehicle();
                break;
            case 7:
                statusView.toggle();
                controller.setStatusViewControls(statusView.getDisplay());
                break;
            case 8:
                ant.attack();
                break;
            case 9:
                controlView.toggle();
                controller.setControlViewControls(controlView.getDisplay());
                break;
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
        boolean avatarCanMove = itemManager.checkTile(elapsed, controller.getXMovement(), controller.getyMovement()); //returns false if item is an obstacle
        if(avatarCanMove) {
            avatar.update(controller.getXMovement(), controller.getyMovement(), elapsed);
        }
        View.update(controller.getCameraXMovement(), controller.getCameraYMovement(), elapsed);
        ant.checkTile();
        handleControllerInput();

        if(controlView.getDisplay()) {
            int input = controller.getTradeMenuInput();
            controlView.handleInput(input);
            //if(input == 5){
                //controlView.toggle();
                //controller.setStatusViewControls(controlView.getDisplay());
            //}
        }
        else if(avatar.getTrading()){
            controller.tradeBindings();
            int input = controller.getTradeMenuInput();
            ant.navigateTradeMenu(input);
            if(input == 5){
                controller.revertTradeBindings();
            }
        }
        else if(statusView.getDisplay()){
            int input = controller.getTradeMenuInput();
            statusView.handleInput(input);
            if(input == 5){
                statusView.toggle();
                controller.setStatusViewControls(statusView.getDisplay());
            }
        }


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
