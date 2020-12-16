package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RandomGenerator;
import ch.epfl.cs107.play.window.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public abstract class smartMovingGhost extends Ghost {

    private SuperPacmanPlayer memorizedPlayer;
    private DiscreteCoordinates targetPos;
    private Queue<Orientation> followingPath;
    private smartMovingGhostHandler handler = new smartMovingGhostHandler();
    /**
     * Update the following path according to the ghost type rules.
     */
    protected abstract void updateFollowingPath();  

    public smartMovingGhost(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }
    
    @Override
    protected Orientation getNextOrientation() {
        if (getCurrentMainCellCoordinates().equals(targetPos) || (followingPath == null)) {
            // Means that the ghost have reached the target, or that it can go to the target.
            return null;
        }
        return followingPath.poll();
    }
    
    protected void memorizePlayer(SuperPacmanPlayer player) {
        this.memorizedPlayer = player;
    }

    protected void forgetPlayer() {
        this.memorizedPlayer = null; 
    }

    protected SuperPacmanPlayer getMemorizedPlayer() {
        return this.memorizedPlayer;
    }

    @Override
    public void setAfraidState() {
        this.targetPos = null;
        super.setAfraidState();
    }
    @Override
    public void setNormalState() {
        this.targetPos = null;
        super.setNormalState();
    }
    
    @Override
    public void resetGhost() {
        super.resetGhost();
        forgetPlayer();
    }
    
    /**
     * Compute and set the following path of the ghost to the target. return false and does not change 
     * the attribute if the target is unreachable! 
     * @param targetPos
     * @return
     */
    protected boolean setFollowingPathToTarget(DiscreteCoordinates targetPos) {
        Queue<Orientation> tempPath = ((SuperPacmanArea) getOwnerArea()).shortestPath(getCurrentMainCellCoordinates(),
                targetPos);
        if (tempPath == null) {
            return false;
        }
        this.targetPos = targetPos;
        this.followingPath = tempPath;
        return true;
    }

    protected boolean hasReachedTargetPos() {
        return (targetPos == null || this.getCurrentMainCellCoordinates().equals(targetPos));
    }
    
    
    /**
     * Util to get all the cells within a distance radius of the center. 
     * @param center
     * @param radius
     * @return
     */
    private List<DiscreteCoordinates> getAllCellsWithinCircle(DiscreteCoordinates center, int radius) {
        List<DiscreteCoordinates> insideCircle = new ArrayList<DiscreteCoordinates>();
        DiscreteCoordinates testedPos;
        for (int x = -radius; x < radius; x++) {
            for (int y = -radius; y < radius; y++) {
                testedPos = new DiscreteCoordinates(center.x + x, center.y + y);
                if (DiscreteCoordinates.distanceBetween(center, testedPos) <= radius) {
                    insideCircle.add(testedPos);
                }
            }
        }
        return insideCircle;
    }

    /**
     * Get a random reachable case, within or in circle of radius and center defined.
     * 
     * @param center
     * @param radius
     * @return
     */
    protected DiscreteCoordinates getRandomReachablePositionWithinRadius(DiscreteCoordinates center, int radius) {
        List<DiscreteCoordinates> cellsWithinCircle = getAllCellsWithinCircle(center, radius);
        DiscreteCoordinates testedPos;
        boolean canBeReached = false;
        
        do {
            testedPos = cellsWithinCircle.get(RandomGenerator.getInstance().nextInt(cellsWithinCircle.size() - 1));
            canBeReached = ((SuperPacmanArea) getOwnerArea()).shortestPath(getCurrentMainCellCoordinates(), testedPos) != null;    
        } while (!canBeReached);

        return testedPos;
    }

    /**
     * Get a random cellule reachable outside or in a circle of radius and center definied. The position is reachable from origin. If not found, return null.
     * 
     * @param center
     * @param radius
     * @return
     */
    protected DiscreteCoordinates getRandomReachablePositionAvoidingRadius(DiscreteCoordinates center, int radius) {

        DiscreteCoordinates testedPos;
        boolean canBeReached = false;
        int count = 0;
        int THRESHOLD_NOT_FOUND = 20000;
        DiscreteCoordinates currentPos = getCurrentMainCellCoordinates();
        do {
            if (count++ > THRESHOLD_NOT_FOUND) {
                System.out.println(this.toString() + "Can't find a suitable location near " + center.toString()
                        + " outside radius of " + radius);
                return null;
            }
            // To get a random position, we compute a random shift in the y and x axis.
            // For optimisation sake, we know that all the points contained in the radius
            // are outside a square of length floor(radius).
            int testedX = RandomGenerator.getInstance().nextInt(getOwnerArea().getHeight());
            int testedY = RandomGenerator.getInstance().nextInt(getOwnerArea().getHeight());
            testedPos = new DiscreteCoordinates(testedX, testedY);
            Queue<Orientation> shortestPath = ((SuperPacmanArea) getOwnerArea()).shortestPath(currentPos, testedPos);
            canBeReached = shortestPath != null;
        } while ((DiscreteCoordinates.distanceBetween(center, testedPos) < radius) || !canBeReached);
        return testedPos;
    }
    
    /**
     * Return a random reachable position within the area. If find none, return none.
     * @return
     */
    protected DiscreteCoordinates getRandomReachablePosition() {
        DiscreteCoordinates testedPos;
        boolean canBeReached = false;
        int count = 0;
        int THRESHOLD_NOT_FOUND = 200;
        DiscreteCoordinates currentPos = getCurrentMainCellCoordinates();
        do {
            if (count++ > THRESHOLD_NOT_FOUND) {
                return null;
            }
            // To get a random position, we compute a random shift in the y and x axis.
            // For optimisation sake, we know that all the points contained in the radius
            // are outside a square of length floor(radius).
            int testedDx = RandomGenerator.getInstance().nextInt(getOwnerArea().getHeight());
            int testedDy = RandomGenerator.getInstance().nextInt(getOwnerArea().getHeight());
            testedPos = currentPos.jump(testedDx, testedDy);
            canBeReached = ((SuperPacmanArea) getOwnerArea()).shortestPath(currentPos, testedPos) != null;
        } while (!canBeReached);
        
        return testedPos;
    }

    //TODO REMOVE?????
    // FOR DEBUG PURPOSES ! 
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    public void update(float deltaTime) {
        updateFollowingPath();
        super.update(deltaTime);
    }

    private class smartMovingGhostHandler extends GhostHandler implements SuperPacmanInteractionVisitor {
        public void interactWith(SuperPacmanPlayer player) {
            super.interactWith(player);
            if (getMemorizedPlayer() == null || getMemorizedPlayer() != player)
                memorizePlayer(player);
        }
    }
    @Override
    public void interactWith(Interactable other) {
        other.acceptInteraction(handler);
    }
}
