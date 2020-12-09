package ch.epfl.cs107.play.game.superpacman.area;

import java.util.List;
import java.util.Queue;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area {

    private SuperPacmanBehavior associatedBehavior;
    private AreaGraph associatedGraph;
    
    public abstract DiscreteCoordinates getSpawnLocation();

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            associatedBehavior = new SuperPacmanBehavior(window, getTitle());
            setBehavior(associatedBehavior);
            this.createArea();
            associatedGraph = associatedBehavior.getGraph();
            return true;
        } else
            return false;
    }

    public final float getCameraScaleFactor() {
        return SuperPacman.CAMERA_SCALE_FACTOR;
    }
    
    /**
     * Create the area by adding it all actors called by begin method Note it set
     * the Behavior as needed !
     */
    protected void createArea() {
        associatedBehavior.registerActors(this);
    }
    
    /**
     * Get the path under the form of a queue of Orientation between point from and to.  
     * @param from
     * @param to
     * @return
     */
    public Queue<Orientation> shortestPath(DiscreteCoordinates from, DiscreteCoordinates to) {
        return this.associatedGraph.shortestPath(from, to);
    }
    
    
    /**
     * Get the path under the form of a queue of Orientation between point from and to, while excluding a set of point from being part of the path.  
     * @param from
     * @param to
     * @return
     */
    public Queue<Orientation> shortestPath(DiscreteCoordinates from, DiscreteCoordinates to,
            List<DiscreteCoordinates> toExclude) {
        return this.associatedGraph.shortestPath(from, to);
    }
    
    public void scareGhosts() {
        associatedBehavior.scareGhosts();
    }

    public void calmGhosts() {
        associatedBehavior.calmGhosts();
    }
}
