package ch.epfl.cs107.play.game.superpacman.behavior;

import java.util.List;

import ch.epfl.cs107.play.game.superpacman.MazeUtils.RandomPacmanMap;
import ch.epfl.cs107.play.game.superpacman.MazeUtils.RandomPacmanMazeStructure;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class RandomBehavior extends SuperPacmanBehavior {

    private RandomPacmanMap randomMazeGenerated;

    public RandomBehavior(Window window, int height, int width, double spawnRateGhosts) {
        super(window, height, width);
        // Requirements of the algorithm used. See implementation of RandomPacmanMap for more information. 
        assert (height % 2 == 1) : "Height must be odd";
        assert (width % 4 == 2): "Width must be a sum of two odd numbers";
        randomMazeGenerated = new RandomPacmanMap(height, width, spawnRateGhosts);
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
