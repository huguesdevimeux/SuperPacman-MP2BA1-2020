package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Jamila extends AreaEntity {
    private Sprite Jamila;
    private Logic signal;
    /**
     * Default Jamila constructor
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     * side note : we are using the ressource bonus but in no case are we using it in its intended use
     * we are "customising" its actual use
     */
    public Jamila(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal) {
        super(area, orientation, position);
        this.signal = signal;
        Jamila = new Sprite("superpacman/bonus", 1.f, 1.f, this);
    }
    public void draw(Canvas canvas){
        Jamila.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList((getCurrentMainCellCoordinates()));
    }

    /*the player won't be able to "eat" Jamila if the signal is on
    BE careful and strategic - don't get stuck as Jamilas will block access if the signal is on
     */
    @Override
    public boolean takeCellSpace() {
        return signal.isOn();
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
       ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
}
