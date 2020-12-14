package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.math.DiscreteCoordinates;

/*
this ghost will appear in Level2 only
this ghost is brave, it doesn't get afraid and doesn't have a refuge
 so it constantly torments the player in Level2
*/
public class Spooky extends smartMovingGhost {

    public Spooky(Area area, Orientation orientation, DiscreteCoordinates position) {
        super(area, orientation, position);
    }

    @Override
    protected void updateFollowingPath() {

        //don't really know what to put here - kinda inspired from inky
        //but removed the part is afraid (it works well but idk if i have to add/remove stuff

         if (getMemorizedPlayer() == null) {
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
        return "superpacman/ghost.spooky";
    }
    //this ghost will go at constant speed and will constantly chase the player
    @Override
    protected int getSpeed() {
        return 12;
    }
}
