package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.SpriteLoader;

import java.awt.image.BufferedImage;

/**
 * Created by Breanna on 3/11/16.
 */
public class PetSprite {
    public static BufferedImage petUp1,petUp2, petDown1, petDown2, petUpLeft1, petUpLeft2,
            petUpRight1, petUpRight2, petDownLeft1, petDownLeft2, petDownRight1, petDownRight2;

    //needs to be called once
    public static void init(){
        SpriteSheet petSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/crab.png"));
        petDown1 = petSheet.cropSheet(0,0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petDown2 = petSheet.cropSheet(StaticVar.petImageWidth*1, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petUp1 = petSheet.cropSheet(StaticVar.petImageWidth*2, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petUp2 = petSheet.cropSheet(StaticVar.petImageWidth*3, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petUpLeft1 = petSheet.cropSheet(StaticVar.petImageWidth*4, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petUpLeft2 = petSheet.cropSheet(StaticVar.petImageWidth*5, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petUpRight1 = petSheet.cropSheet(StaticVar.petImageWidth*6, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petUpRight2 = petSheet.cropSheet(StaticVar.petImageWidth*7, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petDownLeft1 = petSheet.cropSheet(StaticVar.petImageWidth*8, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petDownLeft2 = petSheet.cropSheet(StaticVar.petImageWidth*9, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petDownRight1 = petSheet.cropSheet(StaticVar.petImageWidth*10, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
        petDownRight2 = petSheet.cropSheet(StaticVar.petImageWidth*11, 0, StaticVar.petImageWidth, StaticVar.petImageHeight);
    }
}
