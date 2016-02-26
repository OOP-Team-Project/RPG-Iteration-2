package com.TigersIter2.views;

import com.TigersIter2.entities.Avatar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class AvatarView extends JComponent{


    public AvatarView(){
        setPreferredSize(new Dimension(1280, 720));
    }


    public void paintComponent(Graphics g){
        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        System.out.println("Inside AvatarView");
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(Color.BLUE);
        g2d.fillRect(500,500, 50,50);
        g2d.dispose();
    }

}
