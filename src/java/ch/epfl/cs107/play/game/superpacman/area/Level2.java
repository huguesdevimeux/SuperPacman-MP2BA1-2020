package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.game.tutosSolution.actor.SimpleGhost;
import ch.epfl.cs107.play.math.Vector;

public class Level2 extends SuperPacmanArea{

    public String getTitle(){return "sperpacman/Level2";}

    protected void createArea() {
        // Base
       SuperPacmanArea.configureWalls(this);
    }
}
