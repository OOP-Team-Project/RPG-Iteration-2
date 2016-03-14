package com.TigersIter2.entities;

import com.TigersIter2.assets.FileReader;
import com.TigersIter2.assets.FileWriter;
import com.TigersIter2.assets.StaticVar;
import com.TigersIter2.items.Armor;
import com.TigersIter2.items.Key;
import com.TigersIter2.items.Potion;
import com.TigersIter2.items.TakeableItem;
import com.TigersIter2.location.Location;
import com.TigersIter2.location.LocationConverter;
import com.TigersIter2.skills.SkillTree;
import com.TigersIter2.stats.PlayerStats;

import java.util.HashSet;


public class Avatar extends Entity{

    private Location location;  //This is the location used by MODELS to determine where the avatar is
    private Inventory inventory = new Inventory();
    private Equipment equipment = new Equipment();
    private Occupation occupation;
    private int occ; //for saving purposes
    private Pet pet;
    private Vehicle vehicle;
    private PlayerStats stats;
    private SkillTree skills;

    private HashSet<Location> exploredTiles = new HashSet<Location>();

    private int direction;
    private boolean canPassWater;
    private boolean canPassMountain;
    private int timeUntilAttack = 0;

    private boolean currentlyMoving = false;
    private boolean onTileWithNPC = false;
    private boolean trading = false;
    private boolean isTrapped = false;


    public Avatar(){

        //loads in everything besides equipment and

        loadAvatar();

    }



    //What is this supposed to do? -Sam
    public void setLocation(Location l) {
        location = l;
    }

    //Should be named updatePosition -Sam
    @Override
    public void update(int xMovement, int yMovement, long elapsed) {
        if(xMovement == 0 && yMovement == 0){
            currentlyMoving = false;
        }
        else{
            location.incrementX(Math.round(xMovement * elapsed * StaticVar.entitySpeed * stats.getMovement()));   //Made it invariant of framerate
            location.incrementY(Math.round(yMovement * elapsed * StaticVar.entitySpeed * stats.getMovement()));
            changeDirection(xMovement, yMovement);
            currentlyMoving = true;
        }

        int viewDistance = stats.getLightRadius()*2 + 1;
        for (int i = 0; i < viewDistance; ++i) {
            for (int j = 0; j < viewDistance; ++j) {
                Location l = LocationConverter.PixelLocationToHex(location);
                l.incrementX(i - viewDistance / 2);
                l.incrementY(j - viewDistance / 2);
                if (canSeeHex(l)) exploredTiles.add(l);
            }
        }

        if(vehicle != null){
            vehicle.update(xMovement * stats.getMovement(), yMovement * stats.getMovement(), elapsed);
        }
    }

    public Inventory getInventory(){
        return inventory;
    }

    public Equipment getEquipment(){
        return equipment;
    }
    
    public PlayerStats getStats(){
        return stats;
    }

    public void equipItemAtIndex(int i){
        if(inventory.getItemAtIndex(i).isEquippable())
            equipment.addItem(inventory.removeItemAtIndex(i));
        else
            System.out.println("That item isn't equippable!");
    }

    public void unequipItemAtIndex(int i){
        if(!equipment.isEmpty())
            inventory.addItem(equipment.removeItemAtIndex(i));
        else
            System.out.println("There is nothing to unequip!");
    }

    public void dropItem(TakeableItem item){
        inventory.getItems().remove(item);
        int xLoc = location.getX();
        int yLoc = location.getY()+100;

        item.setLocation(new Location(xLoc, yLoc, 0));
        item.setDisplay(true);
    }

    public void mountOrUnmountVehicle(Vehicle v){
        if(vehicle == null) {
            vehicle = v;
            canPassWater = v.getCanPassWater();
            canPassMountain = v.getCanPassMountain();
            stats.addStatModifier(v.getStatsModifier());
        }
        else{
            vehicle = null;
            canPassMountain = false;
            canPassWater = false;
            stats.removeStatModifier(v.getStatsModifier());
        }
    }

    public Vehicle getVehicle(){
        return vehicle;
    }

    public void setPet(Pet p){
        pet = p;
    }

    public Pet getPet(){
        return pet;
    }

    public int getDirection(){
        return direction;
    }

    public boolean getCanPassWater(){
        return canPassWater;
    }

    public boolean getCanPassMountain(){
        return canPassMountain;
    }

    public Location getLocation() {
        return location;
    }

    public void saveAvatar(){
        StringBuilder saveString = new StringBuilder();
        //saves occupation
        saveString.append("Occupation:\n" + occ + "\n");
        //saves location
        saveString.append("location:\n"+location.getX()+" "+location.getY()+"\n");
        //saves direction
        saveString.append("direction:\n"+direction+"\n");
        //saves water ability
        saveString.append("water:\n" + canPassWater+ "\n");
        //saves mountain ability
        saveString.append("mountain:\n"+ canPassMountain + "\n");

        FileWriter.stringToFile(StaticVar.avatarFile, saveString.toString());

        //needs to save location 1050,960
        //needs to save pixelLocation 629,269
        //direction 279
        //can pass mountain, water false false

    }

    private void loadAvatar(){
        System.out.println("Avatar loading in....");
        String[] avatarString = FileReader.fileToString(StaticVar.avatarFile).split("\\s+"); //splits up file on any white space
        occ = 0;
        if (avatarString[0].equals("Occupation:")) {
            occ = FileReader.stringToInt(avatarString[1]); //first number of file, after occupation
            System.out.println("Occupation set");
        }
        System.out.println("first number of avatar file: " + occ);
        switch(occ){
            case 1: occupation = new Smasher();
                System.out.println("Smasher Set");
                break;
            case 2: occupation = new Summoner();
                System.out.println("Summoner Set");
                break;
            case 3: occupation = new Sneak();
                System.out.println("Sneak Set");
                break;
            default: occupation = new Smasher();
                System.out.println("Error loading occupation");
                break;
        }
        //occupation = o;
        stats = new PlayerStats(occupation);
        skills = new SkillTree(stats);
        //set avatar location
        location = new Location(0,0,0);
        if (avatarString[2].equals("location:")){
            location.setX(FileReader.stringToInt(avatarString[3]));
            location.setY(FileReader.stringToInt(avatarString[4]));
            System.out.println("location Set");
        }
        else System.out.println("Error loading location of avatar");
        if (avatarString[5].equals("direction:")){
            direction = (FileReader.stringToInt(avatarString[6]));
            System.out.println("direction Set");
        }
        else System.out.println("Error loading direction of avatar");
        //canpassgrass is not a variable and is always true
        //set avatar canpasswater
        if (avatarString[7].equals("water:")){
            if (avatarString[8].equals("true")) {
                canPassWater = true;
                System.out.println("canPassWater Set");
            }
            else canPassWater = false;
        }
        else System.out.println("Error loading canPassWater of avatar");
        if (avatarString[9].equals("mountain:")){
            if (avatarString[10].equals("true")) {
                canPassMountain = true;
                System.out.println("canPassMountain Set");
            }
            else canPassMountain = false;
        }
        else System.out.println("Error loading canPassMountain of avatar");
    }

    public Occupation getOccupation(){
        return occupation;
    }

    public SkillTree getSkills(){
        return skills;
    }

    private void changeDirection(int x, int y){
        if(x == 0){
            if(y > 0)
                direction = 270;
            else if(y < 0)
                direction = 90;
        }
        else if(x > 0){
            if(y > 0)
                direction = 315;
            else if(y < 0)
                direction = 45;
        }
        else{
            if(y > 0)
                direction = 225;
            else if(y < 0)
                direction = 135;
        }
    }

    public boolean isCurrentlyMoving() {
        return currentlyMoving;
    }

    public void setOnTileWithNPC(boolean b){
        onTileWithNPC = b;
    }

    public boolean getOnTileWithNPC(){
        return onTileWithNPC;
    }

    public void setTrading(boolean t){
        trading = t;
    }

    public boolean getTrading(){
        return trading;
    }

    public void setIsTrapped(boolean t){
        isTrapped = t;
    }

    public boolean getIsTrapped(){
        return isTrapped;
    }

    public int getTimeUntilAttack(){
        return timeUntilAttack;
    }

    public void decrementTimeUntilAttack(){
        if(timeUntilAttack > 0)
            --timeUntilAttack;
    }

    public void resetTimeUntilAttack(){
        timeUntilAttack = stats.getAttackTime();
    }

    public void takeDamage(int attackStrength){
        //calculate some sort of damage
        int damageTaken = attackStrength;
        stats.decreaseCurrentLife(damageTaken);
        System.out.println("Taking damage");
    }

    //added this getter method to get the player stats for item manager -- Breanna
    public PlayerStats getPlayerStats() { return stats; }

    public String getWeaponType(){
        String ret = equipment.getWeaponType();
        if(ret.equals("none")){
            if(occupation.toString().equals("Summoner"))
                ret = "Staff";
            else if(occupation.toString().equals("Smasher"))
                ret = "Brawling";
        }
        return ret;
    }


    public boolean hasExploredTile(int i, int j) {
        return exploredTiles.contains(new Location(i,j,-i-j));
    }

    public boolean canSeeHex(Location l) {
        Location a = LocationConverter.PixelLocationToHex(getLocation());
        int distance = a.getDistance(l);
        int visionDistance = getStats().getLightRadius();
        return distance <= visionDistance;
    }
}