package ch.epfl.cs107.play.game.superpacman;

import java.util.Random;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.RandomArea;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG {

    //adding this constants to use later on
    public final static float CAMERA_SCALE_FACTOR = 15.f;
    private SuperPacmanPlayer player;
    // Infinite game mode means that there is always a new map after the current (procedurally generated).
    private boolean proceduralGamemode = false;
    private int infiniteLevel = 0;

    //initalising player using method from super class
    @Override
    protected void initPlayer(Player player) {
        super.initPlayer(player);
    }

    public void update(float deltaTime) {
        // RandomArea is a signal that is on when the player can go to the next area. 
        if (proceduralGamemode && ((RandomArea) getCurrentArea()).isOn() && infiniteLevel == ((RandomArea) getCurrentArea()).getLevel()) {
            infiniteLevel++;
            updateProceduralArea(infiniteLevel);
        }
            super.update(deltaTime);
    }

    private void createNonProceduralAreas() {
        addArea(new Level0());
        addArea(new Level1());
        addArea(new Level2());
    }

    /**
     * Dynamicaly add and remove area, to allow the "infinite" gamemode.
     * @param nextLevel index of the next level. 
     */
    private void updateProceduralArea(int nextLevel) {
        addArea(new RandomArea(nextLevel));
        // TODO (LOW) : Purge past areas. 
        
    }

    public boolean begin(Window window, FileSystem fileSystem) {

        if (super.begin(window, fileSystem)) {
            SuperPacmanArea startingArea; 
            if (!proceduralGamemode) {
                createNonProceduralAreas();
                startingArea = (SuperPacmanArea) setCurrentArea("superpacman/Level0", true);
            }
            else {
                addArea(new RandomArea(0));
                startingArea = (SuperPacmanArea) setCurrentArea("randomAreaLevel0", true);
            }
            player = new SuperPacmanPlayer(startingArea, Orientation.UP, startingArea.getSpawnLocation()); // TODO : change spawn pos with the guetteur ARAH ARAH Y A LES KEUFS
            initPlayer(player);
            return true;
        } else return false;
    }

    @Override
    public void end() {}
    protected void switchArea() {}

    public String getTitle() {
        return "Super Pac-Man";
    }
}
