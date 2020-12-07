package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area {


    public abstract DiscreteCoordinates getSpawnLocation();

    protected abstract SuperPacmanBehavior getBehaviorTypeNewInstance(Window window); 

    private SuperPacmanBehavior associatedBehavior;
    /**
     * Configure the walls of the corresponding behavior. 
     * Might be overriden to do stuff to walls. 
     * 
     * @param area
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
            associatedBehavior = getBehaviorTypeNewInstance(window);
            setBehavior(associatedBehavior);
            this.createArea();
            return true;
        } else return false;
    }
    
}
