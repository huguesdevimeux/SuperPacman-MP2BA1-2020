package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0 extends SuperPacmanArea {

    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10, 1); 

    public String getTitle() {
        return "superpacman/Level0";
    }

    protected void createArea() {
        // Base
         //SuperPacmanArea.configureWalls(this);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION; 
    }

    
}