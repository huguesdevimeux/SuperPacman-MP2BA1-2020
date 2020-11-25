package ch.epfl.cs107.play.game.areagame.actor;

import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.List;


/**
 * Represent Interactable object (i.e. Interactor can interact with it)
 * @see Interactor
 * This interface makes sense only in the "Area Context" with Actor contained into Area Cell
 */
public interface Interactable {

    /**
     * Get this Interactor's current occupying cells coordinates
     * @return (List of DiscreteCoordinates). May be empty but not null
     */
    List<DiscreteCoordinates> getCurrentCells();

    /**
     * Indicate if the current Interactable take the whole cell space or not
     * i.e. only one Interactable which takeCellSpace can be in a cell
     * (how many Interactable which don't takeCellSpace can also be in the same cell)
     * @return (boolean)
     */
    boolean takeCellSpace();


    /**@return (boolean): true if this is able to have cell interactions*/
    boolean isCellInteractable();

    /**@return (boolean): true if this is able to have view interactions*/
    boolean isViewInteractable();

    /** Call directly the interaction on this if accepted
     * @param v (AreaInteractionVisitor) : the visitor
     * */
    void acceptInteraction(AreaInteractionVisitor v);

}
