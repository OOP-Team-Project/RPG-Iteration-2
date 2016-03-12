package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.skills.SkillTree;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

/**
 * Created by Magic_Buddha on 3/8/2016.
 */
public class SkillManagementView extends JComponent {

    private final int BOTTOM_OFFSET_BUG = 39;
    private final int STATS_OFFSET = 50;
    private final int SKILL_IMAGE_WIDTH = 150;
    private final int SKILL_IMAGE_HEIGHT = 150;
    private int rows;
    private int columns;
    private int numOfSkills;
    private String info;

    public SkillManagementView(SkillTree sk) {
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        setLayout(new FlowLayout());
        info = "Size: " + getWidth() + "x" + getHeight();


    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g.create();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.GRAY);
        g2d.fillRect(0,STATS_OFFSET,this.getWidth(),this.getHeight()-STATS_OFFSET);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("TimesRoman", Font.BOLD, 18));
        g2d.drawString("Size: " + getWidth() + "x" + getHeight(), 100, 100);
        paintSkills(g2d,(getWidth()/3) * 2, getHeight()-STATS_OFFSET-BOTTOM_OFFSET_BUG);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine((getWidth()/3) * 2, 50,(getWidth()/3) * 2, getHeight() );
        g2d.dispose();
    }

    private void paintSkills(Graphics2D g2d, int width, int height) {
        int marginBetweenSkills = (width - 4 * SKILL_IMAGE_WIDTH) / 5;
        int topMargin = (height - 2 * SKILL_IMAGE_HEIGHT) / 3;
        int sideMarginForThreeSkills = (width - 3 * SKILL_IMAGE_HEIGHT - 2 * marginBetweenSkills) / 2;

        paintSkillBox(g2d, sideMarginForThreeSkills, topMargin + STATS_OFFSET);
        paintSkillBox(g2d, sideMarginForThreeSkills + marginBetweenSkills + SKILL_IMAGE_WIDTH, topMargin + STATS_OFFSET);
        paintSkillBox(g2d, sideMarginForThreeSkills + marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH * 2, topMargin + STATS_OFFSET);

        paintSkillBox(g2d, marginBetweenSkills, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET);
        paintSkillBox(g2d, marginBetweenSkills * 2 + SKILL_IMAGE_WIDTH, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET);
        paintSkillBox(g2d, marginBetweenSkills * 3 + SKILL_IMAGE_WIDTH * 2, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET);
        paintSkillBox(g2d, marginBetweenSkills * 4 + SKILL_IMAGE_WIDTH * 3, topMargin * 2 + SKILL_IMAGE_HEIGHT + STATS_OFFSET);
    }

    private void paintSkillBox(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.black);
        g2d.drawRect(x,y,SKILL_IMAGE_WIDTH,SKILL_IMAGE_HEIGHT);
    }

    private int getMargin(int skillSize, int numSkills) {
        return (StaticVar.gameWidth - numSkills * skillSize) / 5;
    }
}
