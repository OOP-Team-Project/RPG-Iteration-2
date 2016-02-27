package com.TigersIter2.assets.sprites;

import java.awt.image.BufferedImage;

//pulls in the whole sprite.png image, has a crop method to crop them into individual images
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet = sheet;
    }

    // x/y is starting point of crop, widht/height is how tall and wide to crop
    public BufferedImage cropSheet(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height);
    }
}
