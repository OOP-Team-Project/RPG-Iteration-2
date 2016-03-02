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

    private State state, prevState;
    private Controller controller;

    private static final int NUMBER_OF_STATES = 2;
    public static final int INTRO = 0;
    public static final int GAME = 1;



    public StateManager(){
        this.setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));

        this.setLayout(new OverlayLayout(this));
        System.out.println(this.getComponentCount());
        setState(INTRO);
        //controller = new Controller(this);
        //controller.setBindings();
    }

    //kill state and set to new State (SL)
    public void setState(int nextState){
        if (state!=null)this.remove(state);
        //state = null;

        if(nextState == INTRO){
            System.out.println("Creating INTRO state...");
            state = new IntroState(this);
            state.init();
        }
        else if (nextState == GAME){
            System.out.println("Creating GAME state...");
            state = new GameState(this);
            state.init();
        }

        this.add(state);
        this.revalidate();
        this.repaint();
//        this.setLayout(new OverlayLayout(this));
//        this.setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public void update(){

        if (state!=null)
            state.update();
            System.out.println(state.returnName());
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(Color.BLUE);
        g2d.fillRect(0,0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.RED);
        g2d.drawString("StateManager paintComponent. Components:" + this.getComponentCount() + "WIDTH: " + this.getWidth() + " Height: " + this.getHeight(), 260,100);
        g2d.dispose();
        //state.draw(g);
        //state.repaint();
    }

    //don't think it actually needs a draw function, since repaint in main will repaint all children - Sam
    //public void draw(Graphics g){
        //if (state!=null) state.draw(g);
    //}


}
