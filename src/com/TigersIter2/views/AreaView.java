package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.sun.corba.se.impl.orbutil.graph.Graph;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class AreaView extends View {

    //For now the constructor of area view takes in map and avatar.
    //we may edit this to take a list of components instead

    //sets layout for components to overlap.
    //and sets the preferred size of this component to same as JFrame
    public AreaView(MapView mapView, AvatarView avatarView, VehicleView vehicleView, FooterView footerView){
        setLayout(new OverlayLayout(this));
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        this.add(footerView);
        this.add(vehicleView);
        this.add(avatarView);
        //MapView is fucked up - Sam
        this.add(mapView);
    }


    @Override
    public void paintComponent(Graphics g){
        //System.out.println("Inside AreaView");
        g.drawString("AreaView PaintComponent: " + this.getComponentCount(), 260, 200);
    }


}
