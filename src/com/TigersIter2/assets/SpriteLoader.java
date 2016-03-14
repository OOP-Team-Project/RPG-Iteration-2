package com.TigersIter2.assets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


//static class used to load in sprites(SL)
public class SpriteLoader {
    public static BufferedImage loadImage(String path){
        try {
            return ImageIO.read(new File("../res" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
