package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area {

    private static SuperPacmanBehavior behavior;
    //creating a method that level0/1/2 will have in common
    //to configure walls as actors
    protected static void configureWalls(Area area){
        behavior.registerActors(area);
    }
    public final float getCameraScaleFactor() {
        return SuperPacman.CAMERA_SCALE_FACTOR;
    }
    /**
     * Create the area by adding it all actors
     * called by begin method
     * Note it set the Behavior as needed !
     */
    protected abstract void createArea();

    public boolean begin(Window window, FileSystem fileSystem){
        if(super.begin(window,fileSystem)){
            behavior = new SuperPacmanBehavior(window, getTitle());
            setBehavior(behavior);
            createArea();
            return true;
        }
        else return false;
    }
}
