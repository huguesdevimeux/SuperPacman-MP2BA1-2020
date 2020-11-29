package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level0;
import ch.epfl.cs107.play.game.superpacman.area.Level1;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.game.tutosSolution.actor.GhostPlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG {

    //adding these two constants to use later on
    public final static float CAMERA_SCALE_FACTOR = 15.f;
    public final static float STEP = 0.05f;

    //areaIndex will denote the areas level0, level1, level2
    private int areaIndex;
    private SuperPacmanPlayer player;
    private final String[] areas = {"superpacman/Level0", "superpacman/Level1", "superpacman/ Level2"};

    //initalising player using method from super class
    @Override //je laisse?
    protected void initPlayer(Player player) {
        super.initPlayer(player);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    private void createAreas() {
        addArea(new Level0());

    }

    public boolean begin(Window window, FileSystem fileSystem){

        if (super.begin(window, fileSystem)) {
            createAreas();
            //the player will begin at level0, hence areaIndex = 0
            areaIndex = 0;
            Area area = setCurrentArea(areas[areaIndex], true); // TODO : cast? 
            player = new SuperPacmanPlayer(area, Orientation.UP, new DiscreteCoordinates(5, 5));
            initPlayer(player);
            return true;
        }
        else return false;
    }


   @Override
   public void end(){
   }

   protected void switchArea(){

   }

    public String getTitle() {
        return "Super Pac-Man";
    }
}
