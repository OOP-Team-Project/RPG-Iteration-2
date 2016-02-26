package com.TigersIter2.main;

import com.TigersIter2.views.AreaView;
import com.TigersIter2.views.MapView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class GamePanel extends JPanel {

    private int testX, testY;

    AreaView areaView;

    //State

    public GamePanel(){
        //initialize everything, initialize State -> calls initialize world/player etc
        this.setLayout(new OverlayLayout(this));
        this.setPreferredSize(new Dimension(1280, 720));


        testX = 0;
        testY = 0;

    }

    //sams test
    public void update(){
        testX += 2;
        testY += 2;
    }

    //for now we just paint the gray background for the main panel
    @Override
    public void paintComponent(Graphics g){
        System.out.println("Inside GP PCM");
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,0,1280,720);
        g2d.setColor(Color.ORANGE);
        g2d.fillRect(testX,testY,25,25);
        g2d.drawString("I'm GamePanel", testX, testY);
        g2d.dispose();
    }

}
