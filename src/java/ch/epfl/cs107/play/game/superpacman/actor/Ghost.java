package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Animation;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Canvas;

public abstract class Ghost extends MovableAreaEntity implements Interactor {

    private static final int LENGTH_FOV = 2;
    private final GhostHandler handler = new GhostHandler(); 

    protected Sprite sprite;
    protected Animation[] normalStateAnimations;
    private Animation[] afraidAnimations;
    private Animation[] currentAnimations;

    private DiscreteCoordinates refugeLocation;
    private boolean isAfraid;


    public Ghost(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
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
                new Orientation[] { Orientation.UP, Orientation.RIGHT, Orientation.DOWN, Orientation.LEFT });
        this.normalStateAnimations = Animation.createAnimations(18 / 2, sprites);
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
    private void setAfraidState() {
        this.isAfraid = true;
        this.currentAnimations = afraidAnimations;
    }
    
    /**
     * Set state of the ghost to normal, and update its animation accordingly.
     */
    private void setNormalState() {
        this.isAfraid = false; 
        this.currentAnimations = normalStateAnimations; 
    }
    
    protected void updateAfraidState() {
        // TODO put here a condition to set to scared state + add a variable.
        // J'en sais rien en vrai c'est Luca qui le fera lmao
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        updateAfraidState();
        if (isDisplacementOccurs()) {
            currentAnimations[getOrientation().ordinal()].update(deltaTime);
        }
        else {
            currentAnimations[getOrientation().ordinal()].reset();
            orientate(getNextOrientation());
            move(getSpeed());
        }
    }

    @Override
    public void draw(Canvas canvas) {
        currentAnimations[getOrientation().ordinal()].draw(canvas); 
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList(getCurrentMainCellCoordinates());
    }

    @Override
    public boolean takeCellSpace() {
        // One can walk on a ghost (?).
        return false;
    }

    // INTERACTIONS STUFF

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
        for (int x = - LENGTH_FOV; x < LENGTH_FOV; x++) {
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
    private class GhostHandler implements SuperPacmanInteractionVisitor {
        public void interactWith(SuperPacmanPlayer player) {
            System.out.println("Je vois le player!");
        }
    }
    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor)v).interactWith(this);
    }
    
}
