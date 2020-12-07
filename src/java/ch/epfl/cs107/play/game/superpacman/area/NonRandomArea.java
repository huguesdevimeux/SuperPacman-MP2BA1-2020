package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.superpacman.behavior.NonRandomBehavior;
import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior;
import ch.epfl.cs107.play.window.Window;

public abstract class NonRandomArea extends SuperPacmanArea {

    public abstract String getTitle();

    @Override
    protected SuperPacmanBehavior getBehaviorTypeNewInstance(Window window) {
        return new NonRandomBehavior(window, getTitle());
    }
    
}
