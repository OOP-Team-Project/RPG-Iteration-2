package com.TigersIter2.assets.sprites;


import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

//static way to retrieve Wizard images(SL)
public class WizardSprite {
    public static BufferedImage wizardUp1,wizardUp2, wizardDown1, wizardDown2, wizardLeft1, wizardLeft2, wizardRight1, wizardRight2;

    //needs to be called once
    public static void init(){
        SpriteSheet wizardSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/wizard.png"));

        wizardUp1 = wizardSheet.cropSheet(0,0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardUp2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*1, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardDown1 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*2, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardDown2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*3, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardRight1 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*4, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardRight2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*5, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardLeft1 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*6, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);
        wizardLeft2 = wizardSheet.cropSheet(StaticVar.wizardImageWidth*7, 0, StaticVar.wizardImageWidth, StaticVar.wizardImageHeight);


    }
}
