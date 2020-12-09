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
import ch.epfl.cs107.play.game.rpg.actor.Sign;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics.SuperPacmanPlayerStatusGUI;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;


import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuperPacmanPlayer extends Player {
    private Area currentArea;
    private int movingSpeed;
    private SuperPacmanPlayerHandler handler = new SuperPacmanPlayerHandler();
    private SuperPacmanPlayerStatusGUI statusDrawer;
    private int score = 0;

    private Animation[] movingAnimations;
    private Sprite[][] sprites;
    private Orientation desiredOrientation;
    private List<DiscreteCoordinates> targetCell;

    public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
        super(area, orientation, coordinates);
        this.statusDrawer = new SuperPacmanPlayerStatusGUI(this);

        movingSpeed = 6;
        this.desiredOrientation = orientation;
        sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this, 64, 64,
                new Orientation[]{Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT});
        movingAnimations = Animation.createAnimations(movingSpeed / 2, sprites);
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
        } else movingAnimations[getOrientation().ordinal()].update(deltaTime);
        super.update(deltaTime);
    }

    /**
     * Set the desired orientation if the corresponding key is pressed, and if the orientation needs to be updated.
     *
     * @param requestedOrientation
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


        //when the player will interact with the key, the actor key will disappear
        public void interactWith(Key key) {
            getOwnerArea().unregisterActor(key);
        }

        public void interactWith(Bonus bonus) {
            getOwnerArea().unregisterActor(bonus);
        }
        //"eating" a diamond will increment the score by 10
        public void interactWith(Diamond diamond) {
            score += 10;
            getOwnerArea().unregisterActor(diamond);
        }
        //"eating" a cherry will increment the score by 200
        public void interactWith(Cherry cherry) {
            score += 200;
            getOwnerArea().unregisterActor(cherry);
        }
        //TODO -- why does compiler say interaction not implemented when this
        //method is not implemented
        public void interactWith(Interactable other) {}
        public void interactWith(Ghost ghost) {
            System.out.println("Getting bullied by a fuvking ghost!");
        }
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor) v).interactWith(this);
    }

    @Override
    public void draw(Canvas canvas) {
        statusDrawer.setScore(score);
        statusDrawer.setAmountLife(3);
        statusDrawer.draw(canvas);
        movingAnimations[getOrientation().ordinal()].draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        // Player has no fov. WARNING !
        return new ArrayList<DiscreteCoordinates>();
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
        return true;
    }

    @Override
    public boolean isViewInteractable() {
        return true;
    }

}