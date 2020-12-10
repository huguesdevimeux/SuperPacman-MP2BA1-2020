package ch.epfl.cs107.play.game.superpacman.behavior;

import java.util.List;

import ch.epfl.cs107.play.game.superpacman.MazeUtils.RandomPacmanMap;
import ch.epfl.cs107.play.game.superpacman.MazeUtils.RandomPacmanMazeStructure;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class RandomBehavior extends SuperPacmanBehavior {

    private RandomPacmanMap randomMazeGenerated;

    public RandomBehavior(Window window, String name, String nextArea) {
        super(window, 19, 18);
        randomMazeGenerated = new RandomPacmanMap(getHeight(), getWidth(), nextArea);
        SuperPacmanCellType[][] randomBehavior = randomMazeGenerated.getBehavior();

        // Set each cells according to the random behavior map generated.
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                // Generate a behavior for each cell. ;
                setCell(x, y, new SuperPacmanCell(x, y, randomBehavior[x][y]));
            }
        }
        super.setGraph();
    }
    
    public List<DiscreteCoordinates> getDoorsPosition() {
        return randomMazeGenerated.getDoorsPositions();
    }

}
