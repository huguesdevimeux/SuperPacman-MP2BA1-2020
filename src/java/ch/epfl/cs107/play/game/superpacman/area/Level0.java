package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends SuperPacmanArea {

    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10, 1);

    public String getTitle() {
        return "superpacman/Level0";
    }

    public void createArea() {
        super.createArea();
        Door door = new Door("superpacman/Level1", new DiscreteCoordinates(15,6), Logic.TRUE, this,
                Orientation.UP, new DiscreteCoordinates(5,9), new DiscreteCoordinates(6,9) );
        registerActor(door);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION; 
    }

}