package com.TigersIter2.main;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.SmasherSprite;
import com.TigersIter2.assets.sprites.SneakSprite;
import com.TigersIter2.assets.sprites.TerrainSprite;
import com.TigersIter2.assets.sprites.WizardSprite;
import com.TigersIter2.entities.*;
import com.TigersIter2.maps.Map;
import com.TigersIter2.maps.TerrainMap;
import com.TigersIter2.views.AreaView;
import com.TigersIter2.views.AvatarView;
import com.TigersIter2.views.MapView;

import javax.swing.*;
import java.awt.*;

//contains main method, kicks off everything
public class RunGame extends JFrame {

    //JFrame window;
    private GamePanel gamePanel;
    //dirty way of avatar. disregard this
    Avatar a;

    //Talks to the controller
    private Controller controller;
    private boolean running = false;

    private final int FPS = StaticVar.fps;
    private final int TARGET_TIME = 1000 / FPS;

    public static void main(String[] args){
        RunGame runGame = new RunGame();
        //runGame.setLocationRelativeTo(null); //doesn't work here, added to loadGame(SL)
        runGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //process quits when x button is pressed
        runGame.setResizable(false);
        runGame.runGameLoop();
    }

    //sam's tests//

//    public RunGame(){
//        super("GameLoopTest");
//        Container cp = getContentPane();
//        //cp.setLayout(new BorderLayout());
//        //JPanel mainPanel = new JPanel(); //allows us to have panels on the side, permanent buttons etc
//        //mainPanel.setLayout(new GridLayout(1,2)); //change depending on layout wanted
//        //gamePanel = new GamePanel(new AreaView(new MapView(),new AvatarView(new Avatar())));
//        //p.setLayout(new GridLayout(1,2));
//        cp.add(gamePanel, BorderLayout.CENTER);
//        //cp.add(p, BorderLayout.SOUTH);
//        setSize(1280, 720);
//    }

    //loads the game
    public void loadGame(){

        //load image assets(SL) --> MAY HAVE TO ADD RES LIBRARY TO PROJECT IN INTELLIJ
        TerrainSprite.init();
        WizardSprite.init();
        SmasherSprite.init();
        SneakSprite.init();

        setSize(StaticVar.gameWidth, StaticVar.gameHeight); //added class for static variables
        setLocationRelativeTo(null); //window appears in center of screen(SL)
        TerrainMap m = new TerrainMap();
        MapView mapView = new MapView(m);

//        Avatar a = new Avatar();
        a = new Avatar();

        //**************TESTING CODE*********************
        a.setOccupation(new Sneak());
        AvatarView avatarView = new AvatarView(a);


        AreaView areaView = new AreaView(mapView,avatarView);
        System.out.println("areaView # of components: " + areaView.getComponentCount());
        gamePanel = new GamePanel();
        gamePanel.add(areaView);
        System.out.println("gamePanel # of components: " + gamePanel.getComponentCount());
        System.out.println("JFrame # of components: " + this.getComponentCount());
        this.add(gamePanel);
        this.setVisible(true);
        //this.setFocusable(true);

        //Instantiate the controller and bind the default keys
        controller = new Controller(gamePanel);
        controller.setBindings();
    }

    //Starts a new thread and runs the game loop in it.
    public void runGameLoop()
    {
        loadGame();
        running = true;
        Thread loop = new Thread()
        {
            public void run()
            {
                gameLoop();
            }
        };
        loop.start();
    }

    //Only run this in another Thread!
    private void gameLoop() {

        System.out.println("gameLoop called, running = " + running);
        long start;
        long elapsed;
        long wait;

        // game loop
        while (running) {

            start = System.nanoTime();

            updateGame();
            renderGame();

            elapsed = System.nanoTime() - start;

            wait = TARGET_TIME - elapsed / 1000000;
            if (wait < 0) wait = TARGET_TIME;

            try {
                Thread.sleep(wait);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //calls the update of current panel
    //for testing purposes, dirty way of updating avatar.
    //we should prolly have a updateManager class
    //that holds objects that should be updated, or just use events or w/e
    private void updateGame()
    {
        gamePanel.update();
        a.update(controller.getXMovement(), controller.getyMovement());
    }

    //renders the contents of current panel
    //first if statement checks if we are executing repaint on
    //the correct thread for swing functions.
    //if no, we dispatch the repaint to the correct thread (EDT)
    private void renderGame() {
        if (SwingUtilities.isEventDispatchThread()) {
            System.out.println("On the right thread for using swing method repaint");
            this.repaint();
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    //System.out.println("Had to invoke repaint from the wrong thread onto EDT");

                    gamePanel.repaint();
                }
            });
        }
    }
}
