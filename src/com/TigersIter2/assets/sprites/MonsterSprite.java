package com.TigersIter2.assets.sprites;


import com.TigersIter2.assets.SpriteLoader;
import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

//static way to retrieve monster images(SL)
public class MonsterSprite {
    public static BufferedImage monsterUp1,monsterUp2, monsterDown1, monsterDown2, monsterUpLeft1, monsterUpLeft2,
            monsterUpRight1, monsterUpRight2, monsterDownLeft1, monsterDownLeft2, monsterDownRight1, monsterDownRight2;

    //needs to be called once
    public static void init(){
        SpriteSheet monsterSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/monster.png"));

        monsterDown1 = monsterSheet.cropSheet(0,0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);
        monsterDown2 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*1, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);
        monsterUp1 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*2, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);
        monsterUp2 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*3, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);
        monsterUpLeft1 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*4, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);
        monsterUpLeft2 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*5, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);
        monsterUpRight1 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*6, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);
        monsterUpRight2 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*7, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);
        monsterDownLeft1 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*8, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);;
        monsterDownLeft2 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*9, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);;
        monsterDownRight1 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*10, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);;
        monsterDownRight2 = monsterSheet.cropSheet(StaticVar.monsterImageWidth*11, 0, StaticVar.monsterImageWidth, StaticVar.monsterImageHeight);;
    }
}
