package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.maps.terrains.TerrainType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Miles on 2/28/16.
 */
public class TileView extends JComponent {

    //Graphics2D g2d;
    int currentXLocation, currentYLocation;
    TerrainType terrainType;

    public TileView(TerrainType t){  //Constructor specifies stuuuuufff.... I'm terrible at commenting code, you guys (Miles)
        terrainType = t;
    }

    public TileView(int x, int y, TerrainType t){
        currentXLocation = x;
        currentYLocation = y;
        terrainType = t;
    }


    @Override
    public void paintComponent(Graphics g){
        /* TODO: Draw:
            1. This hex Tile's background image (determined by the Terrain Type)
         */

        Graphics2D g2d = (Graphics2D)g.create();

        //TODO: Make this a lot nicer if possible!!!

        if(currentXLocation > 22 || currentYLocation > 10){
            //do nothing - we're out of range!
        }
        else {
            if ((currentXLocation % 2) == 0) { //If X is even, draw it one way.
                g2d.drawImage(terrainType.getTerrainImage(), Math.round(currentXLocation * StaticVar.terrainImageWidth * .75f) - (StaticVar.terrainImageWidth / 2), (currentYLocation * StaticVar.terrainImageHeight) - (StaticVar.terrainImageHeight / 2), null);
            } else {
                g2d.drawImage(terrainType.getTerrainImage(), Math.round(currentXLocation * StaticVar.terrainImageWidth * .75f) - (StaticVar.terrainImageWidth / 2), (currentYLocation * StaticVar.terrainImageHeight), null);
            }
        }
        g2d.dispose();
    }

    //Lookout! Getters/Setters are below!


//    public Graphics2D getG2d() {
//        return g2d;
//    }

//    public void setG2d(Graphics2D g2d) {
//        this.g2d = g2d;
//    }

    public int getCurrentXLocation() {
        return currentXLocation;
    }

    public void setCurrentXLocation(int currentXLocation) {
        this.currentXLocation = currentXLocation;
    }

    public int getCurrentYLocation() {
        return currentYLocation;
    }

    public void setCurrentYLocation(int currentYLocation) {
        this.currentYLocation = currentYLocation;
    }
}

