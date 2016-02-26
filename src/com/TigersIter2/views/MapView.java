package com.iter2.views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */

//MapView has just terrains
public class MapView extends JComponent {

    //for now only setting preferred size.
    //always set preferred size on JComponents, JPanels, ect.
    public MapView() {
        setPreferredSize(new Dimension(1280, 720));

    }



    @Override
    public void paintComponent(Graphics g){
        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        //First we inherit the size of the parent
        System.out.println("Inside MapView");
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(Color.GREEN);
        g2d.fillRect(50,50,50,50);
        g2d.dispose();
    }

}
