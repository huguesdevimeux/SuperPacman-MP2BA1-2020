package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Skeebo extends CollectableAreaEntity {
    private Sprite skeebo;
    /**
     * Default AreaEntity constructor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Skeebo(Area area, DiscreteCoordinates position) {
        super(area, position);
        skeebo = new Sprite("superpacman/bonus", 1.f, 1.f, this);
    }

    public void draw(Canvas canvas){
        skeebo.draw(canvas);
    }
    public void acceptInteraction(AreaInteractionVisitor v){
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
}
