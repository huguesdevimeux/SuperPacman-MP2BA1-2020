package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Spooky;
import ch.epfl.cs107.play.game.superpacman.actor.Strawberry;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.And;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level2 extends NonRandomArea {

    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 29);
    public final DiscreteCoordinates TELEPORT_LOCATION = new DiscreteCoordinates(3, 26);

    //we manually register spooky as he only lives in level2
    //and there is only one occurence of spooky
    private Spooky spooky = new Spooky(this, Orientation.RIGHT, new DiscreteCoordinates(15, 11));
    private Strawberry strawberry1 = new Strawberry(this, new DiscreteCoordinates(28, 3));
    private Strawberry strawberry2 = new Strawberry(this, new DiscreteCoordinates(2, 5));
    private Strawberry strawberry3 = new Strawberry(this, new DiscreteCoordinates(1, 28));
    private Strawberry strawberry4 = new Strawberry(this, new DiscreteCoordinates(28, 28));
    private Key key1 = new Key(this, new DiscreteCoordinates(3, 16));
    private Key key2 = new Key(this, new DiscreteCoordinates(26, 16));
    private Key key3 = new Key(this, new DiscreteCoordinates(2,8));
    private Key key4 = new Key(this, new DiscreteCoordinates(27,8));

    public String getTitle() {
        return "superpacman/Level2";
    }

    protected void createArea() {
        Logic key3_4 = new And(key3, key4);
        super.createArea();
        registerActor(spooky);
        registerActor(key1);
        registerActor(key2);
        registerActor(key3);
        registerActor(key4);
        super.createGate(Orientation.RIGHT, 8, 14, key1);
        super.createGate(Orientation.RIGHT, 8, 10, key1);
        super.createGate(Orientation.RIGHT, 8, 8, key1);
        super.createGate(Orientation.RIGHT, 21, 14, key2);
        super.createGate(Orientation.RIGHT, 21, 10, key2);
        super.createGate(Orientation.RIGHT, 21, 8, key2);
        super.createGate(Orientation.RIGHT, 10,2, key3_4);
        super.createGate(Orientation.RIGHT,19,2, key3_4);
        super.createGate(Orientation.RIGHT,12,8, key3_4);
        super.createGate(Orientation.RIGHT,17,8, key3_4);
        super.createGate(Orientation.RIGHT, 14,3, this);
        super.createGate(Orientation.RIGHT,15,3, this);
        super.createGate(Orientation.DOWN, 5,12, key1);
        super.createGate(Orientation.DOWN, 24, 12, key2);
        createFlockOfJamilas();
        super.createPortal(12, 19);
        super.createPortal(1, 3);
        super.createPortal(28, 12);
    }

    public void createFlockOfJamilas() {
        registerActor(strawberry1);
        registerActor(strawberry2);
        registerActor(strawberry3);
        registerActor(strawberry4);
        createJamila(19, 13, strawberry1);
        createJamila(6, 24, strawberry3);
        createJamila(11, 21, strawberry3);
        createJamila(23, 24, strawberry4);
        createJamila(18, 21, strawberry4);
        createJamila(6, 2, strawberry2);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION;
    }

    public DiscreteCoordinates getTeleportLocation() {
        return this.TELEPORT_LOCATION;
    }

    /**
     * @return true if the number of the diamonds in Level2 is 0
     * method applies only to the gates at (14,3) and (15,3): they're the only ones
     * depending on the number of diamonds collected
     */
    @Override
    public boolean isOn() {
        return !(getCurrentDiamonds() == 0);
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
