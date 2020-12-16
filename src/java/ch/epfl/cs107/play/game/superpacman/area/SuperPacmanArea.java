package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics.GameOverGUI;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Jamila;
import ch.epfl.cs107.play.game.superpacman.actor.Portal;
import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.signal.logic.Not;
import ch.epfl.cs107.play.window.Window;

import java.util.List;
import java.util.Queue;

public abstract class SuperPacmanArea extends Area implements Logic {

    public abstract DiscreteCoordinates getSpawnLocation();
    public abstract DiscreteCoordinates getTeleportLocation();
    private GameOverGUI gameOverGUI = new GameOverGUI();
    protected abstract SuperPacmanBehavior getBehaviorTypeNewInstance(Window window);
    private SuperPacmanBehavior associatedBehavior;
    private AreaGraph associatedGraph;

    /*
    instantiating the number of diamonds at 0
    before launching the games nbDiamonds = 0
    however, when launching, the game will register the number of diamonds and will increment totalnbDiamonds by 1
    thus recording the number of diamonds per level
    */
    private int currentDiamonds = 0;
    private int originalNumberDiamonds;

    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            associatedBehavior = getBehaviorTypeNewInstance(window);
            setBehavior(associatedBehavior);
            associatedGraph = associatedBehavior.getGraph();
            this.createArea();
            return true;
        } else
            return false;
    }
    //this method will be called when we want the game to end
    public void endGame() {
        gameOverGUI.setGameIsOver();
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
    public void createGate(Orientation orientation, int x, int y, Logic signal) {
        registerActor(new Gate(this, orientation, new DiscreteCoordinates(x, y), signal));
        // We disable right nodes of the graph. We invert the signal, as ON state that the gate is OPEN.
        associatedGraph.setSignal(new DiscreteCoordinates(x, y), new Not(signal));
    }

    /*
    register jamila as actor
    side note : we must register Jamila manually as its celltype is the same as the coins
     */
    public void createJamila(int x, int y, Logic signal) {
        registerActor(new Jamila(this, Orientation.RIGHT, new DiscreteCoordinates(x, y), signal));
    }

    /*
    register portals as characters
    we must manually register portals in areas as they have no predefined cell types
     */
    public void createPortal(int x, int y) {
        registerActor(new Portal(this, new DiscreteCoordinates(x, y)));
    }

    /**
     * Get the path under the form of a queue of Orientation between point from and to, while excluding a set of point from being part of the path.
     */
    public Queue<Orientation> shortestPath(DiscreteCoordinates from, DiscreteCoordinates to,
                                           List<DiscreteCoordinates> toExclude) {
        return this.associatedGraph.shortestPath(from, to);
    }

    public void resetGhostSpeed() {
        associatedBehavior.resetGhostSpeed();
    }
    public void scareGhosts() {
        associatedBehavior.scareGhosts();
    }
    public void resetAllGhosts() {
        associatedBehavior.resetAllGhosts();
    }
    public void calmGhosts() {
        associatedBehavior.calmGhosts();
    }
    public void increaseCurrentDiamonds() {
        currentDiamonds++;
    }
    public void decreaseCurrentDiamonds() {
        currentDiamonds--;
    }
    public int getCurrentDiamonds() {
        return currentDiamonds;
    }
    public void setCurrentDiamonds(int number) {
        this.currentDiamonds = number;
        this.originalNumberDiamonds = number;
    }
    public int getOriginalNumberDiamonds() {
        return originalNumberDiamonds;
    }
}

