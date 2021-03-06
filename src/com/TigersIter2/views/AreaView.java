package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class AreaView extends View {

    //For now the constructor of area view takes in map and avatar.
    //we may edit this to take a list of components instead

    //sets layout for components to overlap.
    //and sets the preferred size of this component to same as JFrame

    public AreaView(MapView mapView, AvatarView av, PetView pv, List<VehicleView> vv, FooterView fv, StatusView sv, List<NPCView> nv, ControlView cv, List<ItemView> iv, List<AreaEffectView> aev){
        setLayout(new OverlayLayout(this));
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        this.add(sv);
        this.add(cv);
        this.add(fv);
        for(VehicleView v : vv)
            this.add(v);
        this.add(av);
        this.add(pv);
        for(NPCView n : nv)
            this.add(n);
        for(ItemView i : iv)
            this.add(i);
        for(AreaEffectView ae : aev)
            this.add(ae);
        this.add(mapView);
    }

    @Override
    public void paintComponent(Graphics g){}
}
