package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class FooterView extends View implements ActionListener{

    int currentAnimationFrame = 0;
    private boolean display;
    private int type;
    private List<String> menuOptions;

    public FooterView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth - 400, 200));
        display = false;
        menuOptions = new ArrayList<String>();
        type = 0;
    }

    public void setDisplay(boolean b){
        display = b;
    }

    //0 denotes a menu, 1 denotes a conversation
    public void setType(int i){
        type = i;
    }

    //MAXIMUM OF 5 OPTIONS
    public void setMenuOptions(List<String> list){
        menuOptions = list;
    }


    public void actionPerformed(ActionEvent e) {
        currentAnimationFrame = (currentAnimationFrame + 1) % 2;
    }


    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if(display) {
            g2d.setColor(new Color(0.7607843f, 0.98039216f, 0.79607844f));
            g2d.fillRect(200, StaticVar.gameHeight - 200, StaticVar.gameWidth - 400, 200);
            g2d.setColor(Color.black);
            g2d.setFont(new Font("TimesRoman", Font.BOLD, 20));
            int height = StaticVar.gameHeight - 170;
            int i = 0;
            for(String s : menuOptions) {
                ++i;
                if(type == 0) {
                    g2d.drawString(i + ") " + s, 225, height);
                }
                else if(type == 1) {
                    g2d.drawString(s, 225, height);
                    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 16));
                    g2d.drawString("Press Backspace to continue...", 225, height + 120);
                }
                height += 30;
            }
        }
        g2d.dispose();
    }

}
