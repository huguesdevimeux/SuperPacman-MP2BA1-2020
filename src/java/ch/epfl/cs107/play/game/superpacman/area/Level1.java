package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends SuperPacmanArea {

    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
    
    public String getTitle() {
        return "superpacman/Level1";
    }

    protected void createArea() {
        super.createArea();
        Door door = new Door("superpacman/Level2", new DiscreteCoordinates(15,29), Logic.TRUE, this,
                Orientation.DOWN, new DiscreteCoordinates(14,0), new DiscreteCoordinates(15,0));
        registerActor(door);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION; 
    }

}
