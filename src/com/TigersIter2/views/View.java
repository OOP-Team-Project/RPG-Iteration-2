package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.location.Location;

import javax.swing.*;

/**
 * Created by Miles on 3/7/16.
 */
public class View extends JComponent {

    public static enum mapMode {
        PLAYER_FOLLOW_MODE,
        SCROLL_MODE
    }

    public static Location cameraLocation = new Location(0, 0, 0);

    public static mapMode currentMapMode = mapMode.PLAYER_FOLLOW_MODE;

    public static void update(int xMovement, int yMovement, long elapsed){
        if(currentMapMode == mapMode.SCROLL_MODE){
            cameraLocation.incrementX(Math.round(xMovement * elapsed * StaticVar.cameraSpeed));   //Made it invariant of framerate
            cameraLocation.incrementY(Math.round(yMovement * elapsed * StaticVar.cameraSpeed));
        } else{

        }
    }

    public static Location getCameraLocation() {
        return cameraLocation;
    }

    public static void setCameraLocation(Location cameraLocation) {
        View.cameraLocation = cameraLocation;
    }

    public static mapMode getCurrentMapMode() {
        return currentMapMode;
    }

    public static void setCurrentMapMode(mapMode currentMapMode) {
        View.currentMapMode = currentMapMode;
    }
}


