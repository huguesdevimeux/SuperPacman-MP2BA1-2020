package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Key;
import ch.epfl.cs107.play.game.superpacman.actor.Portal;
import ch.epfl.cs107.play.game.superpacman.actor.Strawberry;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level0 extends NonRandomArea {
    private Key key0 = new Key(this, new DiscreteCoordinates(3,4));
    //registering this new actor to become familiar with
    private Strawberry strawberry0 = new Strawberry(this, new DiscreteCoordinates(1,8));
    //registering this actor to experience what it does
    private Portal portal = new Portal(this, new DiscreteCoordinates(1,1));
    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(10, 1);
    public final DiscreteCoordinates TELEPORT_LOCATION = new DiscreteCoordinates(8,6);
    public String getTitle() {
        return "superpacman/Level0";
    }

    public void createArea() {
        super.createArea();
        Door door = new Door("superpacman/Level1", new DiscreteCoordinates(15, 6), Logic.TRUE, this,
                Orientation.UP, new DiscreteCoordinates(5, 9), new DiscreteCoordinates(6, 9));
        registerActor(door);
        registerActor(key0);
        registerActor(strawberry0);
        //when the player will interact with the shadow, it will teleport somewhere in the grid
        registerActor(portal);
        //the two gates of level0 use the key as signal
        super.createGate(Orientation.RIGHT, 5, 8, key0);
        super.createGate(Orientation.LEFT, 6, 8, key0);
        /*
        we will create an actor jamila just for the taste
        in level0, it will have no impact except decrease
        the player's speed if he eats the coin beforehand
        however, you must interact with the a strawberry before eating a Jamila
         */
        createJamila(10,8, strawberry0);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION;
    }

    public DiscreteCoordinates getTeleportLocation(){
        return this.TELEPORT_LOCATION;
    }
    //in level 0 we don't put conditions on isOn and isOff
    @Override
    public boolean isOn() {
        return true;
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