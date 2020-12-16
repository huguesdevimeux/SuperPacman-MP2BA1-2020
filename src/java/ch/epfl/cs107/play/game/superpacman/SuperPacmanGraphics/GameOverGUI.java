package ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;
//same class as PauseGUI but to handle game endings
public class GameOverGUI implements Graphics {
    private final float FONT_SIZE = 2.5f;
    //by default the game is not over
    public static boolean gameIsOver = false;
    private TextGraphics gameOverStatus;
    private TextGraphics gameOverStatus2;

    private Vector getCenter(Canvas canvas) {
        return canvas.getTransform().getOrigin()
                .add(new Vector(-canvas.getScaledWidth()/2, canvas.getScaledHeight() / 2));
    }

    private void drawGameOverStatus(Canvas canvas){
        gameOverStatus = new TextGraphics("Game Over", FONT_SIZE, Color.RED);
        gameOverStatus.setFontName("ArcadeClassic");
        gameOverStatus.setAnchor(getCenter(canvas).add(new Vector(2,-7)));
        gameOverStatus.draw(canvas);
        gameOverStatus2 = new TextGraphics("press Q to quit", 1, Color.RED);
        gameOverStatus2.setFontName("ArcadeClassic");
        gameOverStatus2.setAnchor(getCenter(canvas).add(new Vector(4,-8)));
        gameOverStatus2.draw(canvas);
    }

    public void setGameIsOver(){
        gameIsOver = true;
    }
    @Override
    public void draw(Canvas canvas) {
        if(gameIsOver) drawGameOverStatus(canvas);
    }
}
