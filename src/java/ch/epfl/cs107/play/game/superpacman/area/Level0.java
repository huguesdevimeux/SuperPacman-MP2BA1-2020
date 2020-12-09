package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Gate;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.Signal;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

public class Level0 extends NonRandomArea {
    private Key key = new Key(this, new DiscreteCoordinates(3,4));
    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10, 1);

    public String getTitle() {
        return "superpacman/Level0";
    }

    public void createArea() {
        super.createArea();
        Door door = new Door("superpacman/Level1", new DiscreteCoordinates(15, 6), Logic.TRUE, this,
                Orientation.UP, new DiscreteCoordinates(5, 9), new DiscreteCoordinates(6, 9));
        registerActor(door);
        registerActor(key);
        registerGates(Orientation.RIGHT, 5, 8);
        registerGates(Orientation.LEFT, 6,8);
    }
    //call the method from SuperpacmanArea to register gates as actors
    public void registerGates(Orientation orientation, int x, int y) {
        super.createGates(orientation, x, y);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION;
    }

}