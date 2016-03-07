package com.TigersIter2.maps;

import com.TigersIter2.assets.FileReader;
import com.TigersIter2.maps.terrains.Grass;
import com.TigersIter2.maps.terrains.Mountain;
import com.TigersIter2.maps.terrains.TerrainType;
import com.TigersIter2.maps.terrains.Water;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class TerrainMap implements Map{

    public static int heightOfWorld, widthOfWorld;
    private int[][] terrainMap;

    //Do we need a list for tiles? Wouldn't a 2D array work just fine since the size will be fixed upon initialization? - Sam
    ArrayList<ArrayList<TerrainType>> tiles = new ArrayList<ArrayList<TerrainType>>();  //Don't be intimidated! This is just a 2D Array pretty much



    public TerrainMap(){   //Default Constructor, uh... makes a bunch of grass tiles and stuff? I guess?
        for(int i = 0; i < 40; i++){
            tiles.add(new ArrayList<TerrainType>());
            for(int j = 0; j < 40; j++){
                if((i == 0 || i == 1))
                    tiles.get(i).add(new Mountain());
                else if(i == 38 || i == 39)
                    tiles.get(i).add(new Mountain());
                else if (j == 0 || j == 1)
                    tiles.get(i).add(new Mountain());
                else if (j == 38 || j == 39)
                    tiles.get(i).add(new Mountain());
                else if((j % 5 == 0))
                    tiles.get(i).add(new Water());
                else
                    tiles.get(i).add(new Grass());
            }
        }
        
        // // TODO: 3/6/2016 Sam -> move this into helper/association class or somehow clean up 
        //Will always need to load in from map file -> map file is unchanging
         //Loading map code is here
            String file = FileReader.fileToString("res/maps/terrainMap.txt");
            String[] parsedString = file.split("\\s+"); //splits up file on any white space
            widthOfWorld = FileReader.stringToInt(parsedString[0]); //first number of file
            heightOfWorld = FileReader.stringToInt(parsedString[1]); //second number of file
            terrainMap = new int[widthOfWorld][heightOfWorld];
            for (int y = 0; y < heightOfWorld; y++){
                for (int x = 0; x < widthOfWorld; x++){
                    terrainMap[x][y]= FileReader.stringToInt(parsedString[x+y*widthOfWorld+2]); //plus 2 since first 2 numbers contain height and width of map
                }
            }
         //
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
