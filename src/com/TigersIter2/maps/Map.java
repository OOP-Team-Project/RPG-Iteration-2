package com.TigersIter2.maps;

import com.TigersIter2.maps.terrains.Grass;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class Map {

    ArrayList<ArrayList<Tile>> tiles = new ArrayList<ArrayList<Tile>>();  //Don't be intimidated! This is just a 2D Array pretty much

    public Map(){   //Default Constructor, uh... makes a bunch of grass tiles and stuff? I guess?



        for(int i = 0; i < 20; i++){

            tiles.add(new ArrayList<Tile>());

            for(int j = 0; j < 20; j++){
                tiles.get(i).add(new Tile(i, j, new Grass()));
            }
        }
    }

    //update for map essentially handles what Tiles will be drawn to the screen, in other words areaView
    public void update(){

    }

    public void drawAllTiles(Graphics2D g2d){
        for(int i = 0; i < tiles.size(); i++){
            for(int j = 0; j < tiles.get(0).size(); j++){
                tiles.get(i).get(j).setG2d(g2d);
                tiles.get(i).get(j).paintComponent(g2d);
            }
        }
    }

    public ArrayList<ArrayList<Tile>> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<ArrayList<Tile>> tiles) {
        this.tiles = tiles;
    }
}
