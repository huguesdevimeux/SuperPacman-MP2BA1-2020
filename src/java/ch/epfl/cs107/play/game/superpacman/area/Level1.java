package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.superpacman.actor.Wall;

public class Level1 extends SuperPacmanArea {

    public String getTitle() {
        return "superpacman/Level1";
    }

    protected void createArea() {
        // Base
        SuperPacmanArea.configureWalls(this);
    }
}
