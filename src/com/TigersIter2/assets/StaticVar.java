package com.TigersIter2.assets;

import java.awt.*;

//class for any variable that is used throughout all the classes(SL)
public class StaticVar {

    //maps and respective names
    public static String map1 = "res/maps/terrainMap.txt";

    public static final int gameHeight = 720;//720;
    public static final int gameWidth = 1280;//1280;

    public static final int fps = 30;


    public static final int terrainImageHeight = 96;    //Was 64 before evil Miles Came
    public static final int terrainImageWidth = 105;     //Was 64 before evil Miles Came


    public static final int entityImageHeight = 128;
    public static final int entityImageWidth = 128;

    public static final float entitySpeed = .07f;
    public static final float cameraSpeed = .7f;


    //wizard sprite should be updated to 128x128 pixel standard!
    public static final int wizardImageHeight = 100;
    public static final int wizardImageWidth = 100;
    public static final int smasherImageHeight = 100;
    public static final int smasherImageWidth = 100;
    public static final int sneakImageHeight = 100;
    public static final int sneakImageWidth = 100;
    public static final int vehicleImageHeight = 100;
    public static final int vehicleImageWidth = 100;
    public static final int villagerImageHeight = 100;
    public static final int villagerImageWidth = 100;
    public static final int monsterImageHeight = 100;
    public static final int monsterImageWidth = 100;


    //MapView Conventional Stuff (Miles)
    public static final int xTilesFromEdge = 9; //8
    public static final int yTilesFromEdge = 4; //3


    //mapLoading stuff
    public static final int grass = 1;
    public static final int water = 2;
    public static final int mountain = 3;


    public static enum mapMode {
        PLAYER_FOLLOW_MODE
    }


}
