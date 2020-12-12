package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public abstract class SuperPacmanArea extends Area {


    public abstract DiscreteCoordinates getSpawnLocation();

    protected abstract SuperPacmanBehavior getBehaviorTypeNewInstance(Window window); 

    private Logic collected;
    private SuperPacmanBehavior associatedBehavior;
    private AreaGraph associatedGraph;
    
    /*
        instantiating the number of diamonds at 0
        before launching the games nbDiamonds = 0
        however, when launching, the game will register the number of diamonds and will increment totalnbDiamonds by 1
        thus recording the number of diamonds per level
         */
    private static int totalNbDiamonds = 0;

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            associatedBehavior = getBehaviorTypeNewInstance(window);
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
     * Create the area by adding all actors called by begin method
     * Note it set the Behavior as needed !
     */
    protected void createArea() {
        associatedBehavior.registerActors(this);
    }

    /**
     * Get the path under the form of a queue of Orientation between point from and to.
     */
    public Queue<Orientation> shortestPath(DiscreteCoordinates from, DiscreteCoordinates to) {
        return this.associatedGraph.shortestPath(from, to);
    }

    //method to create gates depending on certain coordinates x and y and on the orientation
    //check the handout to verify the the coordinates of the gates on each level - page 20
    public void createGates(Orientation orientation, int x, int y, Logic signal) {
        registerActor(new Gate((this), orientation, new DiscreteCoordinates(x, y), signal));
    }

    /**
     * Get the path under the form of a queue of Orientation between point from and to, while excluding a set of point from being part of the path.
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

    public void increaseTotalNbDiamonds(){
        totalNbDiamonds++;
    }
    public void decreaseTotalNbDiamonds(){
        totalNbDiamonds--;
    }
    public int reset(){
        return totalNbDiamonds = 0;
    }

    public boolean totalNbDiamondsIsNull(){
        return totalNbDiamonds == 0;
    }
}

