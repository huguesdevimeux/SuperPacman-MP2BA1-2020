package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.actor.ManBall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;

public class Level1 extends NonRandomArea {

    private ManBall manBall1 = new ManBall(this, new DiscreteCoordinates(16, 24));
    private ManBall manBall2 = new ManBall(this, new DiscreteCoordinates(28, 1));
    private ManBall manBall3 = new ManBall(this, new DiscreteCoordinates(1, 1));
    private ManBall manBall4 = new ManBall(this, new DiscreteCoordinates(13, 24));

    public final DiscreteCoordinates PLAYER_SPAWN_POSITION = new DiscreteCoordinates(15, 6);

    public String getTitle() {
        return "superpacman/Level1";
    }

    protected void createArea() {
        super.createArea();
        registerActor(new Door("superpacman/Level2", new DiscreteCoordinates(15, 29), Logic.TRUE, this,
                Orientation.DOWN, new DiscreteCoordinates(14, 0), new DiscreteCoordinates(15, 0)));
        registerGates(Orientation.RIGHT, 14, 3, this);
        registerGates(Orientation.RIGHT, 15, 3, this);
        createFlockOfJamilas();
    }

    //calling the super method of SuperpacmanArea to register gates as actors
    public void registerGates(Orientation orientation, int x, int y, Logic signal) {
        super.createGates(orientation, x, y, signal);
    }

    public void createFlockOfJamilas() {
 /*
    we must register manBall as an actor manually for Jamila to use it as a signal
    side note : you must interact with a manBall before being able to interact with a Jamila
    AND be careful - some balls allow access to Jamilas that are on the other side of the map
     */
        registerActor(manBall1);
        registerActor(manBall2);
        registerActor(manBall3);
        registerActor(manBall4);
        createJamila(1, 28, manBall3);
        createJamila(28, 17, manBall1);
        createJamila(20, 8, manBall2);
        createJamila(7, 17, manBall4);
        createJamila(28, 28, manBall1);
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return this.PLAYER_SPAWN_POSITION;
    }

    /**
     * @return true if the number of the diamonds in Level1 is 0
     */
    public boolean isOn() {
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
