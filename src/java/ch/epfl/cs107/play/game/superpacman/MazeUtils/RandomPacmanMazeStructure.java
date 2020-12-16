package ch.epfl.cs107.play.game.superpacman.MazeUtils;

import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior.SuperPacmanCellType;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;

import java.util.*;

/** 
 * This is the core of the maze generation. It will handle basic pacman-type maze. 
 */
public class RandomPacmanMazeStructure {

    private SuperPacmanCellType[][] generatedMap;
    private int height;
    private int width;
    private Random randomGenerator;
    private SuperPacmanCellType fillingType;
    private List<DiscreteCoordinates> emptyCells; 
    
    public RandomPacmanMazeStructure(int height, int width, Random randomGenerator) {
        assert (height % 2 == 1 && width % 2 == 1) : "Must be odd! ";
        this.height = height;
        this.width = width;
        this.fillingType = SuperPacmanCellType.FREE_EMPTY;

        this.randomGenerator = randomGenerator; 
        generatedMap = new SuperPacmanCellType[width][height]; // WARNING : it's x - y (to be consitent with the rest of the game)

        // We initalized the map with only walls; as the random walker will "break" walls to construct paths that will form the maze. 
        for (int row = 0; row < generatedMap.length; row++) {
            Arrays.fill(generatedMap[row], SuperPacmanCellType.WALL);
        }
        generateRandomMap(new DiscreteCoordinates(3, 3));
    }

	public List<DiscreteCoordinates> getFreeCells() {
		return emptyCells;
	}


	/**
     * Get the random structure generated. 
     * @return 
     */
    public SuperPacmanCellType[][] getRandomStructure() {
        return generatedMap;
    }
    
    /**
     * This is the core of the generating maze function. This is basically a randomized Depth-First-Traversal of the graph associated with the grid.
     * During the process, the algorithm will as well (randomly) remove dead-ends.
     * TIMPORTANT : the graph contains all the cells spaced with exactly ONE cell. This allows us to materialize a wall by an absence of connexion between two nodes. 
     * @param startingPoint
     */
    private void generateRandomMap(DiscreteCoordinates startingPoint) {
        Deque<DiscreteCoordinates> cellsStack = new LinkedList<DiscreteCoordinates>();
        boolean visited[][] = new boolean[this.width][this.height]; // x y

        cellsStack.addFirst(startingPoint);
        visited[startingPoint.x][startingPoint.y] = true;
        cleanCell(startingPoint);

        while (!cellsStack.isEmpty()) {
            DiscreteCoordinates currentPosition = cellsStack.removeFirst();
            List<DiscreteCoordinates> avNeibh = getAvailableNeibhors(currentPosition, visited);

            if (avNeibh.size() > 0) {
                int selectedIndex = randomGenerator.nextInt(avNeibh.size());
                for (int i = 0; i < avNeibh.size(); i++) {
                    cleanCell(avNeibh.get(i));
                    connectTwoCell(currentPosition, avNeibh.get(i));
                    visited[avNeibh.get(i).x][avNeibh.get(i).y] = true;
                    if (i != selectedIndex)
                        cellsStack.addFirst(avNeibh.get(i));
                }
                cellsStack.addFirst(avNeibh.get(selectedIndex));
            }

            // If there is no available neibhor, it mean that it is a dead-end. As we want to avoid dead-ends, We then have to modify it, by connecting the crrrent cell with an adjacent cell. 
            // But when doing this, we have to assure that it won't create piece of walls alone. (so, the wall that will be removed during the connection process)
            // must NOT be connected to a tip wall. See doc for isTipWall.
            // NOTE : This won't remove all the dead end, as at this step the DFS is not finished. There will be still dead-ends when the DFS is over that wouldn't have been de
            // detected because this only detect dead-end suring the process. 
            // But that's cool if there are still dead-end left, and also randoms. So, it's not a bug, it's a feature. 
            // (Like, really. This is totally fixable, this is left on purpose.)
            else {
                List<DiscreteCoordinates> candidatesToMakeConnection = getNeibhors(currentPosition);
                List<DiscreteCoordinates> validCandidates = new ArrayList<DiscreteCoordinates>(
                        candidatesToMakeConnection);

                // Invalidate cells connected to a tip-wall; or connected to a wall at the edge of the map. 
                for (DiscreteCoordinates candidate : candidatesToMakeConnection) {
                    for (DiscreteCoordinates neibhOfCandidate : getNeibhors(candidate)) {
                        if (isTipWall(neibhOfCandidate) || isAtTheEdge(neibhOfCandidate)) {
                            validCandidates.remove(candidate);
                            break;
                        }
                    }
                }
                // If there are aviavle candidates, we chose one randomly and break the wall. 
                if (validCandidates.size() > 0)
                    connectTwoCell(currentPosition,
                            validCandidates.get(randomGenerator.nextInt(validCandidates.size())));
            }
        }

    }


    /** 
    * Given a position, return all the neibhors WITHIN the grid.
     * @param position
     */
    private List<DiscreteCoordinates> getNeibhors(DiscreteCoordinates position) {
        List<DiscreteCoordinates> neibh = new ArrayList<DiscreteCoordinates>();
        int x = position.x; 
        int y = position.y;

        // I know, this is ugly. But is there really any best way to do this? at least, it's explicit. 
        if (x + 1 < width) neibh.add(new DiscreteCoordinates(x + 1, y)); 
        if (x - 1 >= 0) neibh.add(new DiscreteCoordinates(x - 1, y)); 
        if (y + 1 < height) neibh.add( new DiscreteCoordinates(x, y + 1)); 
        if (y - 1 >= 0) neibh.add(new DiscreteCoordinates(x, y - 1)); 

        return neibh; 
    }
    
    /**
     * Given a position, returns a list of the avaiable neibgors (i.e in the bounds and not visited yet)
     * Each node are spaced by one cell. 
     * @return
     */
    private List<DiscreteCoordinates> getAvailableNeibhors(DiscreteCoordinates position, boolean[][] visited) {
        List<DiscreteCoordinates> avNeibh = new ArrayList<DiscreteCoordinates>();
        int x = position.x;
        int y = position.y;

        // RIGHT
        if (x + 2 < this.width && !visited[x + 2][y]) {
            // generatedMap[x + 1][y] = SuperPacmanCellType.FREE_EMPTY;
            avNeibh.add(new DiscreteCoordinates(x + 2, y));
        }
        // LEFT
        if (x - 2 >= 0 && !visited[x - 2][y]) {
            // generatedMap[x - 1][y] = SuperPacmanCellType.FREE_EMPTY;
            avNeibh.add(new DiscreteCoordinates(x - 2, y));
        }
        // UP
        if (y + 2 < this.height && !visited[x][y + 2]) {
            // generatedMap[x][y + 1] = SuperPacmanCellType.FREE_EMPTY;
            avNeibh.add(new DiscreteCoordinates(x, y + 2));
        }
        // BOTTOM 
        if (y - 2 >= 0 && !visited[x][y - 2]) {
            // generatedMap[x][y - 1] = SuperPacmanCellType.FREE_EMPTY;
            avNeibh.add(new DiscreteCoordinates(x, y - 2));
        }
        return avNeibh;
    }
    
    private DiscreteCoordinates getIntermediatePosition(DiscreteCoordinates position, DiscreteCoordinates nextPosition) {
        Vector vectorMoving = nextPosition.toVector().sub(position.toVector());
        Vector intermidiatePos = position.toVector().add(vectorMoving.normalized());
        return new DiscreteCoordinates((int) intermidiatePos.x, (int) intermidiatePos.y);
    }
    
    private void connectTwoCell(DiscreteCoordinates from, DiscreteCoordinates to) {
        DiscreteCoordinates toRemoveClean = getIntermediatePosition(from, to);
        cleanCell(toRemoveClean); 
    }

    private void cleanCell(DiscreteCoordinates toClean) {
        // When cleaning a cell (i.e removing th wall)
        generatedMap[toClean.x][toClean.y] = this.fillingType;
    }
    
    /**
     * Check if i wall is at the end of a line of walls (i.e if it has exactly 3 neibhors that are not walls) 
     * @param toCheck Coordinate of the cell to check. Must be NOT AN EDGE OF THE GRID!
     * @return
     */
    private boolean isTipWall(DiscreteCoordinates toCheck) {
        int c = 0;
        for (DiscreteCoordinates neibhCoordinates : getNeibhors(toCheck)) {
            if (generatedMap[neibhCoordinates.x][neibhCoordinates.y] != SuperPacmanCellType.WALL) {
                c++;
            }
        }
        return c == 3;
    }
    /**
     * check wether a cell is at the edge of the map. 
     * @param toCheck
     * @return
     */
    private boolean isAtTheEdge(DiscreteCoordinates toCheck) {
        // System.out.println(toCheck.toString());
        // System.out.println((toCheck.x == 0 || toCheck.y == 0 || toCheck.x == width - 1 || toCheck.y == height - 1));
        return (toCheck.x == 0 || toCheck.y == 0 || toCheck.x == width - 1 || toCheck.y == height - 1);
    }
}
