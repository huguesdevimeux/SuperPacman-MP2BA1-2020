package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

/*
this class' purpose is uniquely to be a signal for Jamila
it's pretty cool having strawberries being signals in certain levels
 */
public class Strawberry extends CollectableAreaEntity implements Logic {
    private Sprite strawberry;
    private boolean isCollected;
    /**
     * Default strawberry constructor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Strawberry(Area area, DiscreteCoordinates position) {
        super(area, position);
        strawberry = new Sprite("superpacman/strawberry", 1.f,1.f,this);
        isCollected = false;
    }

    public void draw(Canvas canvas){
        strawberry.draw(canvas);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }

    /**
     * @return true only if the strawberry is not collected
     */
    @Override
    public boolean isOn() {
        return !isCollected;
    }

    @Override
    public boolean isOff() {
        return isCollected;
    }

    @Override
    public float getIntensity() {
        return 0;
    }
    /*
   this method will be called when the player interacts with a strawberry
   changes the value of isCollected to true -- thus turning the signal off
    */
    public void setCollected(){
        isCollected = true;
    }
}
