package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

/**
 * Created by slichtenheld on 2/29/2016.
 */
public class IntroSprite {
    public static BufferedImage introImage, pressSpace0, pressSpace1, pressSpace2, pressSpace3;

    //needs to be called once
    public static void init() {
        SpriteSheet terrainSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/Intro.png"));
        SpriteSheet pressSpaceSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/pressSpace.png"));

        introImage = terrainSheet.cropSheet(0,0,1280,720);
        pressSpace0 = pressSpaceSheet.cropSheet(0,0,278,51);
        pressSpace1 = pressSpaceSheet.cropSheet(278,0,278,51);
        pressSpace2 = pressSpaceSheet.cropSheet(278*2,0,278,51);
        pressSpace3 = pressSpaceSheet.cropSheet(278*3,0,278,51);

    }
}
