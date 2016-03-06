package com.TigersIter2.location;


public class LocationConverter {


    //static methods to covert from HexTileLocation to x,y Location

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
