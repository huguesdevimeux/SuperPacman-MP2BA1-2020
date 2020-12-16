package ch.epfl.cs107.play.game.areagame;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;

import java.util.List;


/**
 * AreaBehavior is a basically a map made of Cells. Those cells are used for the game behavior
 */

public abstract class AreaBehavior{

    /// The behavior is an Image of size height x width
    // NOTE : (modified by students) this is protected to allow a an overriden by a custon version of behavior.
    protected final Image behaviorMap;
    protected final int width, height;
    /// We will convert the image into an array of cells
    protected final Cell[][] cells;

    /**
     * Default AreaBehavior Construcor
     * @param window (Window): graphic context, not null
     * @param name (String): name of the behavior image, not null
     */
    public AreaBehavior(Window window, String name) {
        // Load the image
        behaviorMap = window.getImage(ResourcePath.getBehaviors(name), null, false);
        // Get the corresponding dimension and init the array
        height = behaviorMap.getHeight();
        width = behaviorMap.getWidth();
        cells = new Cell[width][height];
    }
    
    /**
     * [CREATED BY STUDENTS]
     * Constructor to handle the case when no image behavior is provided (eg when rendering non-
     * deterministic behavior). 
     * @param window (Window): graphic context, not null
     * @param height height of the desired behavior 
     * @param width width of the desired bahaviormap
     */
    public AreaBehavior(Window window, int height, int width){
        // We set the behaviorMap to null, as we won't need it.
        behaviorMap = null; 
        // Get the corresponding dimension and init the array
        this.height = height;
        this.width = width;
        cells = new Cell[width][height];
    }

    protected void cellInteractionOf(Interactor interactor){
        for(DiscreteCoordinates dc : interactor.getCurrentCells()){
            if(dc.x < 0 || dc.y < 0 || dc.x >= width || dc.y >= height)
                continue;
            cells[dc.x][dc.y].cellInteractionOf(interactor);
        }
    }

    protected void viewInteractionOf(Interactor interactor){
        for(DiscreteCoordinates dc : interactor.getFieldOfViewCells()){
            if(dc.x < 0 || dc.y < 0 || dc.x >= width || dc.y >= height)
                continue;
            cells[dc.x][dc.y].viewInteractionOf(interactor);
        }
    }

    protected boolean canLeave(Interactable entity, List<DiscreteCoordinates> coordinates) {

        for(DiscreteCoordinates c : coordinates){
            if(c.x < 0 || c.y < 0 || c.x >= width || c.y >= height)
                return false;
            if(!cells[c.x][c.y].canLeave(entity))
                return false;
        }
        return true;
    }

    protected boolean canEnter(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for(DiscreteCoordinates c : coordinates){
            if(c.x < 0 || c.y < 0 || c.x >= width || c.y >= height)
                return false;
            if(!cells[c.x][c.y].canEnter(entity))
                return false;
        }
        return true;
    }

    protected void leave(Interactable entity, List<DiscreteCoordinates> coordinates) {

        for(DiscreteCoordinates c : coordinates){
            cells[c.x][c.y].leave(entity);
        }

    }

    protected void enter(Interactable entity, List<DiscreteCoordinates> coordinates) {
        for(DiscreteCoordinates c : coordinates){
            cells[c.x][c.y].enter(entity);
        }
    }


    protected int getRGB(int r, int c) {
    	return behaviorMap.getRGB(r, c);
    }
    
    public int getHeight() {
    	return height;
    }
    
    public int getWidth() {
    	return width;
    }
    
    protected void setCell(int x,int y, Cell cell) {
    	cells[x][y] = cell;
    }
    
    protected Cell getCell(int x, int y) {
    	return cells[x][y];
    }
}
