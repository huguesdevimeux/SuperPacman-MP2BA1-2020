package ch.epfl.cs107.play.game.superpacman.handler;

import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;

public interface SuperPacmanInteractionVisitor extends RPGInteractionVisitor {


    default void interactWith(Wall wall) {
    }

    default void interactWith(SuperPacmanPlayer player) {
    }

}


