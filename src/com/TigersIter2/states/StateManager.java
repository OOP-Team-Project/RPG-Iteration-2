package com.TigersIter2.states;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.IntroSprite;
import com.TigersIter2.main.Controller;

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
    private static final int INTRO = 0;
    public static final int GAME = 1;



    public StateManager(){
        setState(INTRO);
        //controller = new Controller(this);
        //controller.setBindings();
        //this.setLayout(new OverlayLayout(this));
        //this.setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    //kill state and set to new State (SL)
    public void setState(int nextState){

        if (state!=null)this.remove(state);
        //state = null;

        if(nextState == INTRO){
            state = new IntroState(this);
            state.init();
        }
        else if (nextState == GAME){
            state = new GameState(this);
            state.init();
        }

        this.add(state);
        this.setLayout(new OverlayLayout(this));
        this.setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public void update(){

        if (state!=null)
            state.update();
            System.out.println(state.returnName());
    }

    @Override
    public void paintComponent(Graphics g){
//        Graphics2D g2d = (Graphics2D)g.create();
//        g2d.setColor(Color.BLUE);
//        g2d.fillRect(50,50,50,50);
//        g2d.dispose();
        //state.draw(g);
        state.repaint();
        //repaint();
    }

    //don't think it actually needs a draw function, since repaint in main will repaint all children - Sam
    //public void draw(Graphics g){
        //if (state!=null) state.draw(g);
    //}


}
