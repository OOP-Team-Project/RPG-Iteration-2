package com.TigersIter2.location;

import com.TigersIter2.assets.StaticVar;

/**
 * Created by slichtenheld on 3/8/2016.
 */
public class PixLoc {
    private int x;
    private int y;

//    public PixLoc(int r, int q){
//        y = (int)(Math.sqrt(3)* (float)StaticVar.terrainImageS*((float)r+(float)q/2));
//        x = (int)(StaticVar.terrainImageS*3/2*q);
//    }
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
