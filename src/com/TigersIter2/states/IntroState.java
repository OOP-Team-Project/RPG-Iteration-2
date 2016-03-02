package com.TigersIter2.states;

import com.TigersIter2.assets.sprites.IntroSprite;

import java.awt.*;

/**
 * Created by slichtenheld on 2/29/2016.
 */
public class IntroState extends State {

    private int counter;

    public IntroState(StateManager stateManager){
        super(stateManager);
    }

    @Override
    public void init() {
        counter = 0;
        //pull in picture for intro screen - Sam
        IntroSprite.init();
    }

    @Override
    public void update() {
        counter++;
        if (counter >= 300) stateManager.setState(stateManager.GAME);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.drawImage(IntroSprite.introImage,0,0,null);
        g2d.dispose();
    }


    @Override
    public void handleInput() {

    }
}
