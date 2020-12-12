package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends CollectableAreaEntity implements Logic {
    private Sprite key;
    public boolean isCollected;

    /**
     * Default Key constructor:
     * @param area     (Area): Owner area
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Key(Area area, DiscreteCoordinates position) {
        super(area, position);
        key = new Sprite("superpacman/key", 1.f, 1.f, this);
        //boolean isCollected is false by default as the key is not collected when the player starts playing
        //or gets to another level
        isCollected = false;
    }

    public void draw(Canvas canvas) {
        key.draw(canvas);
    }

    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor) v).interactWith(this);
    }

    /**
    @return true if the key is not collected
   */
    @Override
    public boolean isOn() {
        return !isCollected;
    }

    @Override
    public boolean isOff() {
        return isCollected;
    }

    //if the signal is on, it will return an intensity (not null)
    @Override
    public float getIntensity() {
        if (isOn()) return 1;
        else return 0;
    }

    /*
    this method will be called when the player interacts with a key
    changes the value of isCollected to true -- thus turning the signal off
     */
    public void isCollected() {
        isCollected = true;
    }
}

