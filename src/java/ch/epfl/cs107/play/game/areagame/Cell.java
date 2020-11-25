package ch.epfl.cs107.play.game.areagame;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Each AreaGame will have its own Cell extension.
 * At minimum a cell is linked to its content
 */
public abstract class Cell implements Interactable{

    /// Content of the cell as a set of Interactable
    private Set<Interactable> entities;
    private DiscreteCoordinates coordinates;


    /**
     * Default Cell constructor
     * @param x (int): x-coordinate of this cell
     * @param y (int): y-coordinate of this cell
     */
    public Cell(int x, int y){
        entities = new HashSet<>();
        coordinates = new DiscreteCoordinates(x, y);
    }
    
    /**
     * Do the given interactor interacts with all Interactable sharing the same cell
     * @param interactor (Interactor), not null
     */
    public void cellInteractionOf(Interactor interactor){
        interactor.interactWith(this);
        for(Interactable interactable : entities){
            if(interactable.isCellInteractable())
                interactor.interactWith(interactable);
        }
    }

    /**
     * Do the given interactor interacts with all Interactable sharing the same cell
     * @param interactor (Interactor), not null
     */
    public void viewInteractionOf(Interactor interactor){
        interactor.interactWith(this);
        for(Interactable interactable : entities){
            if(interactable.isViewInteractable())
                interactor.interactWith(interactable);
        }
    }

    /**
     * Do the given interactable enter into this Cell
     * @param entity (Interactable), not null
     */
    protected void enter(Interactable entity) {
        entities.add(entity);
    }

    /**
     * Do the given Interactable leave this Cell
     * @param entity (Interactable), not null
     */
    protected void leave(Interactable entity) {
        entities.remove(entity);
    }

    /**
     * Indicate if the given Interactable can leave this Cell
     * @param entity (Interactable), not null
     * @return (boolean): true if entity can leave
     */
    protected abstract boolean canLeave(Interactable entity);

    /**
     * Indicate if the given Interactable can enter this Cell
     * @param entity (Interactable), not null
     * @return (boolean): true if entity can enter
     */
    protected abstract boolean canEnter(Interactable entity);

    /// Cell implements Interactable

    @Override
    public boolean takeCellSpace(){
        return false;
    }
    
    protected boolean hasNonTraversableContent() {
		for (Interactable entity : entities) {
            if (entity.takeCellSpace())
                return true;
        }
		return false;
	}
    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(coordinates);
    }
}