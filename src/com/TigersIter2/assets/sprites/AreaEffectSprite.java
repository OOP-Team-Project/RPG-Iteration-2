package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

public class AreaEffectSprite {

    public static BufferedImage healDamage, instantDeath, levelUp, takeDamage, teleport;

    //needs to be called once
    public static void init(){
        SpriteSheet healDamageSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/cross.png"));
        SpriteSheet instantDeathSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/skull.png"));
        SpriteSheet levelUpSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/star.png"));
        SpriteSheet takeDamageSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/takeDamage.png"));
        SpriteSheet teleportSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/teleport.png"));

        healDamage = healDamageSheet.cropSheet(0,0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        instantDeath = instantDeathSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        levelUp = levelUpSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        takeDamage = takeDamageSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
        teleport = teleportSheet.cropSheet(0, 0, StaticVar.itemImageWidth, StaticVar.itemImageHeight);
    }

}
