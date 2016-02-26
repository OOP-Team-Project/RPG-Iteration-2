package com.TigersIter2.views;

import com.TigersIter2.entities.Avatar;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class AvatarView extends JComponent{

    Avatar aHandle;

    public AvatarView(){
        setPreferredSize(new Dimension(1280, 720));
    }

    public AvatarView(Avatar a) {
        aHandle = a;
        setPreferredSize(new Dimension(1280, 720));
    }

    public void paintComponent(Graphics g){
        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        //System.out.println("Inside AvatarView");
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);
        g2d.fillOval(aHandle.getLocation().getX(), aHandle.getLocation().getY(), 50, 50);
        g2d.drawString("I'm Avatar", aHandle.getLocation().getX(), aHandle.getLocation().getY());
        g2d.dispose();
    }

}
