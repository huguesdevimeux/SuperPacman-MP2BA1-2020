package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.*;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {

    default void interactWith(Wall wall) {
    }
    default void interactWith(SuperPacmanPlayer player) {
    }
    default void interactWith(Diamond diamond) {
    }
    default void interactWith(Cherry cherry) {
    }
    default void interactWith(Bonus bonus) {
    }
    default void interactWith(Key key) {
    }
    default void interactWith(Ghost ghost) {
    }

    //without this method here -- error messages appear in ghostHandler
    @Override
    default void interactWith(Interactable other) {
    }
}


