package com.TigersIter2.maps;

import com.TigersIter2.maps.terrains.Grass;
import com.TigersIter2.maps.terrains.TerrainType;
import com.TigersIter2.maps.terrains.Water;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class TerrainMap implements Map{

    ArrayList<ArrayList<TerrainType>> tiles = new ArrayList<ArrayList<TerrainType>>();  //Don't be intimidated! This is just a 2D Array pretty much



    public TerrainMap(){   //Default Constructor, uh... makes a bunch of grass tiles and stuff? I guess?
        for(int i = 0; i < 20; i++){
            tiles.add(new ArrayList<TerrainType>());
            for(int j = 0; j < 20; j++){
                if((j % 5 == 0))
                    tiles.get(i).add(new Water());
                else
                    tiles.get(i).add(new Grass());
            }
        }
    }

    //update for map essentially handles what Tiles will be drawn to the screen, in other words areaView
    public void update(){

    }



    public ArrayList<ArrayList<TerrainType>> getTerrainTypes() {
        return tiles;
    }

    public void setTerrainTypes(ArrayList<ArrayList<TerrainType>> tiles) {
        this.tiles = tiles;
    }
}
