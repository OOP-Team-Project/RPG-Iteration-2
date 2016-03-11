package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

public class ItemSprite {

    public static BufferedImage key, potion, weapon, obstacle, armor, interactive, oneShot;

    //needs to be called once
    public static void init(){
        SpriteSheet keySheet = new SpriteSheet(SpriteLoader.loadImage("/textures/key.png"));
        SpriteSheet potionSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/potion.png"));
        SpriteSheet weaponSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/weapon.png"));
        SpriteSheet obstacleSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/obstacle.png"));
        SpriteSheet armorSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/armor.png"));
        SpriteSheet interactiveSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/interactive.png"));
        SpriteSheet oneShotSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/oneShot.png"));

        key = keySheet.cropSheet(0,0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        potion = potionSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        weapon = weaponSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        obstacle = obstacleSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        armor = armorSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        interactive = interactiveSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        oneShot = oneShotSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
    }

}
