package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.CollectableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public class Bonus extends CollectableAreaEntity {
    Sprite[] sprites = RPGSprite.extractSprites("superpacman/coin", 4, 1.f, 1.f,
            this, 16, 16);
    //creating an animation for the coins
    Animation bonusAnimation = new Animation(4, sprites, true);

    /**
     * Default AreaEntity constructor
     *
     * @param area     (Area): Owner area. Not null
     * @param position (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Bonus(Area area, DiscreteCoordinates position) {
        super(area, position);
    }

    @Override
    public void draw(Canvas canvas) {
        bonusAnimation.draw(canvas);
    }

    //we have to repeatedly update the animation or it will stop
    public void update(float deltatime) {
        bonusAnimation.update(deltatime);
    }

    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor) v).interactWith(this);
    }
}
