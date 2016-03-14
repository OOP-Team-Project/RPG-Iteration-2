package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.main.Controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ControlView extends View{

    private final int VIEW_X_START = 75;
    private final int VIEW_Y_START = 130;

    private boolean display;
    private int side;
    private int highlighted;
    private int totalWidth;
    private int totalHeight;
    private boolean changingKey = false;
    private boolean duplicateKey = false;
    private Controller controller;
    private KeyListener listen;

    public ControlView(Controller c){
        setPreferredSize(new Dimension(StaticVar.gameWidth - 400, 200));
        display = false;
        side = 0;
        highlighted = 0;
        totalWidth = StaticVar.gameWidth - 150;
        totalHeight = StaticVar.gameHeight - 200;
        controller = c;
        listen = new ChangeControls();
    }

    public void handleInput(int input){
        if(input == 0){
            decrementHighlighted();
        }
        else if(input == 1){
            incrementHighlighted();
        }
        else if(input == 2){
            decrementSide();
        }
        else if(input == 3){
            incrementSide();
        }
        else if(input == 4){
            if(!changingKey) {
                if (side == 2) {
                    resetView();
                    display = false;
                    controller.setControlViewControls(display);
                } else {
                    controller.getComponent().addKeyListener(listen);
                }
            }
        }
        else if(input ==5){
            //resetView();
        }
    }

    public void toggle(){
        display = !display;
    }

    public boolean getDisplay(){
        return display;
    }


    public void incrementHighlighted(){
        if(!changingKey) {
            if (side == 0) {
                if (highlighted < 11)
                    ++highlighted;
                else
                    side = 2;
            } else if (side == 1) {
                if (highlighted < controller.getControlNames().size() - 1)
                    ++highlighted;
                else
                    side = 2;
            }
        }
    }

    public void decrementHighlighted(){
        if(!changingKey) {
            if (side == 2 && highlighted == controller.getControlNames().size() - 1)
                side = 1;
            else if (side == 2 && highlighted == 11)
                side = 0;
            else if (highlighted > 0)
                --highlighted;
        }
    }

    public void incrementSide(){
        if(!changingKey) {
            if (side == 0) {
                highlighted = 12;
                ++side;
            }
        }
    }

    public void decrementSide(){
        if(!changingKey) {
            if (side == 1) {
                highlighted = 0;
                --side;
            }
        }
    }

    public int getSide(){
        return side;
    }

    public void resetView(){
        side = 0;
        highlighted = 0;
    }


    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);


        if(display) {
            g2d.setColor(new Color(0.3f, 0.3f, 0.3f));
            g2d.fillRect(300, 40, StaticVar.gameWidth - 600, 80);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 60));
            g2d.drawString("C O N T R O L S", 400, 100);

            g2d.fillRect(VIEW_X_START - 2, VIEW_Y_START - 2, totalWidth + 4, totalHeight + 4);
            g2d.setColor(new Color(0.7607843f, 0.98039216f, 0.79607844f));
            g2d.fillRect(VIEW_X_START, VIEW_Y_START, totalWidth, totalHeight);

            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2d.drawString("Movement", VIEW_X_START+230, VIEW_Y_START+35);

            int yVal = VIEW_Y_START+65;
            int xVal = VIEW_X_START+170;
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("Up-Left:",xVal, yVal+30);
            g2d.drawString("Up:",xVal, yVal+60);
            g2d.drawString("Up-Right:",xVal, yVal+90);
            g2d.drawString("Down-Left:",xVal, yVal+120);
            g2d.drawString("Down:",xVal, yVal+150);
            g2d.drawString("Down-Right:",xVal, yVal+180);
            g2d.drawString("Pan Up-Left:",xVal, yVal+210);
            g2d.drawString("Pan Up:",xVal, yVal+240);
            g2d.drawString("Pan Up-Right:",xVal, yVal+270);
            g2d.drawString("Pan Down-Left:",xVal, yVal+300);
            g2d.drawString("Pan Down:",xVal, yVal+330);
            g2d.drawString("Pan Down-Right:",xVal, yVal+360);
            for(int i = 0; i < 12; ++i){
                yVal += 30;
                g2d.setColor(Color.black);
                if(highlighted == i && side == 0){
                    if(changingKey)
                        g2d.setColor(Color.red);
                    else
                        g2d.setColor(Color.white);
                }
                g2d.drawString(controller.getControlNames().get(i), xVal+165, yVal);
            }



            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 40));
            g2d.drawString("Actions", VIEW_X_START+totalWidth/2+200, VIEW_Y_START+35);

            yVal = VIEW_Y_START+65;
            xVal = VIEW_X_START+totalWidth/2+140;
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("Attack:",xVal, yVal+30);
            g2d.drawString("Mount:",xVal, yVal+60);
            g2d.drawString("Status View:",xVal, yVal+90);
            g2d.drawString("Menu Back:",xVal, yVal+120);
            g2d.drawString("Menu Select:",xVal, yVal+150);
            g2d.drawString("Use Skills:",xVal, yVal+180);
            for(int i = 12; i < controller.getControlNames().size(); ++i){
                yVal += 30;
                g2d.setColor(Color.black);
                if(highlighted == i && side == 1){
                    if(changingKey)
                        g2d.setColor(Color.red);
                    else
                        g2d.setColor(Color.white);
                }
                g2d.drawString(controller.getControlNames().get(i), xVal+130, yVal);
            }

            if(duplicateKey){
                g2d.setColor(Color.red);
                g2d.drawString("That key is already being used!", VIEW_X_START+totalWidth/2-120, VIEW_Y_START+totalHeight-150 + 5);
                g2d.setColor(Color.black);
            }

            g2d.setColor(Color.gray);
            if(side == 2){
                g2d.setColor(Color.red);
            }
            g2d.fillRect(VIEW_X_START+totalWidth/2-60, VIEW_Y_START+totalHeight - 100, 120, 50);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            g2d.drawString("Done", VIEW_X_START+totalWidth/2-20, VIEW_Y_START+totalHeight-75 + 5);
        }

        g2d.dispose();
    }

    public class ChangeControls implements KeyListener{
        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(changingKey) {
                if(controller.getControlCodes().contains(e.getKeyCode())){
                    duplicateKey = true;
                }
                else {
                    controller.changeControl(highlighted, e.getKeyCode());
                    controller.getComponent().removeKeyListener(listen);
                    controller.setControlViewControls(display);
                    duplicateKey = false;
                }
            }
            if(!duplicateKey)
                changingKey = !changingKey;

        }
    }

}
