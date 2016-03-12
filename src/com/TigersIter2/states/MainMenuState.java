package com.TigersIter2.states;

import com.TigersIter2.managers.StateManager;
import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.MainMenuSprite;
import com.TigersIter2.main.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;


//JButtons fuck with threads!
public class MainMenuState extends State {


    private final boolean newGame = true;
    private final boolean loadGame = false;

    private String name;
    private boolean newLoad = false;
    private int counterBuffer = 0;


    public MainMenuState(StateManager stateManager, Controller controller){
        super(stateManager, controller);
    }

    @Override
    public void init() {
        System.out.println("IntroState initialized");
        name = "MainMenuState";
        counterBuffer = 0;
        MainMenuSprite.init();
        controller.menuBindings();

    }

    @Override
    public void update(long elapsed) {
        counterBuffer++;

        if (controller.getKeyPressed()== KeyEvent.VK_LEFT) newLoad = newGame;
        else if (controller.getKeyPressed() == KeyEvent.VK_RIGHT) newLoad = loadGame;

        if (controller.getKeyPressed()==KeyEvent.VK_ENTER && counterBuffer>=3){
            if (newLoad == newGame) stateManager.setState(StateManager.NEWGAME);
            else stateManager.setState(StateManager.GAME);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setColor(new Color(0.7607843f, 0.98039216f, 0.79607844f));
        g2d.fillRect(0,0, this.getWidth(), this.getHeight());
        //g2d.drawImage(MainMenuSprite.mainMenuImage,195,50,null);
        g2d.drawImage(MainMenuSprite.Iteration,250,175,null);
        g2d.drawImage(MainMenuSprite.largeWizard,725,150,null);
        g2d.drawImage(MainMenuSprite.largeSneak,875,150,null);
        if (newLoad) {
            g2d.drawImage(MainMenuSprite.newGameButtonPressed,270,400,null);
            g2d.drawImage(MainMenuSprite.loadGameButton,685,400,null);
        }
        else {
            g2d.drawImage(MainMenuSprite.newGameButton, 270, 400, null);
            g2d.drawImage(MainMenuSprite.loadGameButtonPressed,685,400,null);
        }
        g2d.drawImage(MainMenuSprite.mainMenuTextImage, 390, 525, null);
        //g2d.drawString("MainMenu paintComponent. Components: " + this.getComponentCount(), 100, 500);
        g2d.dispose();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public String returnName() {
        return name;
    }

    //Overriding component stuff, may be unnecessary
    @Override
    public Dimension getPreferredSize() { //good practice, don't know if necessary or not
        return new Dimension(StaticVar.gameWidth, StaticVar.gameHeight);
    }


}
