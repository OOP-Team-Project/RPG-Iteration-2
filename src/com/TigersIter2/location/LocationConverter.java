package com.TigersIter2.location;


import com.TigersIter2.assets.StaticVar;

public class LocationConverter {

    //static methods to covert from HexTileLocation to x,y Location
    public static Location PixelLocationToHex(Location pix) {
        if (pix.getX() / StaticVar.terrainImageWidth % 2 == 0)
            return (new Location(pix.getX() / StaticVar.terrainImageWidth, pix.getY() / StaticVar.terrainImageHeight, 0));
        else
            return (new Location(pix.getX() / StaticVar.terrainImageWidth, (pix.getY() + StaticVar.terrainImageHeight / 2) / StaticVar.terrainImageHeight - 1, 0));

    }

    public static Location HexLocationToPixel(Location hex){
        return (new Location(hex.getX()*StaticVar.terrainImageWidth, hex.getY()*StaticVar.terrainImageHeight, 0));
    }
}
