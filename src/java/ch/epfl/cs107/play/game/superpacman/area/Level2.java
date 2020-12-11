package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level2 extends SuperPacmanArea {
    public static final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);

    private Key key1 = new Key(this, new DiscreteCoordinates(3,16));
    private Key key2 = new Key(this, new DiscreteCoordinates(26, 16));
    private Key key3 = new Key(this, new DiscreteCoordinates(2,8));
    private Key key4 = new Key(this, new DiscreteCoordinates(27,8));
    public Logic key3_4 = new And(key3, key4);

    public String getTitle() {
        return "superpacman/Level2";
    }

    protected void createArea() {
        super.createArea();
        SuperPacman.areaIndex++;
        registerActor(key1);
        registerActor(key2);
        registerActor(key3);
        registerActor(key4);
        createGates(Orientation.RIGHT, 8, 14, key1);
        createGates(Orientation.RIGHT, 8, 10, key1);
        createGates(Orientation.RIGHT, 8, 8, key1);
        createGates(Orientation.RIGHT, 21, 14, key2);
        createGates(Orientation.RIGHT, 21, 10, key2);
        createGates(Orientation.RIGHT, 21, 8, key2);
        createGates(Orientation.RIGHT, 10,2, (key4));
        createGates(Orientation.RIGHT,19,2, new And(key3, key4));
        createGates(Orientation.RIGHT,12,8, new And(key3, key4));
        createGates(Orientation.RIGHT,17,8, new And(key3, key4));
        createGates(Orientation.RIGHT, 14,3, this);
        createGates(Orientation.RIGHT,15,3, this);
        createGates(Orientation.DOWN, 5,12, key1);
        createGates(Orientation.DOWN, 24, 12, key2);
    }

    //calling method from SuperPacmanArea to register gates as actors
    public void createGates(Orientation orientation, int x, int y, Logic signal) {
        super.createGates(orientation, x, y, signal);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION;
    }
    /**
     @return true if the number of the diamonds in Level2 is 0
     method applies only to the gates at (14,3) and (15,3): they're the only ones
     depending on the number of diamonds collected
   */
    @Override
    public boolean isOn() {
        return !(totalNbDiamonds == 0);
    }
    @Override
    public boolean isOff() {
        return false;
    }
    @Override
    public float getIntensity() {
        return 0;
    }
}
