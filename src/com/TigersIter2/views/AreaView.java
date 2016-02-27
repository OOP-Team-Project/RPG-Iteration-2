package com.TigersIter2.views;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class AreaView extends JComponent {

    //For now the constructor of area view takes in map and avatar.
    //we may edit this to take a list of components instead

    //sets layout for components to overlap.
    //and sets the preferred size of this component to same as JFrame
    public AreaView(MapView mapView, AvatarView avatarView){
        setLayout(new OverlayLayout(this));
        setPreferredSize(new Dimension(1280, 720));
        this.add(avatarView);
        this.add(mapView);
    }


    @Override
    public void paintComponent(Graphics g){
        //System.out.println("Inside AreaView");

    }


}
