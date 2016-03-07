package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.SmasherSprite;
import com.TigersIter2.assets.sprites.SneakSprite;
import com.TigersIter2.assets.sprites.VehicleSprite;
import com.TigersIter2.assets.sprites.WizardSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Vehicle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class VehicleView extends JComponent implements ActionListener{

    Vehicle vHandle;
    int animationDelay = 100;
    int currentAnimationFrame = 0;
    Timer animationTimer;

    public VehicleView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public VehicleView(Vehicle v) {
        vHandle = v;
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        animationTimer = new Timer(animationDelay, this);
        animationTimer.start();

    }

    public void actionPerformed(ActionEvent e) {
        currentAnimationFrame = (currentAnimationFrame + 1) % 2;
    }

    private void drawVehicle(Graphics2D g2d){
        if (vHandle.getDirection() == 45) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VehicleSprite.vehicleUpRight1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VehicleSprite.vehicleUpRight2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 90) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VehicleSprite.vehicleUp1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VehicleSprite.vehicleUp2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 135) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VehicleSprite.vehicleUpLeft1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VehicleSprite.vehicleUpLeft2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 225) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VehicleSprite.vehicleDownLeft1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VehicleSprite.vehicleDownLeft2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 270) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VehicleSprite.vehicleDown1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VehicleSprite.vehicleDown2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
        else if (vHandle.getDirection() == 315) {
            if(currentAnimationFrame == 0 || !vHandle.isCurrentlyMoving())
                g2d.drawImage(VehicleSprite.vehicleDownRight1, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(VehicleSprite.vehicleDownRight2, vHandle.getPixelLocation().getX(), vHandle.getPixelLocation().getY(), null);
        }
    }

    public void paintComponent(Graphics g){
        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        drawVehicle(g2d);
        g2d.dispose();
    }

}
