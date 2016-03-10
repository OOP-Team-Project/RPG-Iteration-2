package com.TigersIter2.assets.sprites;


import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

//static way to retrieve villager images(SL)
public class VillagerSprite {
    public static BufferedImage villagerUp1,villagerUp2, villagerDown1, villagerDown2, villagerUpLeft1, villagerUpLeft2,
            villagerUpRight1, villagerUpRight2, villagerDownLeft1, villagerDownLeft2, villagerDownRight1, villagerDownRight2;

    //needs to be called once
    public static void init(){
        SpriteSheet villagerSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/villager.png"));

        villagerDown1 = villagerSheet.cropSheet(0,0, StaticVar.villagerImageWidth, StaticVar.villagerImageHeight);
        villagerDown2 = villagerSheet.cropSheet(StaticVar.villagerImageWidth*1, 0, StaticVar.villagerImageWidth, StaticVar.villagerImageHeight);
        villagerUp1 = villagerSheet.cropSheet(StaticVar.villagerImageWidth*2, 0, StaticVar.villagerImageWidth, StaticVar.villagerImageHeight);
        villagerUp2 = villagerSheet.cropSheet(StaticVar.villagerImageWidth*3, 0, StaticVar.villagerImageWidth, StaticVar.villagerImageHeight);
        villagerUpLeft1 = villagerSheet.cropSheet(StaticVar.villagerImageWidth*4, 0, StaticVar.villagerImageWidth, StaticVar.villagerImageHeight);
        villagerUpLeft2 = villagerSheet.cropSheet(StaticVar.villagerImageWidth*5, 0, StaticVar.villagerImageWidth, StaticVar.villagerImageHeight);
        villagerUpRight1 = villagerSheet.cropSheet(StaticVar.villagerImageWidth*6, 0, StaticVar.villagerImageWidth, StaticVar.villagerImageHeight);
        villagerUpRight2 = villagerSheet.cropSheet(StaticVar.villagerImageWidth*7, 0, StaticVar.villagerImageWidth, StaticVar.villagerImageHeight);
        villagerDownLeft1 = villagerUpLeft1;
        villagerDownLeft2 = villagerUpLeft2;
        villagerDownRight1 = villagerUpRight1;
        villagerDownRight2 = villagerUpRight2;
    }
}
