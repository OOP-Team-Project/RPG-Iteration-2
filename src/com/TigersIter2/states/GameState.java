package com.TigersIter2.states;

import com.TigersIter2.areaEffects.*;
import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.items.OneHandedWeaponItem;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.managers.*;
import com.TigersIter2.assets.sprites.*;
import com.TigersIter2.entities.*;
import com.TigersIter2.items.*;
import com.TigersIter2.main.Controller;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.skills.SkillTree;
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
    private AreaEffectManager aem;
    private AvatarMapInteract avatarMapInteract;
    private AreaEffect effect;

    //Views
    private AvatarView avatarView;
    private MapView mapView;
    private AreaView areaView;
    private List<VehicleView> vehicleViews;
    private List<NPCView> npcViews;
    private List<ItemView> itemViews;
    private List<AreaEffectView> areaEffectViews;
    private FooterView footerView;
    private StatusView statusView;
    private ControlView controlView;

    private SkillManagementView smv;

    private MessageView messageView;
    private AttackIndicatorView attackIndicatorView;


    public GameState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {
        controller.setBindings(); //added this to remove enter key functionality from previous menustates
        footerView = new FooterView();
        messageView = new MessageView();
        attackIndicatorView = new AttackIndicatorView();
        controlView = new ControlView(controller);
        vehicleViews = new ArrayList<VehicleView>();
        npcViews = new ArrayList<NPCView>();
        itemViews = new ArrayList<ItemView>();
        areaEffectViews = new ArrayList<AreaEffectView>();
        map = new TerrainMap(StaticVar.map2);
        avatar = new Avatar();
        View.setAvatar(avatar);
        avatarMapInteract = new AvatarMapInteract(avatar, map);
        TakeableItem potion = new Potion("Health Potion", 10);
        TakeableItem potion2 = new Potion("Health Potion", 10);
        TakeableItem potion3 = new Potion("Health Potion", 10);
        TakeableItem butterKnife = new RangedWeaponItem("Crossbow", 1, 1, 0);
        TakeableItem axe = new SpikedGlove("Battle Axe");
        TakeableItem breastplate = new Armor("Breastplate", 2, 4);
        ant = new AvatarNPCInteract(avatar, footerView);
        vehicleViews = new ArrayList<VehicleView>();
        itemManager = new ItemManager(avatar);


        SkillTree st = new SkillTree(avatar.getPlayerStats());
        smv = new SkillManagementView(st);


       // avatar.getInventory().addItem(new Potion("Health Potion"));
       // avatar.getInventory().addItem(new Potion("Strength Potion"));
       // avatar.getInventory().addItem(new Weapon("Battle Axe"));



        itemManager.addItem(potion);
        itemManager.addItem(butterKnife);
        itemManager.addItem(axe);
        itemManager.addItem(breastplate);
        avatar.getInventory().addItem(potion);
        avatar.getInventory().addItem(potion2);
        avatar.getInventory().addItem(potion3);
        avatar.getInventory().addItem(butterKnife);
        avatar.getInventory().addItem(axe);
        avatar.getInventory().addItem(breastplate);



        //avatar.setAttackTime(1000);

        ant = new AvatarNPCInteract(avatar, footerView);

//        //THIS IS ALL FOR TESTING. WILL NOT STAY HERE
//        ant.addVehicle(new Vehicle("Turtle", 5, true, false));
//        ant.addVehicle(new Vehicle("Turtle2", 2, true, true));
//        //ant.addMonster();
//        List<String> list = new ArrayList<String>();
//        list.add("My name is John Cena. I'm an internet sensation.");
//        list.add("What does anyone do anywhere?");
//        list.add("The Detroit Tigers?");
//        list.add("So many things.");
//        list.add("I suppose so.");
//        TakeableItem ohSword = new Sword("Sword");
//        itemManager.addItem(ohSword);
//        ant.addVillager(list, true, true, false);
//        ant.getNpcList().get(0).getInventory().addItem(ohSword);
//        ant.addMonster();
//
//        //testing for item interactions
//        Item item = new Key("Key", 1);
//        item.setLocation(new Location(10 * StaticVar.terrainImageWidth,10 * StaticVar.terrainImageHeight + 200,0));
//        Item obstacle = new Obstacle();
//        obstacle.setLocation(new Location(10 * StaticVar.terrainImageWidth + 400,10 * StaticVar.terrainImageHeight,0));
//        Item interactive = new Interactive(1);
//        interactive.setLocation(new Location(10 * StaticVar.terrainImageWidth + 200,10 * StaticVar.terrainImageHeight + 200,0));
//        Item oneShot = new OneShot();
//        oneShot.setLocation(new Location(10 * StaticVar.terrainImageWidth + 200,10 * StaticVar.terrainImageHeight,0));
//
//        itemManager.addItem(obstacle);
//        itemManager.addItem(item);
//        itemManager.addItem(interactive);
//        itemManager.addItem(oneShot);

        /**
         * Rokas's map. First initializing monsters/villages/vehicles
          */

        Vehicle v1 = new Vehicle("Turtle", 5, true, false);
        Location l1 = LocationConverter.HexLocationToPixel(new Location(2,1,0));
        l1.incrementX(+45);
        l1.incrementY(+35);
        v1.setLocation(l1);
        ant.addVehicle(v1);
        /**
         * Now initializing items
         */
        Item item = new Key("Key", 1);
        item.setLocation(new Location(10 * StaticVar.terrainImageWidth,10 * StaticVar.terrainImageHeight + 200,0));
        Item obstacle = new Obstacle();
        Location l2 = LocationConverter.HexLocationToPixel(new Location(3,5,0));
        l2.incrementX(-45);
//        obstacle.setLocation(l2);
        Item interactive = new Interactive(1);
        interactive.setLocation(l2);
        Item oneShot = new OneShot();
        oneShot.setLocation(new Location(10 * StaticVar.terrainImageWidth + 200,10 * StaticVar.terrainImageHeight,0));

//        itemManager.addItem(obstacle);
        itemManager.addItem(item);
        itemManager.addItem(interactive);
        itemManager.addItem(oneShot);


        // for testing Teleport
        aem = new AreaEffectManager(avatar);
        //Location dest = new Location(10 * StaticVar.terrainImageWidth +500,10 * StaticVar.terrainImageHeight+500, 0);
        //effect = new Teleport(dest);
        effect = new Trap();
        effect.setLocation(new Location(10 * StaticVar.terrainImageWidth-200,10 * StaticVar.terrainImageHeight,0));
        //effect.setPixelLocation(new Location(10 * StaticVar.terrainImageWidth,10 * StaticVar.terrainImageHeight+300,0));
        aem.addEffect(effect);
//
//        //pull in all pictures for GameState

        //Technically only one of these will need to be initialized
        WizardSprite.init();
        SmasherSprite.init();
        SneakSprite.init();
        VehicleSprite.init();
        VillagerSprite.init();
        MonsterSprite.init();
        ItemSprite.init();
        AreaEffectSprite.init();
        SkillsSprite.init();
        AttackSprite.init();

        avatarView = new AvatarView(avatar, map);
        statusView = new StatusView(avatar);
        for(Vehicle vv : ant.getVehicleList()) {
            vehicleViews.add(new VehicleView(vv, avatar, map));
        }
        for(NPC n : ant.getNpcList()){
            npcViews.add(new NPCView(n, avatar, map));
        }
        for(Item i : itemManager.getItemList()){
            itemViews.add(new ItemView(i, avatar, map));
        }

        for(AreaEffect aEffect : aem.getAreaEffects()){
            areaEffectViews.add(new AreaEffectView(aEffect, avatar, map));
        }

        mapView = new MapView(map, avatar);
        areaView =  new AreaView(mapView,avatarView, vehicleViews, footerView, statusView, npcViews, controlView, itemViews, areaEffectViews);
        this.add(messageView);
        this.add(attackIndicatorView);
        this.add(areaView);


        areaView.add(smv, 0);


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
            case 10:
                smv.toggle();
                controller.setSkillViewControls(smv.getDisplay());
                break;
            case 11:
                ant.startSkillsNotFromInteraction();
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
        int xMov = controller.getXMovement();
        int yMov = controller.getyMovement();
        boolean avatarCanMove = itemManager.checkTile(elapsed, controller.getXMovement(), controller.getyMovement()); //returns false if item is an obstacle
        if(avatarCanMove && avatarMapInteract.updateAvatarPos(elapsed, xMov, yMov)) {
            avatar.update(xMov, yMov, elapsed);
        }
        View.update(controller.getCameraXMovement(), controller.getCameraYMovement(), elapsed);
        aem.checkTile();
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
        else if(smv.getDisplay()) {
            int input = controller.getTradeMenuInput();
            smv.handleInput(input);
        }


        if (controller.getKeyPressed() == KeyEvent.VK_SPACE) {
            stateManager.setState(StateManager.INTRO);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        //setting background to gray somehow eliminates tile tearing caused by non-perfect hexagons(hexagons can't really by represented perfectly with pixels)
        g2d.setColor(new Color(100, 90, 90));
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

