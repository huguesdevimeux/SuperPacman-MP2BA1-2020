package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level2 extends NonRandomArea {
   
    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);

    public String getTitle() {
        return "superpacman/Level2";
    }

    protected void createArea() {
        super.createArea();
        createKey(3, 16);
        createKey(26, 16);
        createKey(2, 8);
        createKey(27, 8);
        createGates(Orientation.RIGHT, 8, 14);
        createGates(Orientation.RIGHT, 8, 10);
        createGates(Orientation.RIGHT, 8, 8);
        createGates(Orientation.RIGHT, 21, 14);
        createGates(Orientation.RIGHT, 21, 10);
        createGates(Orientation.RIGHT, 21, 8);
        createGates(Orientation.RIGHT, 10,2);
        createGates(Orientation.RIGHT,19,2);
        createGates(Orientation.RIGHT,12,8);
        createGates(Orientation.RIGHT,17,8);
        createGates(Orientation.RIGHT, 14,3);
        createGates(Orientation.RIGHT,15,3);
        createGates(Orientation.DOWN, 5,12);
        createGates(Orientation.DOWN, 24, 12);
        //dont know how to create a method to replace all this nastiness
    }

    public void createKey(int x, int y) {
        super.createKeys(x, y);
    }

    public void createGates(Orientation orientation, int x, int y) {
        super.createGates(orientation, x, y);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION;
    }
}
