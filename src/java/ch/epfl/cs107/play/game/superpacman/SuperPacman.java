package ch.epfl.cs107.play.game.superpacman;

import ch.epfl.cs107.play.game.actor.Actor;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.RPG;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.game.superpacman.area.Level2;
import ch.epfl.cs107.play.game.tutosSolution.actor.GhostPlayer;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacman extends RPG {

    public final DiscreteCoordinates[]  PLAYER_SPAWN_POSITION = {new DiscreteCoordinates(10,1),new DiscreteCoordinates(15,6), new DiscreteCoordinates(15,29)};
    //adding these two constants to use later on
    public final static float CAMERA_SCALE_FACTOR = 15.f;
    public final static float STEP = 0.05f;

    //areaIndex will denote the areas level0, level1, level2
    private int areaIndex;
    private SuperPacman player;
    private final String[] areas = {"superpacman/Level0", "superpacman/Level1", "superpacman/ Level2"};

    //initalising player using method from super class
    @Override //je laisse?
    protected void initPlayer(Player player) {
        super.initPlayer(player);
    }

    public void update(float deltaTime) {
        super.update(deltaTime);
    }

    private void createAreas(){

    }

    public boolean begin(Window window, FileSystem fileSystem){

        if (super.begin(window, fileSystem)){
            createAreas();
            //the player will begin at level0, hence areaIndex = 0
            areaIndex = 0;
            Area area = setCurrentArea(areas[areaIndex], true);
            //TODO complete method
            player = new SuperPacmanPlayer(getCurrentArea(), Orientation.DOWN, PLAYER_SPAWN_POSITION[areaIndex] );
            area.registerActor((Actor) player);
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
