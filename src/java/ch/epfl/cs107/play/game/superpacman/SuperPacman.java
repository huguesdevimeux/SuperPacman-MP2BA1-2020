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
import ch.epfl.cs107.play.game.superpacman.menu.WelcomeMenu;
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

        if (getCurrentArea().getClass() == WelcomeMenu.class) {
            if (((WelcomeMenu) getCurrentArea()).canQuit()) {
                startGame(((WelcomeMenu) getCurrentArea()).getSelectedOption());
            }
            // NOTE : We "manually" update the area, as super.update updates an RPG area. 
            getCurrentArea().update(deltaTime);
            return;
        }

        // RandomArea is a signal that is on when the player can go to the next area. 
        if (proceduralGamemode && ((RandomArea) getCurrentArea()).isOn() && infiniteLevel == ((RandomArea) getCurrentArea()).getLevel()) {
            infiniteLevel++;
            updateProceduralArea(infiniteLevel);
        }
        super.update(deltaTime);
    }

    private void startGame(int selectedGameMode) {
        switch (selectedGameMode) {
            case 0:
                createNonProceduralAreas();
                player = new SuperPacmanPlayer(setCurrentArea("superpacman/Level0", true), Orientation.UP, new DiscreteCoordinates(10, 1));
                initPlayer(player);
                break;
            case 1:
                addArea(new RandomArea(0));
                player = new SuperPacmanPlayer(setCurrentArea("randomAreaLevel0", true), Orientation.UP,
                        new DiscreteCoordinates(10, 1));
                initPlayer(player);
                proceduralGamemode = true; 
            default:
                break;
        }
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
            addArea(new WelcomeMenu());
            setCurrentArea("menu", true);
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
