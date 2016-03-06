package com.TigersIter2.states;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.IntroSprite;
import com.TigersIter2.main.Controller;
import com.sun.org.apache.xpath.internal.SourceTree;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/29/2016.
 */

// TODO: add ability to pause
public class StateManager extends JPanel{

    private State state;

    //StateManager holds controller and passes it to different states
    private Controller controller;

    private static final int NUMBER_OF_STATES = 2;
    public static final int INTRO = 0;
    public static final int MAINMENU = 1;
    public static final int GAME = 2;


//    private enum S {
//        INTRO,MAINMENU,GAME
//    }

    public StateManager(){
        this.setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        this.setLayout(new FlowLayout(0,0,0)); //JPanel is already a flowlayout, added 0,0,0 to remove padding --flow layout paints children but does not require them
//        this.setLayout(new OverlayLayout(this));
        System.out.println(this.getComponentCount());

        controller = new Controller(this);
        controller.setBindings();


        setState(INTRO);



    }

    //kill state and set to new State (SL)
    public void setState(int nextState){
        if (state!=null)this.remove(state); //invalidates layout, since things have been changed
        //state = null;

        if(nextState == INTRO){
            System.out.println("Creating INTRO state...");
            state = new IntroState(this, controller);
            state.init();
        }
        else if(nextState == MAINMENU){
            System.out.println("Creating MainMenu state...");
            state = new MainMenuState(this, controller);
            state.init();
        }
        else if (nextState == GAME){
            System.out.println("Creating GAME state...");
            state = new GameState(this, controller);
            state.init();
        }

        this.add(state);
        this.revalidate(); //revalidates layout, needed since removing state before
        this.repaint();
    }

    public void update(){

        if (state!=null) {
            state.update();
            //System.out.println(state.returnName());
            //System.out.println(controller.getXMovement() + ", " + controller.getyMovement());
        }
    }

    @Override
    public void paintComponent(Graphics g){ //doesn't need to override paintComponent unless we want solid background, might be nice for edge of maps

    }

}
