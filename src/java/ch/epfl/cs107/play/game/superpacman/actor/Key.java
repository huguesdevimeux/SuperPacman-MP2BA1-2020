package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Key extends CollectableAreaEntity implements Logic {
    private Sprite key;
    /**
     * Default AreaEntity constructor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Key(Area area, DiscreteCoordinates position) {
        super(area, position);
        key = new Sprite("superpacman/key", 1.f, 1.f, this);
    }

    public void draw(Canvas canvas) {
        if(isOn()) {
            key.draw(canvas);
        }
    }

    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor) v).interactWith(this);
    }

    //DONT MIND THE METHODS BELOW
    @Override
    public boolean isOn() {
        return true;
    }

    @Override
    public boolean isOff() {
        return false;
    }

    @Override
    public float getIntensity() {
        return 0;
    }

    @Override
    public float getIntensity(float t) {
        return 0;
    }
    public boolean collected(){
        return isOff();

        }
    }

