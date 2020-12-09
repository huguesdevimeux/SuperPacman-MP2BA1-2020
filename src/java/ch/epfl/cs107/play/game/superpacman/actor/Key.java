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
     * Default AreaEntity constructor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Key(Area area, DiscreteCoordinates position) {
        super(area, position);
        key = new Sprite("superpacman/key", 1.f, 1.f, this);
        isCollected = false;
    }

    public void draw(Canvas canvas) {
            key.draw(canvas);
    }

    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor) v).interactWith(this);
    }

    //DONT MIND THE METHODS BELOW
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
       if(isOn()) return 1;
       else return 0;
    }

    @Override
    public float getIntensity(float t) {
        return 0;
    }

    public void isCollected(){
        isCollected = true;
        }
    }

