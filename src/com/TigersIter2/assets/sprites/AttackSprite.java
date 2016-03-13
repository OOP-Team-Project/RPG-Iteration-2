package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

public class AttackSprite {

    public static BufferedImage bane, enchantment;

    //needs to be called once
    public static void init(){
        SpriteSheet enchantmentSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/enchantment.png"));
        SpriteSheet baneSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/bane.png"));

        bane = baneSheet.cropSheet(0,0, StaticVar.attackImageWidth, StaticVar.attackImageHeight);
        enchantment = enchantmentSheet.cropSheet(0, 0, StaticVar.attackImageWidth, StaticVar.attackImageHeight);
    }

}
