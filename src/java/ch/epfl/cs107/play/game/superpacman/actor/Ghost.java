package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.*;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics.GameOverGUI;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics.PauseGUI;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Ghost extends MovableAreaEntity implements Interactor {

    private static final int LENGTH_FOV = 5;
    private GhostHandler handler = new GhostHandler();
    private DiscreteCoordinates refugePosition;
    public final int GHOST_SCORE = 500;
    protected int movingSpeed = 13;
    protected Sprite sprite;
    protected Animation[] normalStateAnimations;
    private Animation[] afraidAnimations;
    private Animation[] currentAnimations;
    private boolean isAfraid;
    private int afraidTime;

    public Ghost(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
        // The refuge is set to the inital position of the ghost.
        this.refugePosition = position;
        generateAfraidAnimations();
        generateNormalStateAnimation();

        // By default, The Ghost is not afraid.
        setNormalState();
    }

    protected abstract Orientation getNextOrientation();

    protected abstract String getTitle();

    protected abstract int getSpeed();

    private void generateNormalStateAnimation() {
        Sprite[][] sprites = RPGSprite.extractSprites(getTitle(), 2, 1, 1, this, 16, 16,
                new Orientation[]{Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT});
        this.normalStateAnimations = Animation.createAnimations(18 / 2, sprites);
    }

    //once the player eats a coin, the ghost's speed will increase (not temporarily)
    //and will reset once the player eats a Jamila
    protected void increaseMovingSpeed() {
        movingSpeed = 9;
    }

    /**
     * Handle the generation of the animations of the pacman.
     */
    private void generateAfraidAnimations() {
        // This will return 1x2 array (see sprite for afraid ghost).
        Sprite[][] spritesTemp = RPGSprite.extractRawFrames("superpacman/ghost.afraid", 2, 1, 1, 1, this, 16, 16);
        Sprite[][] spritesAnimations = new Sprite[4][2];
        // We basically fill a 4x2 array with the 1x2 array. 
        for (int i = 0; i < spritesAnimations.length; i++) {
            spritesAnimations[i] = spritesTemp[0].clone();
        }
        this.afraidAnimations = Animation.createAnimations(18 / 2, spritesAnimations);

    }

    /**
     * Set state of the ghost to afraid, and update its animation accordingly.
     */
    public void setAfraidState() {
        this.isAfraid = true;
        this.currentAnimations = afraidAnimations;
        setAfraidTime();
    }

    /**
     * Set state of the ghost to normal, and update its animation accordingly.
     */
    public void setNormalState() {
        this.isAfraid = false;
        this.currentAnimations = normalStateAnimations;
    }

    public boolean isAfraid() {
        return isAfraid;
    }

    @Override
    public void update(float deltaTime) {

        //if the game is paused or Over, we "freeze" the ghosts' animations
        if (!GameOverGUI.gameIsOver) {
            if (!PauseGUI.gameIsPaused) {
                super.update(deltaTime);
            }
        }
        if (isDisplacementOccurs()) {
            currentAnimations[getOrientation().ordinal()].update(deltaTime);
        } else {
            currentAnimations[getOrientation().ordinal()].reset();
            Orientation nextOrientation = getNextOrientation();
            // if the orientation is null, the ghost does not move. 
            if (nextOrientation != null)
                orientate(nextOrientation);
            move(getSpeed());
        }
        afraidTime--;
        if (afraidTime <= 0 && isAfraid()) {
            setNormalState();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        currentAnimations[getOrientation().ordinal()].draw(canvas);
    }

    protected DiscreteCoordinates getRefugePosition() {
        return this.refugePosition;
    }

    private void setAfraidTime() {
        /*afraid time is a very large time
        as the time gap between every delta time in update() is very small and at each update,
        afraidTime decreases by 1 --- 230 thus allows for the afraid state of the ghosts to last 8-10 seconds
         */
        afraidTime = 230;
    }

    public void resetGhostSpeed() {
        movingSpeed = 13;
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
        return false;
    }

    @Override
    public List<DiscreteCoordinates> getFieldOfViewCells() {
        List<DiscreteCoordinates> fov = new ArrayList<DiscreteCoordinates>();
        for (int x = -LENGTH_FOV; x < LENGTH_FOV; x++) {
            for (int y = -LENGTH_FOV; y < LENGTH_FOV; y++) {
                fov.add(new DiscreteCoordinates(getCurrentMainCellCoordinates().x + x,
                        getCurrentMainCellCoordinates().y + y));
            }
        }
        return fov;
    }

    @Override
    public boolean wantsCellInteraction() {
        // Can't have cell interaction (it's a ghost)
        return false;
    }

    @Override
    public boolean wantsViewInteraction() {
        return true;
    }

    /**
     * Handle interactions for the ghost.
     */
    protected class GhostHandler implements SuperPacmanInteractionVisitor {
        public void interactWith(SuperPacmanPlayer player) {
        }
    }

    /**
     * send ghost back to its refuge position
     */
    public void returnToRefugePosition() {
        getOwnerArea().leaveAreaCells(this, getEnteredCells());
        setCurrentPosition(getRefugePosition().toVector());
        getOwnerArea().enterAreaCells(this, getCurrentCells());
        resetMotion();
    }

    public void resetGhost() {
        returnToRefugePosition();
    }

    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor) v).interactWith(this);
    }

}
