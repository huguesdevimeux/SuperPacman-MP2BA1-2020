package ch.epfl.cs107.play.game.areagame;

import java.util.List;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Image;
import ch.epfl.cs107.play.window.Window;


/**
 * AreaBehavior is a basically a map made of Cells. Those cells are used for the game behavior
 */

public abstract class AreaBehavior{

    /// The behavior is an Image of size height x width
    private final Image behaviorMap;
    private final int width, height;
    /// We will convert the image into an array of cells
    private final Cell[][] cells;

    /**
     * Default AreaBehavior Construcor
     * @param window (Window): graphic context, not null
     * @param name (String): name of the behavior image, not null
     */
    public AreaBehavior(Window window, String name){
        // Load the image
        behaviorMap = window.getImage(ResourcePath.getBehaviors(name), null, false);
        // Get the corresponding dimension and init the array
        height = behaviorMap.getHeight();
        width = behaviorMap.getWidth();
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
    
    protected int getHeight() {
    	return height;
    }
    
    protected int getWidth() {
    	return width;
    }
    
    protected void setCell(int x,int y, Cell cell) {
    	cells[x][y] = cell;
    }
    
    protected Cell getCell(int x, int y) {
    	return cells[x][y];
    }
}
