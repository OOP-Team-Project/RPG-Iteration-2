package com.TigersIter2.maps.terrains;

import com.TigersIter2.assets.sprites.TerrainSprite;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class Water extends TerrainType{

    public Water(){
        passable = "Water";
        terrainImage = TerrainSprite.water;
    }
}
