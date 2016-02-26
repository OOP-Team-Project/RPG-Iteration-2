package com.TigersIter2.main;

import com.TigersIter2.entities.Avatar;
import com.TigersIter2.views.AreaView;
import com.TigersIter2.views.AvatarView;
import com.TigersIter2.views.MapView;

import javax.swing.*;
import java.awt.*;

/**
 * Created by slichtenheld on 2/25/2016.
 */
public class RunGame extends JFrame {

    //JFrame window;
    private GamePanel gamePanel;
    private boolean running = false;

    private final int FPS = 60;
    private final int TARGET_TIME = 1000 / FPS;

    public static void main(String[] args){
        RunGame runGame = new RunGame();
        runGame.setLocationRelativeTo(null); //window appears in center of screen
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

    public void loadGame(){
        setSize(1280, 720);
        MapView mapView = new MapView();
        AvatarView avatarView = new AvatarView();
        AreaView areaView = new AreaView(mapView,avatarView);
        System.out.println("areaView # of components: " + areaView.getComponentCount());
        gamePanel = new GamePanel();
        gamePanel.add(areaView);
        System.out.println("gamePanel # of components: " + gamePanel.getComponentCount());
        System.out.println("JFrame # of components: " + this.getComponentCount());
        this.add(gamePanel);
        this.setVisible(true);
        this.setFocusable(true);

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
    private void updateGame()
    {
        gamePanel.update();
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
                    System.out.println("Had to invoke repaint from the wrong thread onto EDT");

                    gamePanel.repaint();
                }
            });
        }
    }
}
