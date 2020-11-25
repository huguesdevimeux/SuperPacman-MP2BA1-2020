package ch.epfl.cs107.play.game.tutosSolution.area.tuto2;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.tutosSolution.area.Tuto2Area;

/**
 * Specific area
 */
public class Ferme extends Tuto2Area {
	
	@Override
	public String getTitle() {
		return "zelda/Ferme";
	}

	protected void createArea() {
        // Base
        registerActor(new Background(this));
        registerActor(new Foreground(this));
	}
	
}

