package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

/*
this class will register a fire, once the player eats a strawberry and/or a Jamila
the player and the ghosts both cannot go through it
as such, it is both an obstacle and an advantage as the player can't get past it (so it can't get across the map as easily) but it
also blocks the ghost, leaving the player some time to collect coins and diamonds and other collectables
 */
public class Enclosure extends AreaEntity {
    private Sprite fire;

    /**
     * Default fire constructor
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Enclosure(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        fire = new Sprite("superpacman/prison", 1.f, 1.f, this);
    }
    @Override
    public void draw(Canvas canvas) {
        fire.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList((getCurrentMainCellCoordinates()));
    }

    @Override
    public boolean takeCellSpace() {
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
      //AUTO GENERATED METHOD
    }
}
