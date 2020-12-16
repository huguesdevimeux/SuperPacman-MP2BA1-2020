package ch.epfl.cs107.play.game.superpacman.MazeUtils;

import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior.SuperPacmanCellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class will implement all the specificities of the pacman-like mazes, such as symetry, or ghost houses, or bonuses.
 */
public class RandomPacmanMap {

    private static final double TRESHOLD_RANDOM_GHOST_SPAWN = 0.98f;
	private SuperPacmanCellType[][] generatedMap;
    private int totalHeight;
    private int totalWidth;
    private Random randomGenerator;
	private List<DiscreteCoordinates> deadEnds;
    private List<DiscreteCoordinates> freeCells;
    private List<DiscreteCoordinates> doorsPositions; 

    public RandomPacmanMap(int height, int width) {
        assert (height % 2 == 1) : "Height must be odd";
        assert (width % 4 == 2): "Width must be a sum of two odd numbers";
        totalHeight = height;
        totalWidth = width;
        // this.deadEnds = new ArrayList<DiscreteCoordinates>();
        // this.freeCells = new ArrayList<DiscreteCoordinates>();
        generatedMap = new SuperPacmanCellType[totalWidth][totalHeight]; // x - y 
        randomGenerator = new Random();
        
        RandomPacmanMazeStructure randomPacmanMazeStructure = new RandomPacmanMazeStructure(height, width / 2, randomGenerator);
        SuperPacmanCellType[][] halfMaze = randomPacmanMazeStructure.getRandomStructure();
        generateMazeWithSymetry(halfMaze );
        connectTwoSides();
        dispatchBonuses();
        this.doorsPositions = generateDoorsPositions();
    }

	public SuperPacmanCellType[][] getBehavior() {
        return generatedMap;
    }
    
    /**
     * Given an a maze, will generate the whole map with its symetry to the X axis.
     * It will generate the list of dead ends as well. 
     * @param halfMaze
     */
    private void generateMazeWithSymetry(SuperPacmanCellType[][] halfMaze) {
        // Warning : to be consistent with the API, we chose to adort for the map coordinates [width][height], which makes the process 
        // confusing : totalHeight means the WIDTH of the matrix, when totalWidth means the HEUGHT of the matrix. (but have an opposite meaning according to the game (see AreaBehavior.java, constructor)).
        // Go through COLUMNS.
        for (int j = 0; j < totalHeight; j++) {
            // GO THROUGH LINES -NOT WIDTH !!
            for (int i = 0; i < totalWidth - 1; i++) {
                int indexToCopy = i < halfMaze.length ? i : halfMaze.length - (i % halfMaze.length + 1) - 1;
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

    /**
     * Handle the random generation of the bonuses.
     */
    private void dispatchBonuses() {
        for (int x = 1; x < generatedMap.length - 2; x++) {
            for (int y = 1; y < generatedMap[0].length - 1; y++) {
                if (generatedMap[x][y] != SuperPacmanCellType.WALL) {
                    // this will randomly place bonuses in the dead ends. 
                    if (isDeadEnd(new DiscreteCoordinates(x, y)) && randomGenerator.nextBoolean()) {
                        generatedMap[x][y] = SuperPacmanCellType.FREE_WITH_BONUS;
                    } else {
                        double decision = randomGenerator.nextDouble();
                        if (decision > TRESHOLD_RANDOM_GHOST_SPAWN)
                            generatedMap[x][y] = getRandomTypeOfGhost();
                        else if (decision > 0.9f)
                            generatedMap[x][y] = SuperPacmanCellType.FREE_WITH_CHERRY;
                        else
                            generatedMap[x][y] = SuperPacmanCellType.FREE_WITH_DIAMOND;
                    }
                }
            }

        }
    }
    

	private List<DiscreteCoordinates> generateDoorsPositions() {
        List <DiscreteCoordinates> cos = new ArrayList<DiscreteCoordinates>();
        int xPosDoor = (int) Math.ceil(totalHeight / 2.0) - 2;
        int xPosDoor2 = (int) Math.ceil(totalHeight / 2.0) - 1;

        generatedMap[xPosDoor][totalWidth] = SuperPacmanCellType.FREE_EMPTY;
        cos.add(new DiscreteCoordinates(xPosDoor, totalWidth)); 
        generatedMap[xPosDoor2][totalWidth] = SuperPacmanCellType.FREE_EMPTY;
        cos.add(new DiscreteCoordinates(xPosDoor2, totalWidth)); 
        generatedMap[xPosDoor][totalWidth - 1] = SuperPacmanCellType.FREE_EMPTY;
        generatedMap[xPosDoor2][totalWidth - 1] = SuperPacmanCellType.FREE_EMPTY;
        
        return cos; 
	}

    
    /**
     * return if the position is a dead end (if it has more than 3 neibhors walls)
     * @param toCheck
     * @return
     */
    private boolean isDeadEnd(DiscreteCoordinates toCheck) {
        int count = 0;
        for (DiscreteCoordinates n : getNeibhors(toCheck)) {
            if (generatedMap[n.x][n.y] == SuperPacmanCellType.WALL)
                count++;
        }
        return count >= 3;
    }
    
    private boolean isWall(DiscreteCoordinates position) {
        return generatedMap[position.x][position.y] == SuperPacmanCellType.WALL;
    }

    //TODO : Faire une interface !
    /** 
    * Given a position, return all the neibhors WITHIN the grid.
     * @param position
     */
    private List<DiscreteCoordinates> getNeibhors(DiscreteCoordinates position) {
        List<DiscreteCoordinates> neibh = new ArrayList<DiscreteCoordinates>();
        int x = position.x; 
        int y = position.y;

        // I know, this is ugly. But is there really any best way to do this? at least, it's explicit. 
        if (x + 1 < totalWidth) neibh.add(new DiscreteCoordinates(x + 1, y)); 
        if (x - 1 >= 0) neibh.add(new DiscreteCoordinates(x - 1, y)); 
        if (y + 1 < totalHeight) neibh.add( new DiscreteCoordinates(x, y + 1)); 
        if (y - 1 >= 0) neibh.add(new DiscreteCoordinates(x, y - 1)); 

        return neibh; 
    }

    public List<DiscreteCoordinates> getDoorsPositions() {
        return doorsPositions;
    }
    
    private SuperPacmanCellType getRandomTypeOfGhost() {
        double a = randomGenerator.nextDouble(); 
        if (a > 0.7) 
            return SuperPacmanCellType.FREE_WITH_INKY;
        else if (a > 0.3f)
            return SuperPacmanCellType.FREE_WITH_PINKY;
        return SuperPacmanCellType.FREE_WITH_BLINKY;
	}
}
