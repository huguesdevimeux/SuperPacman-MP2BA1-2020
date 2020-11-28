package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level1 extends SuperPacmanArea {

    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
    
    public String getTitle() {
        return "superpacman/Level1";
    }

    protected void createArea() {
        // Base
        // SuperPacmanArea.configureWalls(this);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION; 
    }

}
