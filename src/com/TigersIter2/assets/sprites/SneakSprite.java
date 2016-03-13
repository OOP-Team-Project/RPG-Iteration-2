package com.TigersIter2.assets.sprites;


import com.TigersIter2.assets.SpriteLoader;
import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

//static way to retrieve sneak images(SL)
public class SneakSprite {
    public static BufferedImage sneakUp1,sneakUp2, sneakDown1, sneakDown2, sneakUpLeft1, sneakUpLeft2,
            sneakUpRight1, sneakUpRight2, sneakDownLeft1, sneakDownLeft2, sneakDownRight1, sneakDownRight2;

    //needs to be called once
    public static void init(){
        SpriteSheet sneakSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/sneak.png"));

        sneakDown1 = sneakSheet.cropSheet(0,0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakDown2 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*1, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakUp1 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*2, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakUp2 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*3, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakUpLeft1 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*4, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakUpLeft2 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*5, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakUpRight1 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*6, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakUpRight2 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*7, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakDownLeft1 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*8, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakDownLeft2 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*9, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakDownRight1 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*10, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
        sneakDownRight2 = sneakSheet.cropSheet(StaticVar.sneakImageWidth*11, 0, StaticVar.sneakImageWidth, StaticVar.sneakImageHeight);
    }
}
