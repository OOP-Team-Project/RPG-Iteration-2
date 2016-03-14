package com.TigersIter2.states;

import com.TigersIter2.areaEffects.*;
import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.*;
import com.TigersIter2.entities.*;
import com.TigersIter2.items.*;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.main.Controller;
import com.TigersIter2.managers.*;
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
    private Pet pet;
    private Vehicle vehicle;
    private AvatarNPCInteract ant;
    private ItemManager itemManager;
    private PetManager petManager;
    private AreaEffectManager aem;
    private AvatarMapInteract avatarMapInteract;
    private List<NPCMapInteract> npcMapInteract;



    //Views
    private AvatarView avatarView;
    private PetView petView;
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
        npcMapInteract = new ArrayList<NPCMapInteract>();
        map = new TerrainMap(StaticVar.map2);
        avatar = new Avatar();
        //Location avatarLoc = LocationConverter.HexLocationToPixel(new Location(8,11));
        //avatar.setLocation(avatarLoc);
        View.setAvatar(avatar);
        avatarMapInteract = new AvatarMapInteract(avatar, map);

        ant = new AvatarNPCInteract(avatar, footerView);
        vehicleViews = new ArrayList<VehicleView>();
        itemManager = new ItemManager(avatar);

        pet = new Pet("Crab", avatar);
        petManager = new PetManager(pet, itemManager, avatar, map);


        SkillTree st = new SkillTree(avatar.getPlayerStats());

        smv = new SkillManagementView(st);

        ant = new AvatarNPCInteract(avatar, footerView);

        aem = new AreaEffectManager(avatar);


        /**
         * Rokas's map. First initializing monsters/villagers/vehicles
          */

        //vehicles
        Vehicle v1 = new Vehicle("Turtle", 5, true, false);
        Location vehileLocation1 = LocationConverter.HexLocationToPixel(new Location(2,1));
        vehileLocation1.incrementX(+45);
        vehileLocation1.incrementY(+35);
        v1.setLocation(vehileLocation1);

        ant.addVehicle(v1);


        Vehicle v2 = new Vehicle("Turtle2", 5, true, true);
        Location vehileLocation0 = LocationConverter.HexLocationToPixel(new Location(18,11)); // 18,12,0
        vehileLocation0.incrementX(+45);
        vehileLocation0.incrementY(+35);
        v2.setLocation(vehileLocation0);

        ant.addVehicle(v2);

        //villagers
        //v1
        List<String> list = new ArrayList<String>();
        list.add("My name is John Cena. I'm an internet sensation.");
        list.add("What does anyone do anywhere?");
        list.add("The Detroit Tigers?");
        list.add("So many things.");
        list.add("I suppose so.");

        TakeableItem ohSword = new Sword("Sword");
        TakeableItem pot1 = new Potion("HP Potion",50);
        TakeableItem pot2 = new Potion("HP Potion",25);
        TakeableItem pot3 = new Potion("HP Potion",25);
        TakeableItem bn = new BrassKnuckles("Brass Knuckles");
        itemManager.addItem(ohSword);
        itemManager.addItem(pot1);
        itemManager.addItem(pot2);
        itemManager.addItem(pot3);
        itemManager.addItem(bn);

        Location villagerLocation1 = LocationConverter.HexLocationToPixel(new Location(8,12));
        villagerLocation1.incrementX(StaticVar.terrainImageWidth/2);
        villagerLocation1.incrementY(StaticVar.terrainImageHeight/2);

        ant.addVillager(list, true, true, false, villagerLocation1);
        ant.getNpcList().get(0).getInventory().addItem(ohSword);
        ant.getNpcList().get(0).getInventory().addItem(pot1);
        ant.getNpcList().get(0).getInventory().addItem(pot2);
        ant.getNpcList().get(0).getInventory().addItem(pot3);
        ant.getNpcList().get(0).getInventory().addItem(bn);






        //v2
        List<String> list2 = new ArrayList<String>();
        list2.add("My name is John Cena. I'm an internet sensation.");
        list2.add("What does anyone do anywhere?");
        list2.add("The Detroit Tigers?");
        list2.add("So many things.");
        list2.add("I suppose so.");

        TakeableItem dagger = new Dagger("Kujik Dagger");
        TakeableItem bnr = new BowAndArrow("Wind Force", 50, 2, 2);
        TakeableItem tk = new ThrowingKnife("Ninja Stars", 25, 2, 2);
        itemManager.addItem(dagger);
        itemManager.addItem(bnr);
        itemManager.addItem(tk);

        Location villagerLocation2 = LocationConverter.HexLocationToPixel(new Location(17,11));
        villagerLocation2.incrementX(StaticVar.terrainImageWidth/2);

        ant.addVillager(list2, true, true, false, villagerLocation2);
        ant.getNpcList().get(0).getInventory().addItem(dagger);

        //monsters
        //m1
        Location monsterLocation1 = LocationConverter.HexLocationToPixel(new Location(2,4));
        monsterLocation1.incrementX(50);
        monsterLocation1.incrementY(50);
        ant.addMonster(monsterLocation1);

        //m2
        Location monsterLocation2 = LocationConverter.HexLocationToPixel(new Location(2,12));
        monsterLocation2.incrementX(50);
        monsterLocation2.incrementY(50);
        ant.addMonster(monsterLocation2);

        //m3
        Location monsterLocation3 = LocationConverter.HexLocationToPixel(new Location(10,17));
        monsterLocation3.incrementX(50);
        monsterLocation3.incrementY(50);
        ant.addMonster(monsterLocation3);

        //m4
        Location monsterLocation4 = LocationConverter.HexLocationToPixel(new Location(18,16));
        monsterLocation4.incrementX(50);
        monsterLocation4.incrementY(50);
        ant.addMonster(monsterLocation4);

        //m5
        Location monsterLocation5 = LocationConverter.HexLocationToPixel(new Location(14,4));
        monsterLocation5.incrementX(50);
        monsterLocation5.incrementY(50);
        ant.addMonster(monsterLocation5);

        //m6
        Location monsterLocation6 = LocationConverter.HexLocationToPixel(new Location(10,2));
        monsterLocation6.incrementX(50);
        monsterLocation6.incrementY(50);
        ant.addMonster(monsterLocation6);

        //m7
        Location monsterLocation7 = LocationConverter.HexLocationToPixel(new Location(8,7));
        monsterLocation7.incrementX(50);
        monsterLocation7.incrementY(50);
        ant.addMonster(monsterLocation7);

        //m8
        Location monsterLocation8 = LocationConverter.HexLocationToPixel(new Location(12,12));
        monsterLocation8.incrementX(50);
        monsterLocation8.incrementY(50);
        ant.addMonster(monsterLocation8);

        //m9
        Location monsterLocation9 = LocationConverter.HexLocationToPixel(new Location(16,15));
        monsterLocation9.incrementX(50);
        monsterLocation9.incrementY(50);
        ant.addMonster(monsterLocation9);



        /**
         * Now initializing items
         */
        //kay to the lock
        Item key1 = new Key("Key", 1);
        Location keyLocation = LocationConverter.HexLocationToPixel(new Location(17,18));
        keyLocation.incrementX(+35);
        keyLocation.incrementY(10);
        key1.setLocation(keyLocation);

        //lock
        Item interactive1 = new Interactive(1);
        Location l2 = LocationConverter.HexLocationToPixel(new Location(2,5));
        l2.incrementX(+35);
        l2.incrementY(60);
        interactive1.setLocation(l2);

        //obstacles
        Item obstacle1 = new Obstacle();
        Location obstacleLocation1 = LocationConverter.HexLocationToPixel(new Location(4,16));
        obstacleLocation1.incrementX(30);
        obstacleLocation1.incrementY(45);
        obstacle1.setLocation(obstacleLocation1);

        Item obstacle2 = new Obstacle();
        Location obstacleLocation2 = LocationConverter.HexLocationToPixel(new Location(3,17));
        obstacleLocation2.incrementX(30);
        obstacle2.setLocation(obstacleLocation2);

        //one shot
        Item oneShot1 = new OneShot();
        Location oneShotLocation1 = LocationConverter.HexLocationToPixel(new Location(2,18));
        oneShotLocation1.incrementX(39);
        oneShotLocation1.incrementY(60);
        oneShot1.setLocation(oneShotLocation1);

        itemManager.addItem(key1);
        itemManager.addItem(interactive1);
        itemManager.addItem(obstacle1);
        itemManager.addItem(obstacle2);
        itemManager.addItem(oneShot1);

        /**
         * area effects
         */
        //trap1
        AreaEffect trap1 = new Trap();
        //Location trapLocation1 = LocationConverter.HexLocationToPixel(new Location(14,13));
        Location trapLocation1 = LocationConverter.HexLocationToPixel(new Location(8,9));
        trapLocation1.incrementX(45);
        trapLocation1.incrementY(65);
        trap1.setLocation(trapLocation1);
        aem.addEffect(trap1);

        //heal damage
        AreaEffect healDamage = new HealDamage();
        Location healDamageLocation = LocationConverter.HexLocationToPixel(new Location(18,14));
        healDamageLocation.incrementX(40);
        healDamageLocation.incrementY(-30);
        healDamage.setLocation(healDamageLocation);
        aem.addEffect(healDamage);

        //instadeath
        AreaEffect instaDeath = new InstantDeath();
        Location instaDeathLocation = LocationConverter.HexLocationToPixel(new Location(4,11));
        instaDeathLocation.incrementX(40);
        instaDeathLocation.incrementY(60);
        instaDeath.setLocation(instaDeathLocation);
        aem.addEffect(instaDeath);

        //level up
        AreaEffect levelUp = new LevelUp();
        Location levelUpLocation = LocationConverter.HexLocationToPixel(new Location(18,12));
        levelUpLocation.incrementX(40);
        levelUpLocation.incrementY(60);
        levelUp.setLocation(levelUpLocation);
        aem.addEffect(levelUp);

        //level up
        AreaEffect levelUp2 = new LevelUp();
        Location levelUpLocation2 = LocationConverter.HexLocationToPixel(new Location(2,2));
        levelUpLocation2.incrementX(40);
        levelUpLocation2.incrementY(60);
        levelUp2.setLocation(levelUpLocation2);
        aem.addEffect(levelUp2);

        //take damage
        AreaEffect takeDamage1 = new TakeDamage();
        Location takeDamagelocation1 = LocationConverter.HexLocationToPixel(new Location(10,16));
        takeDamagelocation1.incrementX(40);
        takeDamagelocation1.incrementY(60);
        takeDamage1.setLocation(takeDamagelocation1);
        aem.addEffect(takeDamage1);

        //take damage
        AreaEffect takeDamage2 = new TakeDamage();
        Location takeDamagelocation2 = LocationConverter.HexLocationToPixel(new Location(10,15));
        takeDamagelocation2.incrementX(40);
        takeDamagelocation2.incrementY(60);
        takeDamage2.setLocation(takeDamagelocation2);
        aem.addEffect(takeDamage2);

        //teleport1
        Location targetTeleportLoc1 = LocationConverter.HexLocationToPixel(new Location(1,18));
        targetTeleportLoc1.incrementX(30);
        AreaEffect teleport1 = new Teleport(targetTeleportLoc1);
        Location sourceTeleportLoc1 = LocationConverter.HexLocationToPixel(new Location(10,1));
        sourceTeleportLoc1.incrementX(40);
        sourceTeleportLoc1.incrementY(66);
        teleport1.setLocation(sourceTeleportLoc1);
        aem.addEffect(teleport1);

        //teleport2
        Location targetTeleportLoc2 = LocationConverter.HexLocationToPixel(new Location(8,12));
        targetTeleportLoc2.incrementX(30);
        targetTeleportLoc2.incrementY(40);
        AreaEffect teleport2 = new Teleport(targetTeleportLoc2);
        Location sourceTeleportLoc2 = LocationConverter.HexLocationToPixel(new Location(3,18));
        sourceTeleportLoc2.incrementX(40);
        sourceTeleportLoc2.incrementY(120);
        teleport2.setLocation(sourceTeleportLoc2);
        aem.addEffect(teleport2);


        for (NPC n : ant.getNpcList()){
            npcMapInteract.add(new NPCMapInteract(n, map));
        }

//        Location monsterLocation10 = LocationConverter.HexLocationToPixel(new Location(15,13,0));
//        monsterLocation10.incrementX(50);
//        monsterLocation10.incrementY(50);
//        ant.addMonster(monsterLocation10);

       //pull in all pictures for GameState
        //Technically only one of these will need to be initialized
        WizardSprite.init();
        SmasherSprite.init();
        SneakSprite.init();
        VehicleSprite.init();
        VehicleSprite2.init();
        VillagerSprite.init();
        MonsterSprite.init();
        ItemSprite.init();
        PetSprite.init();
        AreaEffectSprite.init();
        SkillsSprite.init();
        AttackSprite.init();

        avatarView = new AvatarView(avatar, map);
        petView = new PetView(pet, avatar, map);
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


        mapView = new MapView(map, avatar);
        areaView =  new AreaView(mapView, avatarView, petView, vehicleViews, footerView, statusView, npcViews, controlView, itemViews, areaEffectViews);

        for(AreaEffect aEffect : aem.getAreaEffects()){
            areaEffectViews.add(new AreaEffectView(aEffect, avatar, map));
        }

        mapView = new MapView(map, avatar);
        areaView =  new AreaView(mapView, avatarView, petView, vehicleViews, footerView, statusView, npcViews, controlView, itemViews, areaEffectViews);

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
        petManager.updatePetPos(xMov, yMov, elapsed);
        petManager.stealItem();
        //petManager.startFight(ant.getNpcList());
        for(NPCMapInteract npcInteract : npcMapInteract) {
            NPC n = npcInteract.getNpc();
            if (n instanceof Monster) {
                npcInteract.updateNPCPos(elapsed);
            }
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
            avatar.saveAvatar();
            itemManager.saveItems();
            stateManager.setState(StateManager.MAINMENU);
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

