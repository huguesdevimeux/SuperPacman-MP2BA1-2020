package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior.SuperPacmanCell;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player {

    private Sprite sprite;
    private Area currentArea;
    private int movingSpeed;
    private Animation[] movingAnimations;
    private Sprite[][] sprites;

    public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
        super(area, orientation, coordinates);
        movingSpeed = 6;
        sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this, 64, 64, 
                new Orientation[] { Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT });
        movingAnimations = Animation.createAnimations(movingSpeed/2, sprites); 
        sprite = new Sprite("superpacman/bonus", 1.f, 1.f, this);
        this.currentArea = area;
    }
    
    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Keyboard keyboard = getOwnerArea().getKeyboard();
        moveOrientate(Orientation.LEFT, keyboard.get(Keyboard.LEFT), deltaTime);
        moveOrientate(Orientation.UP, keyboard.get(Keyboard.UP), deltaTime);
        moveOrientate(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT), deltaTime);
        moveOrientate(Orientation.DOWN, keyboard.get(Keyboard.DOWN), deltaTime);
        if (isDisplacementOccurs()) {
            movingAnimations[getOrientation().ordinal()].update(deltaTime);
        }
        else {
            movingAnimations[getOrientation().ordinal()].reset(); 
        }

    }
    
    private void moveOrientate(Orientation orientation, Button b, float deltaTime) {
        List<DiscreteCoordinates> targetCell = Collections
                .singletonList(getCurrentMainCellCoordinates().jump(orientation.toVector()));
        if (b.isDown()) {
            if (getOrientation() != orientation) {
                orientate(orientation);
            }
        }
        if (!(isDisplacementOccurs()) && currentArea.canEnterAreaCells(this, targetCell)) {
            move(movingSpeed);
        }
    }

    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public void draw(Canvas canvas) {
        movingAnimations[getOrientation().ordinal()].draw(canvas);
        // sprite.draw(canvas);
    }


    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        return null;
    }

    @Override
    public boolean wantsCellInteraction() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean wantsViewInteraction() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void interactWith(Interactable other) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean takeCellSpace() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCellInteractable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        // TODO Auto-generated method stub

    }



}
