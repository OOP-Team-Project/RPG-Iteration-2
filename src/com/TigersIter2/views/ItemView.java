package com.TigersIter2.views;

import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.assets.sprites.ItemSprite;
import com.TigersIter2.entities.Avatar;
import com.TigersIter2.items.Interactive;
import com.TigersIter2.items.Item;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.maps.TerrainMap;
import java.awt.*;

public class ItemView extends View {

    Avatar aHandle;
    Item iHandle;
    int mapXLength, mapYLength = 0;

    public ItemView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public ItemView(Item i, Avatar a, TerrainMap map) {
        aHandle = a;
        iHandle = i;
        mapXLength = map.getMapWidth();
        mapYLength = map.getMapHeight();

        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    private void drawItem(Graphics2D g2d){
        if(iHandle.getItemType() == 0) {
            g2d.drawImage(ItemSprite.armor, iHandle.getPixelLocation().getX(), iHandle.getPixelLocation().getY(), null);
        }
        else if(iHandle.getItemType() == 1){

            if(((Interactive) iHandle).getInteractedWith())
                g2d.drawImage(ItemSprite.interactive2, iHandle.getPixelLocation().getX(), iHandle.getPixelLocation().getY(), null);
            else
                g2d.drawImage(ItemSprite.interactive1, iHandle.getPixelLocation().getX(), iHandle.getPixelLocation().getY(), null);
        }
        else if(iHandle.getItemType() == 2){
            g2d.drawImage(ItemSprite.key, iHandle.getPixelLocation().getX(), iHandle.getPixelLocation().getY(), null);
        }
        else if(iHandle.getItemType() == 3){
            g2d.drawImage(ItemSprite.obstacle, iHandle.getPixelLocation().getX(), iHandle.getPixelLocation().getY(), null);
        }
        else if(iHandle.getItemType() == 4){
            g2d.drawImage(ItemSprite.oneShot, iHandle.getPixelLocation().getX(), iHandle.getPixelLocation().getY(), null);
        }
        else if(iHandle.getItemType() == 5){
            g2d.drawImage(ItemSprite.potion, iHandle.getPixelLocation().getX(), iHandle.getPixelLocation().getY(), null);
        }
        else if(iHandle.getItemType() == 6){
            g2d.drawImage(ItemSprite.weapon, iHandle.getPixelLocation().getX(), iHandle.getPixelLocation().getY(), null);
        }
    }

    public void paintComponent(Graphics g){
        if(iHandle.getDisplay()) {

            switch (View.currentMapMode) {
                case PLAYER_FOLLOW_MODE:

                    if ((float) (aHandle.getLocation().getX()) / StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                        iHandle.setPixelLocation(new Location(Math.round(iHandle.getLocation().getX() * .75f - 80), iHandle.getPixelLocation().getY(), 0));

                    } else if ((float) (aHandle.getLocation().getX()) / StaticVar.terrainImageWidth > (mapXLength - StaticVar.xTilesFromEdge + 1)) {
                        iHandle.setPixelLocation(new Location(Math.round((iHandle.getLocation().getX() - ((mapXLength - StaticVar.xTilesFromEdge * 2 + 1) * StaticVar.terrainImageWidth)) * .75f - 80), iHandle.getPixelLocation().getY(), 0));
                    } else {
                        iHandle.setPixelLocation(new Location(Math.round((iHandle.getLocation().getX() - aHandle.getLocation().getX() + StaticVar.xTilesFromEdge * StaticVar.terrainImageWidth) * .75f) - 80, iHandle.getPixelLocation().getY(), 0));

                    }

                    //Y Stuff Below
                    if ((float) (aHandle.getLocation().getY()) / StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                        iHandle.setPixelLocation(new Location(iHandle.getPixelLocation().getX(), Math.round(iHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight * 1.2f)), 0));
                    } else if ((float) (aHandle.getLocation().getY()) / StaticVar.terrainImageHeight > (mapYLength - StaticVar.yTilesFromEdge)) {
                        iHandle.setPixelLocation(new Location(iHandle.getPixelLocation().getX(), Math.round((iHandle.getLocation().getY() - ((mapYLength - StaticVar.yTilesFromEdge * 2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight * 1.2f)), 0));
                    } else {
                        iHandle.setPixelLocation(new Location(iHandle.getPixelLocation().getX(), iHandle.getLocation().getY() - aHandle.getLocation().getY() + Math.round(StaticVar.yTilesFromEdge * StaticVar.terrainImageHeight) - StaticVar.terrainImageHeight - 20, 0));
                    }
                    break;

                case SCROLL_MODE:
                    if ((float) (View.cameraLocation.getX()) / StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                        iHandle.setPixelLocation(new Location(Math.round(iHandle.getLocation().getX() * .75f - 80), iHandle.getPixelLocation().getY(), 0));
                    } else if ((float) (View.cameraLocation.getX()) / StaticVar.terrainImageWidth > (mapXLength - StaticVar.xTilesFromEdge + 1)) {
                        iHandle.setPixelLocation(new Location(Math.round((iHandle.getLocation().getX() - ((mapXLength - StaticVar.xTilesFromEdge * 2 + 1) * StaticVar.terrainImageWidth)) * .75f - 80), iHandle.getPixelLocation().getY(), 0));
                    } else {
                        iHandle.setPixelLocation(new Location(Math.round((iHandle.getLocation().getX() - View.cameraLocation.getX() + StaticVar.xTilesFromEdge * StaticVar.terrainImageWidth) * .75f) - 80, iHandle.getPixelLocation().getY(), 0));

                    }

                    //Y Stuff Below
                    if ((float) (View.cameraLocation.getY()) / StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                        iHandle.setPixelLocation(new Location(iHandle.getPixelLocation().getX(), Math.round(iHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight * 1.2f)), 0));
                    } else if ((float) (View.cameraLocation.getY()) / StaticVar.terrainImageHeight > (mapYLength - StaticVar.yTilesFromEdge)) {
                        iHandle.setPixelLocation(new Location(iHandle.getPixelLocation().getX(), Math.round((iHandle.getLocation().getY() - ((mapYLength - StaticVar.yTilesFromEdge * 2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight * 1.2f)), 0));
                    } else {
                        iHandle.setPixelLocation(new Location(iHandle.getPixelLocation().getX(), iHandle.getLocation().getY() - View.cameraLocation.getY() + Math.round(StaticVar.yTilesFromEdge * StaticVar.terrainImageHeight) - StaticVar.terrainImageHeight - 20, 0));
                    }

                default:
                    break;
            }

            Graphics2D g2d = (Graphics2D) g.create();
            //gotta use that AA
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            Location l = LocationConverter.PixelLocationToHex(iHandle.getLocation());
            if (aHandle.canSeeHex(l)) {
                drawItem(g2d);
            }

            g2d.dispose();
        }
    }

}
