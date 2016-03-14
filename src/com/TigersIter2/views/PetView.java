package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.PetSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.entities.Pet;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Breanna on 3/11/16.
 */
public class PetView extends View implements ActionListener {
    Avatar aHandle;
    Pet pHandle;
    int animationDelay = 100;
    int currentAnimationFrame = 0;
    Timer animationTimer;
    Location pixelLocation;
    int mapXLength, mapYLength = 0;

    public PetView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public PetView(Pet p, Avatar a, TerrainMap map) {
        aHandle = a;
        pHandle = p;
        mapXLength = map.getMapWidth();
        mapYLength = map.getMapHeight();


        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
        animationTimer = new Timer(animationDelay, this);
        animationTimer.start();
        pixelLocation = pHandle.getLocation();

    }

    public void actionPerformed(ActionEvent e) { currentAnimationFrame = (currentAnimationFrame + 1) % 2; }

    private void drawPet(Graphics2D g2d) {
        //System.out.println("Pet's pixel location: " + pHandle.getPixelLocation().toString());
        if (pHandle.getDirection() == 45) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petUpRight1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(PetSprite.petUpRight2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (pHandle.getDirection() == 90) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petUp1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(PetSprite.petUp2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (pHandle.getDirection() == 135) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petUpLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(PetSprite.petUpLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (pHandle.getDirection() == 225) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petDownLeft1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(PetSprite.petDownLeft2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (pHandle.getDirection() == 270) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petDown1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(PetSprite.petDown2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if (pHandle.getDirection() == 315) {
            if(currentAnimationFrame == 0 || !aHandle.isCurrentlyMoving())
                g2d.drawImage(PetSprite.petDownRight1, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(PetSprite.petDownRight2, pixelLocation.getX(), pixelLocation.getY(), null);
        }
    }

    public void paintComponent(Graphics g){
        if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
            setPixelLocation(new Location(Math.round(pHandle.getLocation().getX()*.75f - 80), pixelLocation.getY()));
        }
        else if((float) (View.cameraLocation.getX())/StaticVar.terrainImageWidth > (mapXLength - StaticVar.xTilesFromEdge + 1)) {
            setPixelLocation(new Location(Math.round((pHandle.getLocation().getX() - ((mapXLength - StaticVar.xTilesFromEdge*2 + 1) * StaticVar.terrainImageWidth))*.75f - 80), pixelLocation.getY()));
        }
        else  {
            setPixelLocation(new Location(Math.round((pHandle.getLocation().getX() - View.cameraLocation.getX() + StaticVar.xTilesFromEdge*StaticVar.terrainImageWidth)*.75f) - 80, pixelLocation.getY()));
        }

        //Y Stuff Below
        if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
            setPixelLocation(new Location(pixelLocation.getX(), Math.round(pHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight*1.2f))));
        }
        else if((float) (View.cameraLocation.getY())/StaticVar.terrainImageHeight > (mapYLength - StaticVar.yTilesFromEdge)) {
            setPixelLocation(new Location(pixelLocation.getX(), Math.round((pHandle.getLocation().getY() - ((mapYLength - StaticVar.yTilesFromEdge*2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight*1.2f))));
        }
        else {
            setPixelLocation(new Location(pixelLocation.getX(), pHandle.getLocation().getY() - View.cameraLocation.getY() + Math.round(StaticVar.yTilesFromEdge*StaticVar.terrainImageHeight) - StaticVar.terrainImageHeight - 20));
        }
        //TEST CODE, BUT FOLLOW THIS FORMAT FOR OVERRIDING PAINTCOMPONENT
        Graphics2D g2d = (Graphics2D)g.create();
        //gotta use that AA
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(Color.RED);

        Location l = LocationConverter.PixelLocationToHex(pHandle.getLocation());
        if(aHandle.canSeeHex(l))
        drawPet(g2d);

        g2d.dispose();

    }

    public void setPixelLocation(Location pixelLocation) {
        this.pixelLocation = pixelLocation;
    }
}
