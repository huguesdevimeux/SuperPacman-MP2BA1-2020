package ch.epfl.cs107.play.game.areagame.actor;


import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class CollectableAreaEntity extends AreaEntity {
    /**
     * Default AreaEntity constructor
     * @param area        (Area): Owner area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public CollectableAreaEntity(Area area, DiscreteCoordinates position) {
        super(area, Orientation.DOWN, position);
    }

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList((getCurrentMainCellCoordinates()));
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        v.interactWith(this);
    }
}
