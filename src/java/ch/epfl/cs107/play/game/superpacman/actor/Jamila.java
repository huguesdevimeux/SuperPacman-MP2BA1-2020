package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

//we hope the classe's name doesn't cause any offense
public class Jamila extends AreaEntity {
    private Logic signal;
    Sprite[] sprites = RPGSprite.extractSprites("superpacman/heart", 4, 1.f, 1.f,
            this, 16, 16);
    Animation Jamila = new Animation(2, sprites, true);

    /**
     * Default Jamila constructor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Jamila(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal) {
        super(area, orientation, position);
        this.signal = signal;
    }

    public void draw(Canvas canvas) {
        Jamila.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {
        if (signal.isOff()) Jamila.update(deltaTime);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList((getCurrentMainCellCoordinates()));
    }

    /*
    the player won't be able to "eat" Jamila if the signal is on
    BE careful and strategic - don't eat jamilas if you have full health
    and be aware that once you ingest either Jamila or a strawberry, you leave behind
    a fire
     */
    @Override
    public boolean takeCellSpace() {
        return false;
    }

    /*
    the interaction between the player and Jamila can only take place if
    the player has eaten the nearest strawberry (if he hasn't the signal will stay on
    */
    @Override
    public boolean isCellInteractable() {
        return signal.isOff();
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor) v).interactWith(this);
    }
}
