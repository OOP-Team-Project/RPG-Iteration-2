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


    static HexLocation cube2hex(CubeLocation cubeLocation){
        int q = cubeLocation.getX();
        int r = cubeLocation.getZ();
        return new HexLocation(q,r);
    }

    static CubeLocation hex2cube(HexLocation hexLocation){
        int x = hexLocation.getQ();
        int z = hexLocation.getR();
        int y = -x-z;
        return new CubeLocation(x,y,z);
    }
}
