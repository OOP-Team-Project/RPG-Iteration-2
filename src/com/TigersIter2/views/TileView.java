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
    float currentXLocation, currentYLocation;
    TerrainType terrainType;
    int hexGridID = 0; //Used to see how the hex tile should be drawn (they alternate or whatever) (Miles)
    boolean glow = false;   //Used as a test to visually see what tile the player is on.

    public TileView(TerrainType t){  //Constructor specifies stuuuuufff.... I'm terrible at commenting code, you guys (Miles)
        terrainType = t;
    }

    public TileView(float x, float y, TerrainType t, int id){

        setPreferredSize(new Dimension(StaticVar.terrainImageWidth, StaticVar.terrainImageHeight));

        setDoubleBuffered(true);

        currentXLocation = x;
        currentYLocation = y;
        terrainType = t;
        hexGridID = id;
    }


    @Override
    public void paintComponent(Graphics g){
        /* TODO: Draw:
            1. This hex Tile's background image (determined by the Terrain Type)
         */


        Graphics2D g2d = (Graphics2D)g.create();

        if(currentXLocation > 22 || currentYLocation > 10 || currentXLocation < -1 || currentYLocation < -1){
            //do nothing - we're out of range!
        }
        else {

            if(glow){
                g2d.setXORMode(new Color(0, 0, 0, 0));
            }


            if ((hexGridID % 2) == 0) { //If X is even, draw it one way.
                g2d.drawImage(terrainType.getTerrainImage(), Math.round(currentXLocation * StaticVar.terrainImageWidth * .75f) - (StaticVar.terrainImageWidth / 2), Math.round(currentYLocation * StaticVar.terrainImageHeight) - (StaticVar.terrainImageHeight / 2), null);
            } else { //If it's odd, draw it a different way!
                g2d.drawImage(terrainType.getTerrainImage(), Math.round(currentXLocation * StaticVar.terrainImageWidth * .75f) - (StaticVar.terrainImageWidth / 2), Math.round(currentYLocation * StaticVar.terrainImageHeight), null);
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

    public void shouldGlow(boolean b){
        glow = b;
    }

    public float getCurrentXLocation() {
        return currentXLocation;
    }

    public void setCurrentXLocation(float currentXLocation) {
        this.currentXLocation = currentXLocation;
    }

    public float getCurrentYLocation() {
        return currentYLocation;
    }

    public void setCurrentYLocation(float currentYLocation) {
        this.currentYLocation = currentYLocation;
    }

    public int getHexGridID() {
        return hexGridID;
    }
}

