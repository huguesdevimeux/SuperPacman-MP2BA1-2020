package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.superpacman.handler.SuperPacmanInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Inky extends smartMovingGhost {

    private int movingSpeed = 18;
    private final int MAX_DISTANCE_WHEN_SCARED = 5;
    private final int MAX_DISTANCE_WHEN_NOT_SCARED = 10;

    public Inky(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    @Override
    protected String getTitle() {
        return "superpacman/ghost.inky";
    }

    @Override
    protected int getSpeed() {
        return movingSpeed; 
    }
    
    @Override
    protected void updateFollowingPath() {
        if (isAfraid()) {
            // We don't update the followingPath when the ghost didn't reach its target.    
            if (hasReachedTargetPos()) setFollowingPathToTarget(getRandomReachablePositionWithinRadius(getRefugePosition(), MAX_DISTANCE_WHEN_SCARED));
        }
        // if there is a player memorized, and the player can't be reached (== the path corresponding can't be set), 
        // We generate a new path to around its refuge position. 
        else if (getMemorizedPlayer() == null) {
            if (hasReachedTargetPos()) setFollowingPathToTarget(getRandomReachablePositionWithinRadius(getRefugePosition(), MAX_DISTANCE_WHEN_NOT_SCARED));
        }
        // In some case the ghost can be on the same cell as the player (hasReachedTargetPos true), and we don't want a new random path in this case.
        else if (!setFollowingPathToTarget(getMemorizedPlayer().getCurrentCells().get(0)) && !hasReachedTargetPos()) {
            setFollowingPathToTarget(getRandomReachablePositionWithinRadius(getRefugePosition(), MAX_DISTANCE_WHEN_NOT_SCARED));        
        }
    }

}
