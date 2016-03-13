package com.TigersIter2.assets.sprites;

import com.TigersIter2.entities.Sneak;

import java.awt.image.BufferedImage;

/**
 * Created by slichtenheld on 3/9/2016.
 */
public class NewGameMenuSprites {

    public static BufferedImage ChooseAnAvatar, SmasherString, SmasherStringPressed, SneakString, SneakStringPressed, SummonerString, SummonerStringPressed;

    //needs to be called once
    public static void init() {
        SpriteSheet NewMenuSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/ChooseAnAvatar.png"));
        SpriteSheet SmasherStringSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/SmasherString.png"));
        SpriteSheet SummonerStringSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/SummonerString.png"));
        SpriteSheet SneakSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/SneakString.png"));


        ChooseAnAvatar = NewMenuSheet.cropSheet(0,0,476,77);
        SmasherString = SmasherStringSheet.cropSheet(0,0,258,77);
        SmasherStringPressed = SmasherStringSheet.cropSheet(258,0,258,77);
        SneakString = SneakSheet.cropSheet(0,0,185,77);
        SneakStringPressed = SneakSheet.cropSheet(185,0,185,77);
        SummonerString = SummonerStringSheet.cropSheet(0,0,309,78);
        SummonerStringPressed = SummonerStringSheet.cropSheet(309,0,309,78);

    }
}
