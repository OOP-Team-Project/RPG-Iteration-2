package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.*;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Vehicle;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class VehicleView extends View implements ActionListener{

    Avatar aHandle;
    Vehicle vHandle;
    int animationDelay = 100;
    int currentAnimationFrame = 0;
    Timer animationTimer;
    Location pixelLocation;
    int mapXLength, mapYLength = 0;

    public VehicleView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public VehicleView(Vehicle v, Avatar a, TerrainMap map) {
        aHandle = a;
        vHandle = v;
        mapXLength = map.getMapWidth();
        mapYLength = map.getMapHeight();


        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        animationTimer = new Timer(animationDelay, this);
        animationTimer.start();

        pixelLocation = vHandle.getLocation();

    }

    public void actionPerformed(ActionEvent e) {
        currentAnimationFrame = (currentAnimationFrame + 1) % 2;
    }

    private void drawVehicle(Graphics2D g2d){
        if (vHandle.getCanPassWater()) {        // For the water turtle
            if (vHandle.getDirection() == 45) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite.vehicleUpRight1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite.vehicleUpRight2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 90) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite.vehicleUp1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite.vehicleUp2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 135) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite.vehicleUpLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite.vehicleUpLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 225) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite.vehicleDownLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite.vehicleDownLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 270) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite.vehicleDown1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite.vehicleDown2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 315) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite.vehicleDownRight1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite.vehicleDownRight2, pixelLocation.getX(), pixelLocation.getY(), null);
            }
        }
        else if(vHandle.getCanPassMountain()){      //  For the mountain turtle
            if (vHandle.getDirection() == 45) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite2.vehicleUpRight1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite2.vehicleUpRight2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 90) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite2.vehicleUp1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite2.vehicleUp2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 135) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite2.vehicleUpLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite2.vehicleUpLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 225) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite2.vehicleDownLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite2.vehicleDownLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 270) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite2.vehicleDown1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite2.vehicleDown2, pixelLocation.getX(), pixelLocation.getY(), null);
            } else if (vHandle.getDirection() == 315) {
                if (currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                    g2d.drawImage(VehicleSprite2.vehicleDownRight1, pixelLocation.getX(), pixelLocation.getY(), null);
                else
                    g2d.drawImage(VehicleSprite2.vehicleDownRight2, pixelLocation.getX(), pixelLocation.getY(), null);
            }
        }
    }

    public void paintComponent(Graphics g){

        if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
            //tileViews.get(i).get(j).setCurrentXLocation(i);
            setPixelLocation(new Location(Math.round(vHandle.getLocation().getX()*.75f - 80), pixelLocation.getY()));
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i);
            //aHandle.setPixelLocation(new Location(Math.round(aHandle.getLocation().getX()*.75f - 80), aHandle.getPixelLocation().getY(), 0));
        }
        else if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth > (mapXLength - StaticVar.xTilesFromEdge + 1)) {
            //tileViews.get(i).get(j).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
            setPixelLocation(new Location(Math.round((vHandle.getLocation().getX() - ((mapXLength - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), pixelLocation.getY()));
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
            //aHandle.setPixelLocation(new Location(Math.round((aHandle.getLocation().getX() - ((tileViews.size() - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), aHandle.getPixelLocation().getY(), 0));
        }
        else  {
            setPixelLocation(new Location(Math.round((vHandle.getLocation().getX() - View.cameraLocation.getX() + StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth)*.75f) - 80, pixelLocation.getY()));
        }
        //Y Stuff Below
        if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
            setPixelLocation(new Location(pixelLocation.getX(), Math.round(vHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight*1.2f))));
        }
        else if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight > (mapYLength - StaticVar.yTilesFromEdge)) {
            //tileViews.get(i).get(j).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
            setPixelLocation(new Location(pixelLocation.getX(), Math.round((vHandle.getLocation().getY() - ((mapYLength - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f))));
            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
            //aHandle.setPixelLocation(new Location(aHandle.getPixelLocation().getX(), Math.round((aHandle.getLocation().getY() - ((tileViews.get(0).size() - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
        }
        else {
            setPixelLocation(new Location(pixelLocation.getX(), vHandle.getLocation().getY() - View.cameraLocation.getY() + Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight) - StaticVar.terrainImageHeight - 20));
        }


        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Location l = LocationConverter.PixelLocationToHex(vHandle.getLocation());

        if (aHandle.canSeeHex(l)) {
            drawVehicle(g2d);
        }
        g2d.dispose();
    }

    public void setPixelLocation(Location pixelLocation) {
        this.pixelLocation = pixelLocation;
    }

}
