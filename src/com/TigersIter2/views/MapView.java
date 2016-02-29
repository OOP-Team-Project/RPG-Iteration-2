package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.maps.Map;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */

//MapView has just terrains
public class MapView extends JComponent {

    Map mHandle;

    //for now only setting preferred size.
    //always set preferred size on JComponents, JPanels, ect.
    public MapView() {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public MapView(Map map) {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        mHandle = map;
    }



    @Override
    public void paintComponent(Graphics g){
        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        //First we inherit the size of the parent
        //System.out.println("Inside MapView");
        Graphics2D g2d = (Graphics2D)g.create();
        /*g2d.setColor(Color.GREEN);
        g2d.fillRect(400,400,50,50);
        g2d.drawString("This is map paint",375, 380);
        g2d.dispose();*/

        //START (Miles, 4:34 PM Sunday Feb. 28)
        //So... does this make sense? MapView orders the Map to draw all of its tiles (and gives it where to draw them)

        mHandle.drawAllTiles(g2d);

        //END

        g2d.dispose();
    }

}
