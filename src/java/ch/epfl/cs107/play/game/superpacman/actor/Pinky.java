package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

public class Pinky extends smartMovingGhost {
    private final int MIN_AFRAID_DISTANCE = 5;

    public Pinky(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    @Override
    protected void updateFollowingPath() {
        if (isAfraid()) {
            //once the ghost is afraid, we increase its speed
            increaseMovingSpeed();
            if (!hasReachedTargetPos())
                return;
            if (getMemorizedPlayer() != null) {
                DiscreteCoordinates candidatePos = getRandomReachablePositionAvoidingRadius(getMemorizedPlayer().getCurrentCells().get(0), MIN_AFRAID_DISTANCE);
                // if it is null, it means no random espace position has been found. We the don't touch the following path, which will cause the ghost to not move.
                if (candidatePos != null) {
                    setFollowingPathToTarget(candidatePos);
                }
            } else {
                setFollowingPathToTarget(getRandomReachablePosition());
            }
        } else if (getMemorizedPlayer() == null) {
            if (hasReachedTargetPos())
                setFollowingPathToTarget(getRandomReachablePosition());
        }
        // In some case the ghost can be on the same cell as the player (hasReachedTargetPos true, and setFollowingPathToTarget returns False), and we don't want a new random path in this case.
        // Note that the path going to the player will be updated regardless wether the target has been reached. 
        else if (!setFollowingPathToTarget(getMemorizedPlayer().getCurrentCells().get(0)) && !hasReachedTargetPos()) {
            setFollowingPathToTarget(getRandomReachablePosition());
        }
    }
    @Override
    protected String getTitle() {
        return "superpacman/ghost.pinky";
    }
    //returns moving speed in Ghost class
    @Override
    protected int getSpeed() {
        return movingSpeed;
    }

}
