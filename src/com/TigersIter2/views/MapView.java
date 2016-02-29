package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.maps.Map;
import com.TigersIter2.maps.TerrainMap;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */

//MapView has just terrains
public class MapView extends JComponent {

    TerrainMap mHandle;

    //for now only setting preferred size.
    //always set preferred size on JComponents, JPanels, ect.
    public MapView() {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public MapView(TerrainMap map) {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        mHandle = map;

        //TODO: Fix this it's so ugly gaaaaaaaaaa

        for(int i = 0; i < mHandle.getTerrainTypes().get(0).size(); i++){
            for(int j = 0; j < mHandle.getTerrainTypes().size(); j++){
                this.add(new TileView(i, j, mHandle.getTerrainTypes().get(i).get(j)));
            }
        }
    }


    @Override
    public void paintComponent(Graphics g){
//        Graphics2D g2d = (Graphics2D)g.create();
//        //mHandle.drawAllTiles(g2d);
//        g2d.dispose();
        for(int i = 0; i < getComponentCount(); i++)
            ((TileView) getComponent(i)).paintComponent(g);
    }


}
