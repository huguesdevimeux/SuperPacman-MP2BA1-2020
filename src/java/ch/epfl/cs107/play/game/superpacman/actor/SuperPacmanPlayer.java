package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior.SuperPacmanCell;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class SuperPacmanPlayer extends Player {
    private Area currentArea;
    private int movingSpeed;

    private SuperPacmanPlayerHandler handler = new SuperPacmanPlayerHandler();

    private Animation[] movingAnimations;
    private Sprite[][] sprites;
    private Orientation desiredOrientation;
    private List<DiscreteCoordinates> targetCell;
  
    public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
        super(area, orientation, coordinates);
        movingSpeed = 6;
        this.desiredOrientation = orientation;
        sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this, 64, 64, 
                new Orientation[] { Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT });
        movingAnimations = Animation.createAnimations(movingSpeed/2, sprites); 
        this.currentArea = area;
        resetMotion();
    }

    @Override
    public void update(float deltaTime) {
        Keyboard keyboard = getOwnerArea().getKeyboard();

        updateDesiredOrientation(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        updateDesiredOrientation(Orientation.UP, keyboard.get(Keyboard.UP));
        updateDesiredOrientation(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        updateDesiredOrientation(Orientation.DOWN, keyboard.get(Keyboard.DOWN));

        // NOTE : As the name DOES NOT suggesti, isDisplacementOccurs return whether the player is in displacement between 
        // two cells, not in displacement between point A to point B!
        if (!isDisplacementOccurs()) {
            movingAnimations[getOrientation().ordinal()].reset();
            targetCell = Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector()));
            if (this.getOwnerArea().canEnterAreaCells(this, targetCell)) {
                orientate(desiredOrientation);
            }
            // move is only called when pacman is not moving in deplacement.
            move(movingSpeed);
        }
        else movingAnimations[getOrientation().ordinal()].update(deltaTime);
        super.update(deltaTime);
    }

    /**
     * Set the desired orientation if the corresponding key is pressed, and if the orientation needs to be updated. 
     * @param desiredOrientation
     * @param key
     */
    public void updateDesiredOrientation(Orientation requestedOrientation, Button key) {
        if (key.isPressed() && requestedOrientation != getOrientation()) {
            this.desiredOrientation = requestedOrientation;
        }
    }
    private class SuperPacmanPlayerHandler implements SuperPacmanInteractionVisitor {
        public void interactWith(Door door) {
            setIsPassingADoor(door);
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }

    @Override
    public void draw(Canvas canvas) {
        movingAnimations[getOrientation().ordinal()].draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        return true;
    }

    @Override
    public boolean wantsViewInteraction() {
        return false;
    }
   @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        return false;
    }

    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }
}