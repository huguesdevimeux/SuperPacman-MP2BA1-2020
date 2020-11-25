package ch.epfl.cs107.play.game.tutosSolution.area.tuto2;

import ch.epfl.cs107.play.game.areagame.actor.Background;
import ch.epfl.cs107.play.game.areagame.actor.Foreground;
import ch.epfl.cs107.play.game.tutosSolution.actor.SimpleGhost;
import ch.epfl.cs107.play.game.tutosSolution.area.Tuto2Area;
import ch.epfl.cs107.play.math.Vector;

/**
 * Specific area
 */
public class Village extends Tuto2Area {
	
	@Override
	public String getTitle() {
		return "zelda/Village";
	}
	

	protected void createArea() {
        // Base
	
        registerActor(new Background(this)) ;
        registerActor(new Foreground(this)) ;
        registerActor(new SimpleGhost(new Vector(20, 10), "ghost.2"));
        }
	
	
    
}
