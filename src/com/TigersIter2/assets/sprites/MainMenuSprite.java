package com.TigersIter2.assets.sprites;

import com.TigersIter2.assets.SpriteLoader;

import java.awt.image.BufferedImage;

/**
 * Created by slichtenheld on 3/6/2016.
 */
public class MainMenuSprite {

    public static BufferedImage mainMenuImage, mainMenuTextImage, newGameButton, newGameButtonPressed, loadGameButton, loadGameButtonPressed,
            Iteration, largeWizard, largeSneak, largeSmasher;

    //needs to be called once
    public static void init() {
        SpriteSheet mainMenuSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/mainMenu.png"));
        SpriteSheet mainMenuTextSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/mainMenuText.png"));
        SpriteSheet loadButtonSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/loadGameButton.png"));
        SpriteSheet newButtonSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/newGameButton.png"));
        SpriteSheet IterationSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/Iteration.png"));
        SpriteSheet LargeWizardSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/wizardEnlarged.png"));
        SpriteSheet LargeSneakSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/sneakEnlarged.png"));
        SpriteSheet LargeSmasherSheet = new SpriteSheet(SpriteLoader.loadImage("/textures/smasherEnlarged.png"));

        mainMenuImage = mainMenuSheet.cropSheet(0,0,912,108);
        mainMenuTextImage = mainMenuTextSheet.cropSheet(0,0,500,40);
        newGameButton = newButtonSheet.cropSheet(0,0,340,100);
        newGameButtonPressed = newButtonSheet.cropSheet(340,0,340,100);
        loadGameButton = loadButtonSheet.cropSheet(0,0,340,100);
        loadGameButtonPressed = loadButtonSheet.cropSheet(340,0,340,100);
        Iteration = IterationSheet.cropSheet(0,0,514,123);
        largeWizard = LargeWizardSheet.cropSheet(0,0,200,200);
        largeSneak = LargeSneakSheet.cropSheet(0,0,200,200);
        largeSmasher = LargeSmasherSheet.cropSheet(0,0,200,200);

    }
}
