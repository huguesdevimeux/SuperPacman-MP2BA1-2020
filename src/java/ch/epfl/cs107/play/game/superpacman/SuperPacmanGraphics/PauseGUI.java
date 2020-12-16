package ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanArea;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;

public class PauseGUI implements Graphics {
   private final float FONT_SIZE = 2.f;
   public static boolean gameIsPaused = false;
   private TextGraphics pauseStatus;
   private TextGraphics pauseStatus2;

   private Vector getCenter(Canvas canvas) {
        return canvas.getTransform().getOrigin()
                .add(new Vector(-canvas.getScaledWidth()/2, canvas.getScaledHeight() / 2));
    }

        private void drawPauseStatus(Canvas canvas){
            pauseStatus = new TextGraphics("PAUSE", FONT_SIZE, Color.RED);
            pauseStatus.setFontName("ArcadeClassic");
            pauseStatus.setAnchor(getCenter(canvas).add(new Vector(5, -7)));
            pauseStatus.draw(canvas);
            pauseStatus2 = new TextGraphics("press p to play", 1, Color.RED);
            pauseStatus2.setFontName("ArcadeClassic");
            pauseStatus2.setAnchor(getCenter(canvas).add(new Vector(4.2f, -8)));
            pauseStatus2.draw(canvas);
        }

 public void setPause(){
       gameIsPaused = true;
 }
 public void setPlay(){
       gameIsPaused = false;
 }

@Override
    public void draw(Canvas canvas) {
    if(gameIsPaused) drawPauseStatus(canvas);
    }
}
