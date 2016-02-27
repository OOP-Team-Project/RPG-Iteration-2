package com.TigersIter2.assets.sprites;

import com.sun.deploy.ui.ImageLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


//static class used to load in sprites(SL)
public class SpriteLoader {
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(ImageLoader.class.getResource(path)); //returns buffered image object of loaded image
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
