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
    Location pixelLocation;

    public ItemView(){
        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    public ItemView(Item i, Avatar a, TerrainMap map) {
        aHandle = a;
        iHandle = i;
        mapXLength = map.getMapWidth();
        mapYLength = map.getMapHeight();

        pixelLocation = new Location(0,0);

        setPreferredSize(new Dimension(StaticVar.gameWidth, StaticVar.gameHeight));
    }

    private void drawItem(Graphics2D g2d){
        if(iHandle.getItemType() == 0) {
            g2d.drawImage(ItemSprite.armor, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if(iHandle.getItemType() == 1){

            if(((Interactive) iHandle).getInteractedWith())
                g2d.drawImage(ItemSprite.interactive2, pixelLocation.getX(), pixelLocation.getY(), null);
            else
                g2d.drawImage(ItemSprite.interactive1, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if(iHandle.getItemType() == 2){
            g2d.drawImage(ItemSprite.key, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if(iHandle.getItemType() == 3){
            g2d.drawImage(ItemSprite.obstacle, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if(iHandle.getItemType() == 4){
            g2d.drawImage(ItemSprite.oneShot, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if(iHandle.getItemType() == 5){
            g2d.drawImage(ItemSprite.potion, pixelLocation.getX(), pixelLocation.getY(), null);
        }
        else if(iHandle.getItemType() == 6){
            g2d.drawImage(ItemSprite.weapon, pixelLocation.getX(), pixelLocation.getY(), null);
        }
    }

    public void paintComponent(Graphics g){
        if(iHandle.getDisplay()) {

            if ((float) (View.cameraLocation.getX()) / StaticVar.terrainImageWidth < ((float) StaticVar.xTilesFromEdge)) {
                setPixelLocation(new Location(Math.round(iHandle.getLocation().getX() * .75f - 80), pixelLocation.getY()));
            } else if ((float) (View.cameraLocation.getX()) / StaticVar.terrainImageWidth > (mapXLength - StaticVar.xTilesFromEdge + 1)) {
                setPixelLocation(new Location(Math.round((iHandle.getLocation().getX() - ((mapXLength - StaticVar.xTilesFromEdge * 2 + 1) * StaticVar.terrainImageWidth)) * .75f - 80), pixelLocation.getY()));
            } else {
                setPixelLocation(new Location(Math.round((iHandle.getLocation().getX() - View.cameraLocation.getX() + StaticVar.xTilesFromEdge * StaticVar.terrainImageWidth) * .75f) - 80, pixelLocation.getY()));
            }
            //Y Stuff Below
            if ((float) (View.cameraLocation.getY()) / StaticVar.terrainImageHeight < (float) StaticVar.yTilesFromEdge) {
                setPixelLocation(new Location(pixelLocation.getX(), Math.round(iHandle.getLocation().getY() - Math.round(StaticVar.terrainImageHeight * 1.2f))));
            } else if ((float) (View.cameraLocation.getY()) / StaticVar.terrainImageHeight > (mapYLength - StaticVar.yTilesFromEdge)) {
                setPixelLocation(new Location(pixelLocation.getX(), Math.round((iHandle.getLocation().getY() - ((mapYLength - StaticVar.yTilesFromEdge * 2) * StaticVar.terrainImageHeight)) - Math.round(StaticVar.terrainImageHeight * 1.2f))));
            } else {
                setPixelLocation(new Location(pixelLocation.getX(), iHandle.getLocation().getY() - View.cameraLocation.getY() + Math.round(StaticVar.yTilesFromEdge * StaticVar.terrainImageHeight) - StaticVar.terrainImageHeight - 20));
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

    public void setPixelLocation(Location pixelLocation) {
        this.pixelLocation = pixelLocation;
    }
}
