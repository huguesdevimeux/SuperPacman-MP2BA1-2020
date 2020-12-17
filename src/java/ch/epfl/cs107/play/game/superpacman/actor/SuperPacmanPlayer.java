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
import ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics.GameOverGUI;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics.PauseGUI;
import ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics.SuperPacmanPlayerStatusGUI;
import ch.epfl.cs107.play.game.superpacman.area.RandomArea;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuperPacmanPlayer extends Player {
    private int movingSpeed;
    private SuperPacmanPlayerHandler handler = new SuperPacmanPlayerHandler();
    private SuperPacmanPlayerStatusGUI statusDrawer;
    private PauseGUI pauseStatus = new PauseGUI();
    private GameOverGUI gameOverStatus = new GameOverGUI();
    private int score = 0;
    private int amountLife = 5;
    private Keyboard keyboard = getOwnerArea().getKeyboard();
    private Animation[] movingAnimations;
    private Sprite[][] sprites;
    private Orientation desiredOrientation;
    private List<DiscreteCoordinates> targetCell;

    public SuperPacmanPlayer(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
        super(area, orientation, coordinates);
        this.statusDrawer = new SuperPacmanPlayerStatusGUI(this);
        movingSpeed = 5;
        this.desiredOrientation = orientation;
        sprites = RPGSprite.extractSprites("superpacman/pacman", 4, 1, 1, this, 64, 64,
                new Orientation[]{Orientation.DOWN, Orientation.LEFT, Orientation.UP, Orientation.RIGHT});
        movingAnimations = Animation.createAnimations(movingSpeed / 2, sprites);
        resetMotion();
    }

    @Override
    public void update(float deltaTime) {
        updateDesiredOrientation(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
        updateDesiredOrientation(Orientation.UP, keyboard.get(Keyboard.UP));
        updateDesiredOrientation(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
        updateDesiredOrientation(Orientation.DOWN, keyboard.get(Keyboard.DOWN));

        // NOTE : As the name DOES NOT suggest, isDisplacementOccurs return whether the player is in displacement between
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

        //while the game is NOT over and NOT in pause, we call update from super class
        if (!GameOverGUI.gameIsOver) {
            if (!PauseGUI.gameIsPaused) {
                super.update(deltaTime);
            }
        }
        //we update both the pause and gameOver statuses
        pauseStatus();
        gameOverStatus();
        //if you want to quit the game, you can press Q
        if (keyboard.get(Keyboard.Q).isPressed()) System.exit(0);
    }

    public void pauseStatus() {
        //press SPACE bar to pause and P to unpause
        if (keyboard.get(Keyboard.SPACE).isPressed()) pauseStatus.setPause();
        if (keyboard.get(Keyboard.P).isPressed()) pauseStatus.setPlay();
    }
    public void gameOverStatus() {
        if (amountLife == 0) ((SuperPacmanArea) getOwnerArea()).endGame();
    }

    //method will be used when going through doors (ie to a new level)
    public void resetAmountLives(){
        amountLife = 5;
    }
    //method will be used when entering a new level or when player eats a Jamila
    public void resetSpeed() {
        movingSpeed = 5;
    }
    /*
    method will be used when player eats a coin
    it will the increase the ghosts' speed but not temporarily
    and eating a Jamila, will reset speed but will increase health
    making the game more dynamic
    */
    public void increaseSpeed() {
        movingSpeed = 4;
    }
    public void scareGhosts() {
        ((SuperPacmanArea) getOwnerArea()).scareGhosts();
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
        //this class handles the interactions between the player and all the possible actors

        public void interactWith(Door door) {
            setIsPassingADoor(door);
            //when interacting with a door, the total nb of diamonds will be sent back to 0
            ((SuperPacmanArea) getOwnerArea()).setCurrentDiamonds(0);
            //when entering a new level, we reset the speed to 5
            resetSpeed();
            //when entering a new level we also reset de amount of lives
            resetAmountLives();
        }

        //when the player will interact with the key, the actor key will disappear
        public void interactWith(Key key) {
            //we make sure to turn the signal off when the key is collected
            key.isCollected();
            getOwnerArea().unregisterActor(key);
        }

        public void interactWith(Bonus bonus) {
            //when interacting with the coin - the ghosts get scared
            scareGhosts();
            getOwnerArea().unregisterActor(bonus);
        }

        //"eating" a diamond will increment the score by 10
        public void interactWith(Diamond diamond) {
            score += 10;
            //when interacting with a diamond
            //logically the total number of diamonds decreases
            ((SuperPacmanArea) getOwnerArea()).decreaseCurrentDiamonds();
            getOwnerArea().unregisterActor(diamond);
        }

        //"eating" a cherry will increment the score by 200
        public void interactWith(Cherry cherry) {
                score += 200;
                getOwnerArea().unregisterActor(cherry);
        }

        public void interactWith(Ghost ghost) {
            if (ghost.isAfraid()) {
                ghost.resetGhost();
                score += ghost.GHOST_SCORE;
            } else {
                pacmanIsEaten();
                ((SuperPacmanArea) getOwnerArea()).resetAllGhosts();
            }
        }
        /*
        interacting with the actor jamila (Aka a heart) will :
        -increase health (if it is strictly under maximum health (5))
        -reset the player's speed which had previously been increased
        -reset the ghost's speed which had previously been increased
        but BE CAREFUL - you must interact with the nearest strawberry in order to eat a Jamila and
        the game will automatically register a fire once you interact with either a Jamila or a strawberry
        so proceed cautiously ;)
         */
        public void interactWith(Jamila jamila) {
            if (amountLife < 5) amountLife++;
            resetSpeed();
            ((SuperPacmanArea) getOwnerArea()).resetGhostSpeed();
            getOwnerArea().registerActor(new Enclosure(getOwnerArea(), Orientation.UP,
                    new DiscreteCoordinates((int) getPosition().getX(), (int) getPosition().getY())));
            getOwnerArea().unregisterActor(jamila);
        }

        //eating a strawberry will increase speed and allow access to Jamilas (that will then increase health
        //but reset the player's and the ghosts' speed)
        //You decide when to interact with a strawberry
        //but be careful, eating it will create a fire, meaning you won't be able to access that cell anymore
        public void interactWith(Strawberry strawberry) {
            //you must press E when interacting with a strawberry to eat it
            if (keyboard.get(Keyboard.E).isPressed()) {
                increaseSpeed();
                getOwnerArea().registerActor(new Enclosure(getOwnerArea(), Orientation.UP,
                        new DiscreteCoordinates((int) getPosition().getX(), (int) getPosition().getY())));
                strawberry.setCollected();
                getOwnerArea().unregisterActor(strawberry);
            }
        }

        /*
        the portal actor will enable the player to teleport somewhere on the map
        */
        public void interactWith(Portal portal) {
            teleportPacman();
            getOwnerArea().unregisterActor(portal);
        }
        @Override
        public void interactWith(Interactable other) {}
    }

    public DiscreteCoordinates getSpawnLocation() {
        return ((SuperPacmanArea) getOwnerArea()).getSpawnLocation();
    }

    public DiscreteCoordinates getTeleportLocation() {
        return ((SuperPacmanArea) getOwnerArea()).getTeleportLocation();
    }

    public void pacmanIsEaten() {
        amountLife--;
        getOwnerArea().leaveAreaCells(this, getEnteredCells());
        setCurrentPosition(getSpawnLocation().toVector());
        getOwnerArea().enterAreaCells(this, getCurrentCells());
        resetMotion();
    }

    public void teleportPacman() {
        getOwnerArea().leaveAreaCells(this, getEnteredCells());
        setCurrentPosition((getTeleportLocation().toVector()));
        getOwnerArea().enterAreaCells(this, getCurrentCells());
        resetMotion();
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
        ((SuperPacmanInteractionVisitor) v).interactWith(this);
    }

    @Override
    public void draw(Canvas canvas) {
        statusDrawer.setScore(score);
        statusDrawer.setAmountLife(amountLife);
        if (getOwnerArea().getClass() == RandomArea.class) {
            int numberDiamondsToCollect = ((RandomArea) getOwnerArea()).getNumberDiamondsToCollect();
            int currentDiamonds = ((SuperPacmanArea) getOwnerArea()).getCurrentDiamonds();
            statusDrawer.setNumberDiamondsLeft(Math.max(0, -numberDiamondsToCollect + currentDiamonds));
            statusDrawer.setNextLevel(((RandomArea) getOwnerArea()).getLevel() + 1);
        }
        statusDrawer.draw(canvas);
        movingAnimations[getOrientation().ordinal()].draw(canvas);
        pauseStatus.draw(canvas);
        gameOverStatus.draw(canvas);
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