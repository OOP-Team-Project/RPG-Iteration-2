package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

public class ItemSprite {

    public static BufferedImage key, potion, weapon, obstacle;

    //needs to be called once
    public static void init(){
        SpriteSheet keySheet = new SpriteSheet(SpriteLoader.loadImage("/textures/key.png"));
        SpriteSheet potionSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/potion.png"));
        SpriteSheet weaponSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/weapon.png"));
        SpriteSheet obstacleSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/obstacle.png"));

        key = keySheet.cropSheet(0,0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        potion = potionSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        weapon = weaponSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        obstacle = obstacleSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
    }

}
