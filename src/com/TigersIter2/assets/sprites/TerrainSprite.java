package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

//static way to retrieve Terrain images(SL)
//for ex. TerrainSprite.mountain returns mountain pic
public class TerrainSprite {

    public static BufferedImage mountain, water, grass;

    //needs to be called once
    public static void init(){
//        SpriteSheet terrainSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/hexTerrains (Miles).png"));

//        mountain = terrainSheet.cropSheet(0,0, StaticVar.terrainImageWidth, StaticVar.terrainImageHeight);
//        grass = terrainSheet.cropSheet(StaticVar.terrainImageWidth*1, 0, StaticVar.terrainImageWidth, StaticVar.terrainImageHeight);
//        water = terrainSheet.cropSheet(StaticVar.terrainImageWidth*2, 0, StaticVar.terrainImageWidth, StaticVar.terrainImageHeight);

        SpriteSheet terrainSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/hexTerrainsV2.png"));


        mountain = terrainSheet.cropSheet(0,0, StaticVar.terrainImageWidth, StaticVar.terrainImageHeight);
        water = terrainSheet.cropSheet(StaticVar.terrainImageWidth*1, 0, StaticVar.terrainImageWidth, StaticVar.terrainImageHeight);
        grass = terrainSheet.cropSheet(StaticVar.terrainImageWidth*2, 0, StaticVar.terrainImageWidth, StaticVar.terrainImageHeight);

    }

}
