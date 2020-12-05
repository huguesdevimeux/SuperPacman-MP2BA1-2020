package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;

/**
 * Blinky is characterised by its random moves.
 */
public class Blinky extends Ghost {
    private int movingSpeed = 18; 
    
    public Blinky(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    /**
     * Blinky moves randomly
     */
    public Orientation getNextOrientation() {
        // there is 4 orientations. 
        int randomInt = RandomGenerator.getInstance().nextInt(4);
        return Orientation.fromInt(randomInt);
    }

    @Override
    protected String getTitle() {
        return "superpacman/ghost.blinky";
    }

    @Override
    protected int getSpeed() {
        return movingSpeed;
    }
    
    
}
