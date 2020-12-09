package ch.epfl.cs107.play.game.superpacman.area;

import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.AreaGraph;
import ch.epfl.cs107.play.game.areagame.Cell;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Blinky;
import ch.epfl.cs107.play.game.superpacman.actor.Ghost;
import ch.epfl.cs107.play.game.superpacman.actor.Inky;
import ch.epfl.cs107.play.game.superpacman.actor.Pinky;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior {


    public enum SuperPacmanCellType {
        NONE(0), // never used as real content
        WALL(-16777216), //black
        FREE_WITH_DIAMOND(-1), //white
        FREE_WITH_BLINKY(-65536), //red
        FREE_WITH_PINKY(-157237), //pink
        FREE_WITH_INKY(-16724737), //cyan
        FREE_WITH_CHERRY(-36752), //light red
        FREE_WITH_BONUS(-16478723), //light blue
        FREE_EMPTY(-6118750); // sort of gray

        final int type;

        SuperPacmanCellType(int type) {
            this.type = type;
        }

        public static SuperPacmanCellType toType(int type) {
            for (SuperPacmanCellType ict : SuperPacmanCellType.values()) {
                if (ict.type == type)
                    return ict;
            }
            // When you add a new color, you can print the int value here before assign it to a type
            System.out.println(type);
            return NONE;
        }
    }
   
    private AreaGraph associatedAreaGraph;
    private List<Ghost> ghostsInGrid = new ArrayList<Ghost>(); 

    /**
     * Default SuperPacmanBehavior Constructor
     *
     * @param window (Window), not null
     * @param name   (String): Name of the Behavior, not null
     */
    public SuperPacmanBehavior(Window window, String name) {
        super(window, name);
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                // Generate a behavior for each cell. 
                SuperPacmanCellType color = SuperPacmanCellType.toType(getRGB(getHeight() - 1 - y, x));
                setCell(x, y, new SuperPacmanCell(x, y, color));
            }
        }

        associatedAreaGraph = new AreaGraph();
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                associatedAreaGraph.addNode(new DiscreteCoordinates(x, y), !isWall(x - 1, y), !isWall(x, y + 1),
                        !isWall(x + 1, y), !isWall(x, y - 1));
            }
        }
    }
    
    /**
     * Check whether the cell at x,y is wall. By default, if it is out of the boudaries, IT IS a wall.
     * 
     * @param x coordinate
     * @param y coordinate
     * @return true if the cell at (x,y) is a wall
     */
    public boolean isWall(int x, int y){
        if(x >= 0 && y >= 0 && y < getHeight() && x < getWidth()){
            return ((SuperPacmanCell)getCell(x,y)).type == SuperPacmanCellType.WALL;
        } else {
            return true;
        }
    }
    
    /**
     * Generate walls for the behavior. 
     * 
     * @param area The area containing the walls.  
     */
    protected void registerActors(SuperPacmanArea area) {
        Ghost addedGhost;
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (isWall(x, y)) {
                    area.registerActor(new Wall(area, new DiscreteCoordinates(x, y), getNeighborhood(x, y)));
                } 
                else if (((SuperPacmanCell) getCell(x, y)).type == SuperPacmanCellType.FREE_WITH_BLINKY) {
                    addedGhost = new Blinky(area, Orientation.UP, new DiscreteCoordinates(x, y));
                    area.registerActor(addedGhost);
                    ghostsInGrid.add(addedGhost); 
                }
                else if (((SuperPacmanCell) getCell(x, y)).type == SuperPacmanCellType.FREE_WITH_INKY) {
                    addedGhost = new Inky(area, Orientation.UP, new DiscreteCoordinates(x, y));
                    area.registerActor(addedGhost);
                    ghostsInGrid.add(addedGhost);
                }
                else if (((SuperPacmanCell) getCell(x, y)).type == SuperPacmanCellType.FREE_WITH_PINKY) {
                    addedGhost = new Pinky(area, Orientation.UP, new DiscreteCoordinates(x, y));
                    area.registerActor(addedGhost);
                    ghostsInGrid.add(addedGhost);
                }
            }
        }
    }

    /**
     * @param x coordinate
     * @param y coordinate
     * @return whether the wall's surrounding cells are walls as well
     */
    private boolean[][] getNeighborhood(int x, int y) {

        boolean[][] neighborhood = new boolean[3][3];
        //the cell at coordinates 1,1 (the center of the 3x3 array)
        // is true as we study its surroundings
        neighborhood[1][1] = true;
        neighborhood[0][0] = isWall(x-1, y+1);
        neighborhood[2][2] = isWall(x+1, y-1);
        neighborhood[2][1] = isWall(x+1, y);
        neighborhood[0][1] = isWall(x-1,y);
        neighborhood[1][2] = isWall(x,y-1);
        neighborhood[1][0] = isWall(x,y+1);
        neighborhood[0][2] = isWall(x-1,y-1);
        neighborhood[2][0] = isWall(x+1,y+1);
        return neighborhood;
    }
    /**
     * Get the graph associated with the behavior. 
     * @return Areagraph corresonding to the behavior.
     */
    public AreaGraph getGraph() {
        return associatedAreaGraph;
    }
    
    /**
     * Set the state of all the ghosts within the grid to afraid. 
     */
    public void scareGhosts() {
        for (Ghost ghost : ghostsInGrid) {
            ghost.setAfraidState();
        }
    }


    /**
    * Set the state of all the ghosts within the grid to normal. 
     */ 
    public void calmGhosts() {
        for (Ghost ghost : ghostsInGrid) {
            ghost.setNormalState();
        }
    }

    /**
     * Represents a cell of the game.
     */
    public class SuperPacmanCell extends Cell {
        /// Type of the cell following the enum
        private final SuperPacmanCellType type;

        /**
         * Default SuperPacmanCell Constructor
         *
         * @param x    (int): x coordinate of the cell
         * @param y    (int): y coordinate of the cell
         * @param type (EnigmeCellType), not null
         */
        public SuperPacmanCell(int x, int y, SuperPacmanCellType type) {
            super(x, y);
            this.type = type;
        }

        public boolean isWall() {
            return type.equals(SuperPacmanCellType.WALL);
        }

        @Override
        protected boolean canLeave(Interactable entity) {
            return true;
        }

        protected boolean canEnter(Interactable entity) {
            return !hasNonTraversableContent(); 
        }

        @Override
        public boolean isCellInteractable() {
            return false;
        }

        @Override
        public boolean isViewInteractable() {
            return false;
        }

        @Override
        public void acceptInteraction(AreaInteractionVisitor v) {
        }
    }
}
