package com.TigersIter2.assets.sprites;


import com.TigersIter2.assets.SpriteLoader;
import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

//static way to retrieve vehicle images(SL)
public class VehicleSprite {
    public static BufferedImage vehicleUp1,vehicleUp2, vehicleDown1, vehicleDown2, vehicleUpLeft1, vehicleUpLeft2,
            vehicleUpRight1, vehicleUpRight2, vehicleDownLeft1, vehicleDownLeft2, vehicleDownRight1, vehicleDownRight2;

    //needs to be called once
    public static void init(){
        SpriteSheet vehicleSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/turtle.png"));

        vehicleDown1 = vehicleSheet.cropSheet(0,0, StaticVar.vehicleImageWidth, StaticVar.vehicleImageHeight);
        vehicleDown2 = vehicleSheet.cropSheet(StaticVar.vehicleImageWidth*1, 0, StaticVar.vehicleImageWidth, StaticVar.vehicleImageHeight);
        vehicleUp1 = vehicleSheet.cropSheet(StaticVar.vehicleImageWidth*2, 0, StaticVar.vehicleImageWidth, StaticVar.vehicleImageHeight);
        vehicleUp2 = vehicleSheet.cropSheet(StaticVar.vehicleImageWidth*3, 0, StaticVar.vehicleImageWidth, StaticVar.vehicleImageHeight);
        vehicleUpLeft1 = vehicleSheet.cropSheet(StaticVar.vehicleImageWidth*4, 0, StaticVar.vehicleImageWidth, StaticVar.vehicleImageHeight);
        vehicleUpLeft2 = vehicleSheet.cropSheet(StaticVar.vehicleImageWidth*5, 0, StaticVar.vehicleImageWidth, StaticVar.vehicleImageHeight);
        vehicleUpRight1 = vehicleSheet.cropSheet(StaticVar.vehicleImageWidth*6, 0, StaticVar.vehicleImageWidth, StaticVar.vehicleImageHeight);
        vehicleUpRight2 = vehicleSheet.cropSheet(StaticVar.vehicleImageWidth*7, 0, StaticVar.vehicleImageWidth, StaticVar.vehicleImageHeight);
        vehicleDownLeft1 = vehicleUpLeft1;
        vehicleDownLeft2 = vehicleUpLeft2;
        vehicleDownRight1 = vehicleUpRight1;
        vehicleDownRight2 = vehicleUpRight2;
    }
}
