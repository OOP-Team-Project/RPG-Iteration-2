package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.StaticVar;

import java.awt.image.BufferedImage;

/**
 * Created by slichtenheld on 2/29/2016.
 */
public class IntroSprite {
    public static BufferedImage introImage;

    //needs to be called once
    public static void init() {
        SpriteSheet terrainSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/Intro.png"));

        introImage = terrainSheet.cropSheet(0,0,1280,720);
    }
}
