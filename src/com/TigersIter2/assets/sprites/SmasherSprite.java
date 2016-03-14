package com.TigersIter2.assets.sprites;


import com.TigersIter2.assets.SpriteLoader;
import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

//static way to retrieve smasher images(SL)
public class SmasherSprite {
    public static BufferedImage smasherUp1,smasherUp2, smasherDown1, smasherDown2, smasherUpLeft1, smasherUpLeft2,
            smasherUpRight1, smasherUpRight2, smasherDownLeft1, smasherDownLeft2, smasherDownRight1, smasherDownRight2;

    //needs to be called once
    public static void init(){
        SpriteSheet smasherSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/smasher.png"));

        smasherDown1 = smasherSheet.cropSheet(0,0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherDown2 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*1, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherUp1 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*2, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherUp2 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*3, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherUpLeft1 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*4, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherUpLeft2 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*5, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherUpRight1 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*6, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherUpRight2 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*7, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherDownLeft1 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*8, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherDownLeft2 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*9, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherDownRight1 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*10, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
        smasherDownRight2 = smasherSheet.cropSheet(StaticVar.smasherImageWidth*11, 0, StaticVar.smasherImageWidth, StaticVar.smasherImageHeight);
    }
}
