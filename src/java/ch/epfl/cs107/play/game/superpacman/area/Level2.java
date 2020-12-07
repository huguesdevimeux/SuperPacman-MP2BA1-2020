package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.game.tutosSolution.actor.SimpleGhost;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

public class Level2 extends NonRandomArea {
   
    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);

    public String getTitle(){return "superpacman/Level2";}

    protected void createArea() {
        super.createArea();
        //TODO - implement stuff if we have time
    }
    
    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION; 
    }

}
