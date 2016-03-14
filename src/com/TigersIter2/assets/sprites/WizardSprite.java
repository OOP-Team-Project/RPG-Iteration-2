package com.TigersIter2.assets.sprites;


import com.TigersIter2.assets.SpriteLoader;
import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

//static way to retrieve Wizard images(SL)
public class WizardSprite {
    public static BufferedImage wizardUp1,wizardUp2, wizardDown1, wizardDown2, wizardUpLeft1, wizardUpLeft2,
            wizardUpRight1, wizardUpRight2, wizardDownLeft1, wizardDownLeft2, wizardDownRight1, wizardDownRight2;

    //needs to be called once
    public static void init(){
        SpriteSheet wizardSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/wizard.png"));

        wizardDown1 = wizardSheet.cropSheet(0,0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardDown2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*1, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardUp1 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*2, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardUp2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*3, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardUpRight1 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*4, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardUpRight2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*5, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardUpLeft1 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*6, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardUpLeft2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*7, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardDownLeft1 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*8, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardDownLeft2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*9, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardDownRight1 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*10, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardDownRight2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*11, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
    }
}
