package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Level0 extends SuperPacmanArea {

    public String getTitle() {
        return "superpacman/Level0";
    }

    protected void createArea() {
        // Base
        SuperPacmanArea.configureWalls(this);
    }
}