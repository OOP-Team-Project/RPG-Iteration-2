package com.TigersIter2.states;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.IntroSprite;
import com.TigersIter2.assets.sprites.SmasherSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Smasher;
import com.TigersIter2.main.Controller;
import com.TigersIter2.views.AvatarView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/29/2016.
 */
public class IntroState extends State {

    private String name;

    private int counter;

    public IntroState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {

        counter = 0;
        //pull in picture for intro screen - Sam
        IntroSprite.init();
        System.out.println("IntroState initialized");
        name = "IntroState";
    }

    @Override
    public Dimension getPreferredSize() { //good practice, don't know if necessary or not
        return new Dimension(StaticVar.gameWidth, StaticVar.gameHeight);
    }

    @Override
    public void update() {
        counter++;
        System.out.println("IntroState counter: " + counter);
        if (counter >= 60) {
            stateManager.setState(stateManager.MAINMENU);
            counter = 0;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.drawImage(IntroSprite.introImage,0,0,null);
//        g2d.setColor(Color.GREEN);
//        g2d.fillRect(0,20, this.getWidth(), this.getHeight()-20);
        g2d.setColor(Color.RED);
        g2d.drawString("Intro paintComponent. Components: " + this.getComponentCount(), 0, 400);
        g2d.dispose();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public String returnName() {
        return name;
    }


}
