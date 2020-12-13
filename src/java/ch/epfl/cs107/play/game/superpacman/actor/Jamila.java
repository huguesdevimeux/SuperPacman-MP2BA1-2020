package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Jamila extends CollectableAreaEntity {
    private Sprite Jamila;
    /**
     * Default Jamila constructor
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     * side note : we are using the ressource bonus but in no case are we using it in its intended use
     * we are "customising" its actual use
     */
    public Jamila(Area area, DiscreteCoordinates position) {
        super(area, position);
        Jamila = new Sprite("superpacman/bonus", 1.f, 1.f, this);
    }

    public void draw(Canvas canvas){
        Jamila.draw(canvas);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
       ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
}
