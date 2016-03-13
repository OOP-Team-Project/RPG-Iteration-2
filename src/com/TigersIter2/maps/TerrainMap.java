package com.TigersIter2.maps;

import com.TigersIter2.assets.FileReader;
import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.location.Location;
import com.TigersIter2.maps.terrains.Grass;
import com.TigersIter2.maps.terrains.Mountain;
import com.TigersIter2.maps.terrains.TerrainType;
import com.TigersIter2.maps.terrains.Water;

import java.util.ArrayList;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class TerrainMap implements Map {
    //constructor will get a lot smaller once we migrate to using array of ints for map (Sam)


    private int[][] map;
    private int mapWidth, mapHeight;

    ArrayList<ArrayList<TerrainType>> tiles = new ArrayList<ArrayList<TerrainType>>();  //Don't be intimidated! This is just a 2D Array pretty much
    private TerrainType[][] tilesArray;

    //takes in string(named in staticVar) and initializes map
    public TerrainMap(String mapName) {
        String[] map1String = FileReader.fileToString(mapName).split("\\s+"); //splits up file on any white space
        mapWidth = FileReader.stringToInt(map1String[0]); //first number of file
        mapHeight = FileReader.stringToInt(map1String[1]); //second number of file
        map = new int[mapWidth][mapHeight];

        //load in ints to appropriate 2D array
        for (int y = 0; y < mapHeight; y++) {
            for (int x = 0; x < mapWidth; x++) {
                map[x][y] = FileReader.stringToInt(map1String[x + y * mapWidth + 2]); //plus 2 since first 2 numbers contain height and width of map
            }
        }
        TerrainType terrainArray[] = {new Grass(), new Grass(), new Water(), new Mountain()};

        tilesArray = new TerrainType[mapWidth][mapHeight];
        for (int i = 0; i < mapWidth; i++) {
            tiles.add(new ArrayList<TerrainType>());
            for (int j = 0; j < mapHeight; j++) {
                tilesArray[i][j] = terrainArray[map[i][j]];
            }
        }

        for (int i = 0; i < mapWidth; i++) {
            tiles.add(new ArrayList<TerrainType>());
            for (int j = 0; j < mapHeight; j++) tiles.get(i).add(tilesArray[i][j]);
        }
    }

    //update for map essentially handles what Tiles will be drawn to the screen, in other words areaView
    public void update() {

    }

    public ArrayList<ArrayList<TerrainType>> getTerrainTypes() {
        return tiles;
    }

    public int[][] getMap() {
        return map;
    }

    public boolean checkPassable(Location location){
        if (map[location.getX()][location.getY()] == StaticVar.grass) return true;
        return false;
    }

    public int getTerrainType(Location location){
        return map[location.getX()][location.getY()];
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getMapHeight() {
        return mapHeight;
    }
}
