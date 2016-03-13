package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Pet;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;

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
    Pet pHandle;
    ArrayList<ArrayList<TileView>> tileViews = new ArrayList<ArrayList<TileView>>();


    //for now only setting preferred size.
    //always set preferred size on JComponents, JPanels, ect.
    public MapView() {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public MapView(TerrainMap map, Avatar a, Pet p) {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        mHandle = map;
        aHandle = a;
        pHandle = p;

        //TODO: Fix this it's so ugly gaaaaaaaaaa I'm out of controoooool(Miles)

        for(int i = 0; i < mHandle.getMapWidth(); i++){
            tileViews.add(new ArrayList<TileView>());
            for(int j = 0; j < mHandle.getMapHeight(); j++){
                tileViews.get(i).add(new TileView(i, j, mHandle.getTerrainTypes().get(i).get(j), i));
                //this.add(new TileView(i, j, mHandle.getTerrainTypes().get(i).get(j), i));
            }
        }
    }


    @Override
    public void paintComponent(Graphics g){
        for(int i = 0; i < tileViews.size(); i++) {
            for(int j = 0; j < tileViews.get(0).size(); j++) {
                switch (View.currentMapMode) {
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

                        //pet update pixelLocation
                        if((float) (pHandle.getLocation().getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i);
                            pHandle.setPixelLocation(new Location(Math.round(pHandle.getLocation().getX()*.75f - 80), pHandle.getPixelLocation().getY(), 0));

                        }
                        else if((float) (pHandle.getLocation().getX())/StaticVar.terrainImageWidth > (tileViews.size() - StaticVar.xTilesFromEdge + 1)) {
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                            pHandle.setPixelLocation(new Location(Math.round((pHandle.getLocation().getX() - ((tileViews.size() - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), pHandle.getPixelLocation().getY(), 0));
                        }
                        else {
                            pHandle.setPixelLocation(new Location(Math.round(StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth*.75f - 80), pHandle.getPixelLocation().getY(), 0));
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
                        //set pet pixelLocation
                        if((float) (pHandle.getLocation().getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j);
                            pHandle.setPixelLocation(new Location(pHandle.getPixelLocation().getX(), Math.round(pHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                        }
                        else if((float) (aHandle.getLocation().getY())/StaticVar.terrainImageHeight > (tileViews.get(0).size() - StaticVar.yTilesFromEdge)) {
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                            pHandle.setPixelLocation(new Location(pHandle.getPixelLocation().getX(), Math.round((pHandle.getLocation().getY() - ((tileViews.get(0).size() - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                        }
                        else {
                            pHandle.setPixelLocation(new Location(pHandle.getPixelLocation().getX(), Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - (float) (aHandle.getLocation().getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
                        }

                        View.cameraLocation.setX(aHandle.getLocation().getX());
                        View.cameraLocation.setY(aHandle.getLocation().getY());

                        break;

                    case SCROLL_MODE:

                        if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                            tileViews.get(i).get(j).setCurrentXLocation(i);
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i);
                            aHandle.setPixelLocation(new Location(Math.round(aHandle.getLocation().getX()*.75f - 80), aHandle.getPixelLocation().getY(), 0));
                            pHandle.setPixelLocation(new Location(Math.round(pHandle.getLocation().getX()*.75f - 80), pHandle.getPixelLocation().getY(), 0));
                        }
                        else if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth > (tileViews.size() - StaticVar.xTilesFromEdge + 1)) {
                            tileViews.get(i).get(j).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - tileViews.size() + StaticVar.xTilesFromEdge*2 - 1);
                            aHandle.setPixelLocation(new Location(Math.round((aHandle.getLocation().getX() - ((tileViews.size() - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), aHandle.getPixelLocation().getY(), 0));
                            pHandle.setPixelLocation(new Location(Math.round((pHandle.getLocation().getX() - ((tileViews.size() - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), pHandle.getPixelLocation().getY(), 0));
                        }
                        else {
                            tileViews.get(i).get(j).setCurrentXLocation(i - (float) (View.cameraLocation.getX()) / StaticVar.terrainImageWidth + StaticVar.xTilesFromEdge);
                            aHandle.setPixelLocation(new Location(Math.round((StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth - View.cameraLocation.getX() + aHandle.getLocation().getX())*.75f - 80), aHandle.getPixelLocation().getY(), 0));
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentXLocation(i - (float) (aHandle.getLocation().getX()) / StaticVar.terrainImageWidth + StaticVar.xTilesFromEdge);
                            pHandle.setPixelLocation(new Location(Math.round((StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth - View.cameraLocation.getX() + pHandle.getLocation().getX())*.75f - 80), pHandle.getPixelLocation().getY(), 0));
                        }


                        //Y Stuff Below
                        if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                            tileViews.get(i).get(j).setCurrentYLocation(j);
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j);
                            aHandle.setPixelLocation(new Location(aHandle.getPixelLocation().getX(), Math.round(aHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                            pHandle.setPixelLocation(new Location(pHandle.getPixelLocation().getX(), Math.round(pHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                        }
                        else if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight > (tileViews.get(0).size() - StaticVar.yTilesFromEdge)) {
                            tileViews.get(i).get(j).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - tileViews.get(0).size() + StaticVar.yTilesFromEdge*2);
                            aHandle.setPixelLocation(new Location(aHandle.getPixelLocation().getX(), Math.round((aHandle.getLocation().getY() - ((tileViews.get(0).size() - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                            pHandle.setPixelLocation(new Location(pHandle.getPixelLocation().getX(), Math.round((pHandle.getLocation().getY() - ((tileViews.get(0).size() - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f)), 0));
                        }
                        else {
                            tileViews.get(i).get(j).setCurrentYLocation(j - (float) (View.cameraLocation.getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
                            aHandle.setPixelLocation(new Location(aHandle.getPixelLocation().getX(), Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)) - View.cameraLocation.getY() + aHandle.getLocation().getY(), 0));
                            //((TileView) getComponent((i * tileViews.get(0).size()) + j)).setCurrentYLocation(j - (float) (aHandle.getLocation().getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
                            pHandle.setPixelLocation(new Location(pHandle.getPixelLocation().getX(), Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)) - View.cameraLocation.getY() + pHandle.getLocation().getY(), 0));
                        }

                        //tileViews.get(i).get(j).setCurrentXLocation(i - (float) (View.cameraLocation.getX()) / StaticVar.terrainImageWidth + StaticVar.xTilesFromEdge);
                        //tileViews.get(i).get(j).setCurrentYLocation(j - (float) (View.cameraLocation.getY()) / StaticVar.terrainImageHeight + StaticVar.yTilesFromEdge);
                        //aHandle.setPixelLocation(new Location(Math.round((StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth - View.cameraLocation.getX() + aHandle.getLocation().getX())*.75f - 80), Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight - Math.round(StaticVar.terrainImageHeight*1.2f)) - View.cameraLocation.getY() + aHandle.getLocation().getY(), 0));

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
