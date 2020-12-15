package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.Strawberry;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends NonRandomArea {

    private Strawberry strawberry1 = new Strawberry(this, new DiscreteCoordinates(16, 24));
    private Strawberry strawberry2 = new Strawberry(this, new DiscreteCoordinates(28, 1));
    private Strawberry strawberry3 = new Strawberry(this, new DiscreteCoordinates(1, 1));
    private Strawberry strawberry4 = new Strawberry(this, new DiscreteCoordinates(13, 24));

    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
    public final DiscreteCoordinates TELEPORT_LOCATION = new DiscreteCoordinates(28, 2);

    public String getTitle() {
        return "superpacman/Level1";
    }

    protected void createArea() {
        super.createArea();

        registerActor(new Door("superpacman/Level2", new DiscreteCoordinates(15,29), Logic.TRUE, this,
                Orientation.DOWN, new DiscreteCoordinates(14,0), new DiscreteCoordinates(15,0)));
        super.createGate(Orientation.RIGHT, 14, 3,this);
        super.createGate(Orientation.RIGHT,15,3,  this);
        createPortal(28, 19);
        createPortal(7, 3);
        createPortal(13, 21);
        createPortal(16, 21);
        createFlockOfJamilas();
    }

    public void createFlockOfJamilas() {
     /*
     we must register strawberry as an actor manually for Jamila to use it as a signal
     side note : you must interact with a strawberry before being able to interact with a Jamila
     AND be careful - you can access Jamilas only if you eat the nearest strawberries
      */
        registerActor(strawberry1);
        registerActor(strawberry2);
        registerActor(strawberry3);
        registerActor(strawberry4);
        createJamila(1, 28, strawberry4);
        createJamila(28, 17, strawberry2);
        createJamila(20, 8, strawberry2);
        createJamila(7, 17, strawberry3);
        createJamila(28, 28, strawberry1);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION;
    }

    public DiscreteCoordinates getTeleportLocation() {
        return this.TELEPORT_LOCATION;
    }

    /**
    @return true if the number of the diamonds in Level1 is 0
    */
    public boolean isOn(){
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
