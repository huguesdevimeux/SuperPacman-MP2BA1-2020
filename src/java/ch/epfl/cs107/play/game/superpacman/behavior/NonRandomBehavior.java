package ch.epfl.cs107.play.game.superpacman.behavior;

import ch.epfl.cs107.play.window.Window;

public class NonRandomBehavior extends SuperPacmanBehavior{

	public NonRandomBehavior(Window window, String name) {
        super(window, name);
        
        // A Non-random behavior sets the type of the cells according to a behavior map. 
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                // Generate a behavior for each cell. 
                SuperPacmanCellType color = SuperPacmanCellType.toType(getRGB(getHeight() - 1 - y, x));
                setCell(x, y, new SuperPacmanCell(x, y, color));
            }
        }
        super.setGraph();
	}
    
}
