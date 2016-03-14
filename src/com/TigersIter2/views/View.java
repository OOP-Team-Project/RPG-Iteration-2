package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
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

    public static Location cameraLocation = new Location(0, 0);
    public static EntityFollowingCamera eCam;
    public static Camera scrollCam = new Camera();
    public static int mapXLength, mapYLength;

    public static mapMode currentMapMode = mapMode.PLAYER_FOLLOW_MODE;

    public static void update(int xMovement, int yMovement, long elapsed){
        if(eCam == null)
            return;

        if(currentMapMode == mapMode.SCROLL_MODE){

            int newX = scrollCam.getObjectOfInterest_X() + Math.round(xMovement * elapsed * StaticVar.cameraSpeed);
            int newY = scrollCam.getObjectOfInterest_Y() + Math.round(yMovement * elapsed * StaticVar.cameraSpeed);

            if(newX < StaticVar.terrainImageWidth*StaticVar.xTilesFromEdge)
                newX = StaticVar.terrainImageWidth*StaticVar.xTilesFromEdge;
            if(newX > StaticVar.terrainImageWidth*(mapXLength - StaticVar.xTilesFromEdge + 1))
                newX = StaticVar.terrainImageWidth*(mapXLength - StaticVar.xTilesFromEdge + 1);

            if(newY < StaticVar.terrainImageHeight*StaticVar.yTilesFromEdge)
                newY = StaticVar.terrainImageHeight*StaticVar.yTilesFromEdge;
            if(newY > StaticVar.terrainImageHeight*(mapYLength - StaticVar.yTilesFromEdge))
                newY = StaticVar.terrainImageHeight*(mapYLength -StaticVar.yTilesFromEdge);


            scrollCam.setObjectOfInterest_X(newX);
            scrollCam.setObjectOfInterest_Y(newY);

            cameraLocation.setX(scrollCam.getObjectOfInterest_X());
            cameraLocation.setY(scrollCam.getObjectOfInterest_Y());

        } else{
            scrollCam.setObjectOfInterest_X(eCam.getObjectOfInterest_X());
            scrollCam.setObjectOfInterest_Y(eCam.getObjectOfInterest_Y());

            cameraLocation.setX(eCam.getObjectOfInterest_X());
            cameraLocation.setY(eCam.getObjectOfInterest_Y());

        }
    }

    public static Location getCameraLocation() {
        return cameraLocation;
    }

    public static void setCameraLocation(Location cameraLocation) {
        View.cameraLocation = cameraLocation;
    }

    public static void setAvatar(Avatar a){
        eCam = new EntityFollowingCamera(a);
    }

    public static mapMode getCurrentMapMode() {
        return currentMapMode;
    }

    public static void setCurrentMapMode(mapMode currentMapMode) {
        View.currentMapMode = currentMapMode;
    }

    public static int getMapYLength() {
        return mapYLength;
    }

    public static void setMapYLength(int mapYLength) {
        View.mapYLength = mapYLength;
    }

    public static int getMapXLength() {
        return mapXLength;
    }

    public static void setMapXLength(int mapXLength) {
        View.mapXLength = mapXLength;
    }
}


