package ch.epfl.cs107.play.game.rpg.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactor;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/**
 * Player is a Specific RPG actor
 */
public abstract class Player extends MovableAreaEntity implements Interactor {

    private boolean isPassingADoor;
    private Door passedDoor;

    /**
     * Default Player constructor
     * @param area (Area): Owner Area, not null
     * @param orientation (Orientation): Initial player orientation, not null
     * @param coordinates (Coordinates): Initial position, not null
     */
    public Player(Area area, Orientation orientation, DiscreteCoordinates coordinates) {
        super(area, orientation, coordinates);
        passedDoor = null;
        isPassingADoor = false;
    }

    /**
     * Leave an area by unregister this player
     */
    public void leaveArea(){
        getOwnerArea().unregisterActor(this);
    }

    /**
     *
     * @param area (Area): initial area, not null
     * @param position (DiscreteCoordinates): initial position, not null
     */
    public void enterArea(Area area, DiscreteCoordinates position){
        area.registerActor(this);
        area.setViewCandidate(this);

        setOwnerArea(area);
        setCurrentPosition(position.toVector());
        resetDoorStates();
        resetMotion();
    }

    /**
     * Reset the door state
     */
    private void resetDoorStates(){
        passedDoor = null;
        isPassingADoor = false;
    }

    /// Getter and setter for interaction

    /**
     * Indicate the player just passed a door
     * @param door (Door): the door passed, not null
     */
    protected void setIsPassingADoor(Door door){
        this.passedDoor = door;
        isPassingADoor = true;
    }

    /**@return (boolean): true if the player is passing a door*/
    public boolean isPassingADoor(){
        return isPassingADoor;
    }

    /**
     * Getter of the passing door
     * @return (Door)
     */
    public Door passedDoor(){
        return passedDoor;
    }
}
