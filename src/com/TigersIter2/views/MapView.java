package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.maps.Map;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.maps.terrains.TerrainType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by slichtenheld on 2/25/2016.
 */

//MapView has just terrains
public class MapView extends JComponent {

    TerrainMap mHandle;
    Avatar aHandle;
    ArrayList<ArrayList<TileView>> tileViews = new ArrayList<ArrayList<TileView>>();

    public enum mapMode {
        PLAYER_FOLLOW_MODE
    }

    mapMode currentMapMode = mapMode.PLAYER_FOLLOW_MODE;

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

        for(int i = 0; i < mHandle.getTerrainTypes().get(0).size(); i++){
            tileViews.add(new ArrayList<TileView>());
            for(int j = 0; j < mHandle.getTerrainTypes().size(); j++){
                tileViews.get(i).add(new TileView(i, j, mHandle.getTerrainTypes().get(i).get(j), i));
            }
        }

    }


    @Override
    public void paintComponent(Graphics g){
        for(int i = 0; i < tileViews.size(); i++) {
            for(int j = 0; j < tileViews.get(0).size(); j++) {
                switch (currentMapMode) {
                    case PLAYER_FOLLOW_MODE:

                        System.out.println(aHandle.getLocation().getX()/StaticVar.terrainImageWidth);

                        if((float) (aHandle.getLocation().getX())/StaticVar.terrainImageWidth < 7f)
                            tileViews.get(i).get(j).setCurrentXLocation(i);
                        else if((float) (aHandle.getLocation().getX() - 7)/StaticVar.terrainImageWidth > (tileViews.size() - 10))
                            tileViews.get(i).get(j).setCurrentXLocation(i - tileViews.size() + 10 + 7);
                        else
                            tileViews.get(i).get(j).setCurrentXLocation(i - (float) (aHandle.getLocation().getX())/StaticVar.terrainImageWidth + 7);

                        if((float) (aHandle.getLocation().getY())/StaticVar.terrainImageHeight < 3f)
                            tileViews.get(i).get(j).setCurrentYLocation(j);
                        else if((float) (aHandle.getLocation().getY())/StaticVar.terrainImageHeight > (tileViews.size() - 5))
                            tileViews.get(i).get(j).setCurrentYLocation(j - tileViews.get(0).size() + 8);
                        else
                            tileViews.get(i).get(j).setCurrentYLocation(j - (float) (aHandle.getLocation().getY())/StaticVar.terrainImageHeight + 3);
                        break;
                    default:
                        break;
                }

                tileViews.get(i).get(j).paintComponent(g);
            }
        }
    }


}
