package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area {
    private Logic collected;
    private SuperPacmanBehavior associatedBehavior;
    /**
     * Configure the walls of the corresponding behavior. 
     * Might be overriden to do stuff to walls. 
     *
     */

    public final float getCameraScaleFactor() {
        return SuperPacman.CAMERA_SCALE_FACTOR;
    }
    /**
     * Create the area by adding it all actors
     * called by begin method
     * Note it set the Behavior as needed !
     */
    protected void createArea(){
        associatedBehavior.registerActors(this);
    }

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            associatedBehavior = new SuperPacmanBehavior(window, getTitle());
            setBehavior(associatedBehavior);
            this.createArea();
            return true;
        } else return false;
    }
    
    public abstract DiscreteCoordinates getSpawnLocation();

    //method to create gates depending on certain coordinates x and y and on the orientation
    //check the handout to verify the the coordinates of the gates on each level - page 20
    public void createGates(Orientation orientation, int x, int y) {
        registerActor(new Gate((this), orientation, new DiscreteCoordinates(x,y), collected));
    }

    //same method as createGates but for keys instead of gates
    public void createKeys(int x, int y){
        registerActor(new Key(this, new DiscreteCoordinates(x,y)));
    }
}

