package ch.epfl.cs107.play.game.superpacman.menu;

import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class Pause extends SuperPacmanArea implements Keyboard {

    private boolean pIsPressed = false;

    @Override
    public Button get(int code) {
        if(code == 0)pIsPressed = true;
        return null;
    }

    @Override
    public void createArea() {
        super.createArea();
        registerActor(new Foreground(this));
    }

    @Override
    public String getTitle() {
        return "superpacman/pause";
    }

    @Override
    public boolean isOn() {
        return false;
    }

    @Override
    public boolean isOff() {
        return false;
    }

    @Override
    public float getIntensity() {
        return 0;
    }

    @Override
    public DiscreteCoordinates getSpawnLocation() {
        return null;
    }

    @Override
    public DiscreteCoordinates getTeleportLocation() {
        return null;
    }

    @Override
    protected SuperPacmanBehavior getBehaviorTypeNewInstance(Window window) {
        return null;
    }
}
