package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends NonRandomArea {

    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);
    public String getTitle() {
        return "superpacman/Level1";
    }

    protected void createArea() {
        super.createArea();
        registerActor(new Door("superpacman/Level2", new DiscreteCoordinates(15,29), Logic.TRUE, this,
                Orientation.DOWN, new DiscreteCoordinates(14,0), new DiscreteCoordinates(15,0)));
        registerGates(Orientation.RIGHT, 14, 3,this);
        registerGates(Orientation.RIGHT,15,3,  this);
        createFlockOfJamilas();
    }

    //calling the super method of SuperpacmanArea to register gates as actors
    public void registerGates(Orientation orientation, int x, int y, Logic signal) {
        super.createGates(orientation, x, y, signal);
    }

    public void createFlockOfJamilas(){
        createJamila(1,28);
        createJamila(28, 17);
        createJamila(20, 8);
        createJamila(7, 17);
        createJamila(28,28);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION;
    }

    /**
     @return true if the number of the diamonds in Level1 is 0
    */
    public boolean isOn(){
        return !(this.totalNbDiamondsIsNull());
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
