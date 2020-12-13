package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.RandomArea;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG {

    //adding this constants to use later on
    public final static float CAMERA_SCALE_FACTOR = 15.f;
    private SuperPacmanPlayer player;

    //initalising player using method from super class
    @Override
    protected void initPlayer(Player player) {
        super.initPlayer(player);
    }
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
    private void createAreas() {
        addArea(new Level0());
        addArea(new Level1());
        addArea(new Level2());
        RandomArea randomArea2 = new RandomArea(2, "superpacman/Level0"); //is normal that nextArea is level0??
        RandomArea randomarea1 = new RandomArea(1, randomArea2.getTitle());
        addArea(randomarea1);
        addArea(randomArea2);
    }

    public boolean begin(Window window, FileSystem fileSystem) {

        if (super.begin(window, fileSystem)) {
            createAreas();
            Area area = setCurrentArea("superpacman/Level1", true);
            player = new SuperPacmanPlayer(area, Orientation.UP, new DiscreteCoordinates(10, 1)); // TODO : change spawn pos with the guetteur ARAH ARAH Y A LES KEUFS
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
