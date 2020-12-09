package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.superpacman.behavior.RandomBehavior;
import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class RandomArea extends SuperPacmanArea {

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "randomArea";
	}

	@Override
	public DiscreteCoordinates getSpawnLocation() {
		// TODO Auto-generated method stub
		return new DiscreteCoordinates(1, 1);
	}

	@Override
	protected SuperPacmanBehavior getBehaviorTypeNewInstance(Window window) {
		return new RandomBehavior(window, getTitle());
	}
    
}
