package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.Map;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.maps.terrains.TerrainType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by slichtenheld on 2/25/2016.
 */

//MapView has just terrains... maybe we should rename to TerrainMapView?
public class MapView extends JComponent {

    TerrainMap mHandle;
    Avatar aHandle;
    ArrayList<ArrayList<TileView>> tileViews = new ArrayList<ArrayList<TileView>>();

    /*public enum mapMode {
        PLAYER_FOLLOW_MODE
    }*/

    StaticVar.mapMode currentMapMode = StaticVar.mapMode.PLAYER_FOLLOW_MODE;

    //for now only setting preferred size.
    //always set preferred size on JComponents, JPanels, ect.
    public MapView() {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public MapView(TerrainMap map, Avatar a) {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        mHandle = map;
        aHandle = a;

        //TODO: Fix this it's so ugly gaaaaaaaaaa I'm out of controoooool(Miles)

        for(int i = 0; i < mHandle.getTerrainTypes().size(); i++){
            tileViews.add(new ArrayList<TileView>());
            for(int j = 0; j < mHandle.getTerrainTypes().get(0).size(); j++){
                tileViews.get(i).add(new TileView(i, j, mHandle.getTerrainTypes().get(i).get(j), i));
                //this.add(new TileView(i, j, mHandle.getTerrainTypes().get(i).get(j), i));
            }
        }
    }


    @Override
    public void paintComponent(Graphics g){
        for(int i = 0; i < tileViews.size(); i++) {
            for(int j = 0; j < tileViews.get(0).size(); j++) {
                switch (currentMapMode) {
                    case PLAYER_FOLLOW_MODE:

                        if((float) (aHandle.getLocation().getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                            tileViews.get(i).get(j).setCurrentXLocation(i);
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i);
                            aHandle.setPixelLocation(new Location(Math.round(aHandle.getLocation().getX()*.75f - 80), aHandle.getPixelLocation().getY(), 0));

                        }
                        else if((float) (aHandle.getLocation().getX())/StaticVar.terrainImageWidth > (tileViews.size() - StaticVar.xTilesFromEdge + 1)) {
                            tileViews.get(i).get(j).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                            aHandle.setPixelLocation(new Location(Math.round((aHandle.getLocation().getX() - ((tileViews.size() - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), aHandle.getPixelLocation().getY(), 0));
                        }
                        else {
                            tileViews.get(i).get(j).setCurrentXLocation(i - (float) (aHandle.getLocation().getX()) / StaticVar.terrainImageWidth + StaticVar.xTilesFromEdge);
                            aHandle.setPixelLocation(new Location(Math.round(StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth*.75f - 80), aHandle.getPixelLocation().getY(), 0));
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - (float) (aHandle.getLocation().getX()) / StaticVar.terrainImageWidth + StaticVar.xTilesFromEdge);
                        }


                        //Y Stuff Below
                        if((float) (aHandle.getLocation().getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                            tileViews.get(i).get(j).setCurrentYLocation(j);
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j);
                            aHandle.setPixelLocation(new Location(aHandle.getPixelLocation().getX(), Math.round(aHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                        }
                        else if((float) (aHandle.getLocation().getY())/StaticVar.terrainImageHeight > (tileViews.get(0).size() - StaticVar.yTilesFromEdge)) {
                            tileViews.get(i).get(j).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                            aHandle.setPixelLocation(new Location(aHandle.getPixelLocation().getX(), Math.round((aHandle.getLocation().getY() - ((tileViews.get(0).size() - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                        }
                        else {
                            tileViews.get(i).get(j).setCurrentYLocation(j - (float) (aHandle.getLocation().getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
                            aHandle.setPixelLocation(new Location(aHandle.getPixelLocation().getX(), Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - (float) (aHandle.getLocation().getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
                        }
                        break;
                    default:
                        break;
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
