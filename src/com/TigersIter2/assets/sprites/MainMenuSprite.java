package com.TigersIter2.assets.sprites;

import java.awt.image.BufferedImage;

/**
 * Created by slichtenheld on 3/6/2016.
 */
public class MainMenuSprite {

    public static BufferedImage mainMenuImage, mainMenuTextImage, newGameButton, newGameButtonPressed, loadGameButton, loadGameButtonPressed;

    //needs to be called once
    public static void init() {
        SpriteSheet mainMenuSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/mainMenu.png"));
        SpriteSheet mainMenuTextSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/mainMenuText.png"));
        SpriteSheet loadButtonSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/loadGameButton.png"));
        SpriteSheet newButtonSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/newGameButton.png"));

        mainMenuImage = mainMenuSheet.cropSheet(0,0,912,108);
        mainMenuTextImage = mainMenuTextSheet.cropSheet(0,0,500,40);
        newGameButton = newButtonSheet.cropSheet(0,0,340,100);
        newGameButtonPressed = newButtonSheet.cropSheet(340,0,340,100);
        loadGameButton = loadButtonSheet.cropSheet(0,0,340,100);
        loadGameButtonPressed = loadButtonSheet.cropSheet(340,0,340,100);
    }
}
