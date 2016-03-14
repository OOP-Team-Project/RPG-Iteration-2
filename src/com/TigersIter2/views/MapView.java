package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.maps.terrains.Grass;
import com.TigersIter2.maps.terrains.Mountain;
import com.TigersIter2.maps.terrains.TerrainType;
import com.TigersIter2.maps.terrains.Water;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by slichtenheld on 2/25/2016.
 */

//MapView has just terrains... maybe we should rename to TerrainMapView?
public class MapView extends View {

    TerrainMap mHandle;
    Avatar aHandle;
    int[][] map; //SAM
    ArrayList<ArrayList<TileView>> tileViews = new ArrayList<ArrayList<TileView>>();


    //for now only setting preferred size.
    //always set preferred size on JComponents, JPanels, ect.
    public MapView() {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public MapView(TerrainMap TerrainMap, Avatar a) {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        mHandle = TerrainMap;
        aHandle = a;
        map = mHandle.getMap();

        //TerrainType terrainArray[] = {new Grass(), new Grass(), new Water(), new Mountain()};

        //TODO: Fix this it's so ugly gaaaaaaaaaa I'm out of controoooool(Miles)

        for(int i = 0; i < mHandle.getMapWidth(); i++){
            tileViews.add(new ArrayList<TileView>());
            for(int j = 0; j < mHandle.getMapHeight(); j++){
                tileViews.get(i).add(new TileView(i, j, mHandle.getTerrainTypes().get(i).get(j), i));
                //this.add(new TileView(i, j, mHandle.getTerrainTypes().get(i).get(j), i));
            }
        }

        View.setMapXLength(tileViews.size());
        View.setMapYLength(tileViews.get(0).size());
    }


    @Override
    public void paintComponent(Graphics g){
        for(int i = 0; i < tileViews.size(); i++) {
            for(int j = 0; j < tileViews.get(0).size(); j++) {

                if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                    tileViews.get(i).get(j).setCurrentXLocation(i);
                }
                else if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth > (tileViews.size() - StaticVar.xTilesFromEdge + 1)) {
                    tileViews.get(i).get(j).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                }
                else {
                    tileViews.get(i).get(j).setCurrentXLocation(i - (float) (View.cameraLocation.getX()) / StaticVar.terrainImageWidth + StaticVar.xTilesFromEdge);
                }

                //Y Stuff Below
                if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                    tileViews.get(i).get(j).setCurrentYLocation(j);
                }
                else if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight > (tileViews.get(0).size() - StaticVar.yTilesFromEdge)) {
                    tileViews.get(i).get(j).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                }
                else {
                    tileViews.get(i).get(j).setCurrentYLocation(j - (float) (View.cameraLocation.getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
                }


                //Miles Tests below:
                Location aHexLocation = LocationConverter.PixelLocationToHex(aHandle.getLocation());

                if(aHexLocation.getX() == i && aHexLocation.getY() == j)
                    tileViews.get(i).get(j).shouldGlow(true);
                else
                    tileViews.get(i).get(j).shouldGlow(false);

                tileViews.get(i).get(j).paintComponent(g);
            }
        }
    }



}
