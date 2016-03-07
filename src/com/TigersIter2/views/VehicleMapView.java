package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Vehicle;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Miles on 3/6/16.
 */
public class VehicleMapView extends JComponent {

    Avatar aHandle;
    Vehicle vHandle;

    StaticVar.mapMode currentMapMode = StaticVar.mapMode.PLAYER_FOLLOW_MODE;

    //for now only setting preferred size.
    //always set preferred size on JComponents, JPanels, ect.
    public VehicleMapView() {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public VehicleMapView(TerrainMap map, Avatar a, Vehicle v) {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        aHandle = a;
        vHandle = v;
    }


    @Override
    public void paintComponent(Graphics g){
                switch (currentMapMode) {
                    case PLAYER_FOLLOW_MODE:

                        if((float) (aHandle.getLocation().getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                            //tileViews.get(i).get(j).setCurrentXLocation(i);
                            vHandle.setPixelLocation(new Location(vHandle.getLocation().getX(), vHandle.getPixelLocation().getY(), 0));
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i);
                            //aHandle.setPixelLocation(new Location(Math.round(aHandle.getLocation().getX()*.75f - 80), aHandle.getPixelLocation().getY(), 0));

                        }
                        else  {
                            vHandle.setPixelLocation(new Location(vHandle.getLocation().getX() - aHandle.getLocation().getX(), vHandle.getPixelLocation().getY(), 0));

                        }


                        //Y Stuff Below
                        if((float) (aHandle.getLocation().getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                            vHandle.setPixelLocation(new Location(vHandle.getPixelLocation().getX(), vHandle.getLocation().getY(), 0));
                        }
                        else {
                            vHandle.setPixelLocation(new Location(vHandle.getPixelLocation().getX(), vHandle.getLocation().getY() - aHandle.getLocation().getY(), 0));
                        }
                        break;
                    default:
                        break;
                }
    }



}
