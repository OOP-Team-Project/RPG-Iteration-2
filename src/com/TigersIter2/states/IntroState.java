package com.TigersIter2.states;

import com.TigersIter2.assets.sprites.*;
import com.TigersIter2.managers.StateManager;
import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.main.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by slichtenheld on 2/29/2016.
 */
public class IntroState extends State {

    private String name;

    public IntroState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {

        //pull in picture for intro screen - Sam
        IntroSprite.init();

        //WTF this fixes map not displaying bug, DOESN'T MAKE SENSE
        TerrainSprite.init();
        //Initialize for MainMenuState and NewGameState
        WizardSprite.init();
        SmasherSprite.init();
        SneakSprite.init();

        System.out.println("IntroState initialized");
        name = "IntroState";
    }

    @Override
    public Dimension getPreferredSize() { //good practice, don't know if necessary or not
        return new Dimension(StaticVar.gameWidth, StaticVar.gameHeight);
    }

    @Override
    public void update(long elapsed) {

        if ((controller.getKeyPressed()== KeyEvent.VK_ENTER || controller.getKeyPressed()==KeyEvent.VK_SPACE)) {
            stateManager.setState(StateManager.MAINMENU);
        }
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

    @Override
    public String returnName() {
        return name;
    }


}
