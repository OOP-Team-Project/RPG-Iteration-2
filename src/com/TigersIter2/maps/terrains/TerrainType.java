package com.TigersIter2.maps.terrains;

import java.awt.image.BufferedImage;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class TerrainType {

    //START (Miles, 5:03 PM February 28)
    BufferedImage terrainImage; //My idea here is that every TerrainType has an image. This should be something in the Sprites thing.

    String passable = "Nobody"; //My idea here is that we have strings that define who can pass through. When an entity
                                    //tries to pass through a tile, they check if they can by comparing strings.
                                    //POSSIBLE ALTERNATIVES SINCE THIS IS KINDA LAME:
                                    //  - Have a list of entity types that can pass (i.e., "Boats" but not "Avatars"

    public BufferedImage getTerrainImage() {
        return terrainImage;
    }

    public void setTerrainImage(BufferedImage terrainImage) {
        this.terrainImage = terrainImage;
    }

    public String getPassable() {
        return passable;
    }

    public void setPassable(String passable) {
        this.passable = passable;
    }


    //END
}
