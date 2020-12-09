package ch.epfl.cs107.play.game.superpacman.behavior;

import ch.epfl.cs107.play.game.superpacman.MazeUtils.RandomPacmanMap;
import ch.epfl.cs107.play.game.superpacman.MazeUtils.RandomPacmanMazeStructure;
import ch.epfl.cs107.play.window.Window;

public class RandomBehavior extends SuperPacmanBehavior {

    public RandomBehavior(Window window, String name) {
        super(window, 19, 18);
        System.out.println(1);
        RandomPacmanMap randomMaze = new RandomPacmanMap(getHeight(), getWidth()); 
        SuperPacmanCellType[][] randomBehavior = randomMaze.getBehavior();

        // Set each cells according to the random behavior map generated.
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                // Generate a behavior for each cell. ;
                setCell(x, y, new SuperPacmanCell(x, y, randomBehavior[x][y]));
            }
        }
        super.setGraph();
    }

}
