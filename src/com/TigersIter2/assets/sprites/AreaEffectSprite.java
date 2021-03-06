package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.SpriteLoader;
import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

public class AreaEffectSprite {

    public static BufferedImage healDamage, instantDeath, levelUp, takeDamage, teleport, trap;

    //needs to be called once
    public static void init(){
        SpriteSheet healDamageSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/cross.png"));
        SpriteSheet instantDeathSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/skull.png"));
        SpriteSheet levelUpSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/star.png"));
        SpriteSheet takeDamageSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/takeDamage.png"));
        SpriteSheet teleportSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/teleport.png"));
        SpriteSheet trapSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/trap.png"));

        healDamage = healDamageSheet.cropSheet(0,0, StaticVar.areaEffectImageWidth, StaticVar.areaEffectImageHeight);
        instantDeath = instantDeathSheet.cropSheet(0, 0, StaticVar.areaEffectImageWidth, StaticVar.areaEffectImageHeight);
        levelUp = levelUpSheet.cropSheet(0, 0, StaticVar.areaEffectImageWidth, StaticVar.areaEffectImageHeight);
        takeDamage = takeDamageSheet.cropSheet(0, 0, StaticVar.areaEffectImageWidth, StaticVar.areaEffectImageHeight);
        teleport = teleportSheet.cropSheet(0, 0, StaticVar.areaEffectImageWidth, StaticVar.areaEffectImageHeight);
        trap = trapSheet.cropSheet(0, 0, StaticVar.areaEffectImageWidth, StaticVar.areaEffectImageHeight);
    }

}
