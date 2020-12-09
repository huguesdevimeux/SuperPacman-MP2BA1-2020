package ch.epfl.cs107.play.game.superpacman.MazeUtils;

import java.util.Random;

import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior.SuperPacmanCellType;

/**
 * This class will implement all the specificities of the pacman-like mazes, such as symetry, or ghost houses. 
 */
public class RandomPacmanMap {

    private SuperPacmanCellType[][] generatedMap;
    private int totalHeight;
    private int totalWidth;
    private Random randomGenerator; 

    public RandomPacmanMap(int height, int width) {
        assert (height % 2 == 1) : "Height must be odd";
        assert (width % 4 == 2): "Width must be a sum of two odd numbers";
        totalHeight = height;
        totalWidth = width;
        generatedMap = new SuperPacmanCellType[totalWidth][totalHeight]; // x - y 
        randomGenerator = new Random();
        // WARNING !!! TRANSPOSE EVERY COORDINATES
        
        SuperPacmanCellType[][] halfMaze = new RandomPacmanMazeStructure(height, width / 2, randomGenerator).getRandomStructure();
        generateSymetry(halfMaze);
        connectTwoSides();
    }

    public SuperPacmanCellType[][] getBehavior() {
        return generatedMap;
    }
    /**
     * Given an a maze, will generate the whole map with its symetry to the X axis.
     * @param halfMaze
     */
    private void generateSymetry(SuperPacmanCellType[][] halfMaze) {
        // Warning : to be consistent with the API, we chose to adort for the map coordinates [width][height], which makes the process 
        // confusing : totalHeight means the WIDTH of the matrix, when totalWidth means the HEUGHT of the matrix. (but have an opposite meaning according to the game (see AreaBehavior.java, constructor)).
        // Go through COLUMNS.
        for (int j = 0; j < totalHeight; j++) {
            // GO THROUGH LINES -NOT WIDTH !!
            for (int i = 0; i < totalWidth - 1; i++) {
                int indexToCopy = i < halfMaze.length ? i : halfMaze.length - (i % halfMaze.length + 1) - 1;
                System.out.println(indexToCopy);
                generatedMap[i][j] = halfMaze[indexToCopy][j];
            }
        }
    }
    
    /**
     * Will connect the two sides by randomly breaking wall along the wall separing the two sides (the two symetric mazes.)
     * A very naive algorithm : browse the middle name, and break a wall randomly within it, if the cell is not in the middle of a bar of walls. 
     */
    private void connectTwoSides() {
        int offset = this.randomGenerator.nextInt(3) + 2;
        for (int i = 1; i < generatedMap.length; i++) {
            if (offset-- <= 0) {
                if (generatedMap[(totalHeight / 2) - 1 - 1][i] != SuperPacmanCellType.WALL
                        && generatedMap[(totalHeight / 2) - 1 + 1][i] != SuperPacmanCellType.WALL) {
                    generatedMap[(totalHeight / 2) - 1][i] = SuperPacmanCellType.FREE_EMPTY;
                }
                offset = this.randomGenerator.nextInt(3) + 2;
            }
        }
    }
}
