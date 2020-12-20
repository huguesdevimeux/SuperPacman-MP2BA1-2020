package ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;
//same class as PauseGUI but to handle game endings
public class GameOverGUI implements Graphics {
    private static final String FONT = "ArcadeClassic";
    private final float FONT_SIZE = 2.5f;
    //by default the game is not over
    public static boolean gameIsOver = false;
    private TextGraphics gameOverStatus;
    private TextGraphics gameOverStatus2;
	private int score;

    private Vector getCenter(Canvas canvas) {
        return canvas.getTransform().getOrigin()
                .add(new Vector(-canvas.getScaledWidth()/2, canvas.getScaledHeight() / 2));
    }

    private void drawGameOverStatus(Canvas canvas){
        gameOverStatus = new TextGraphics("Game Over", FONT_SIZE, Color.RED);
        gameOverStatus.setFontName(FONT);
        gameOverStatus.setAnchor(getCenter(canvas).add(new Vector(2,-7)));
        gameOverStatus.draw(canvas);
        gameOverStatus2 = new TextGraphics("press Q to quit", 1, Color.RED);
        gameOverStatus2.setFontName(FONT);
        gameOverStatus2.setAnchor(getCenter(canvas).add(new Vector(4,-8)));
        gameOverStatus2.draw(canvas);
    }

    private void drawScore(Canvas canvas) {
        TextGraphics gameOverScore = new TextGraphics("Score  " + Integer.toString(score), FONT_SIZE - 1.5f, Color.YELLOW);
        gameOverScore.setFontName(FONT);
        gameOverScore.setAnchor(getCenter(canvas).add(new Vector(5,-10)));
        gameOverScore.draw(canvas);
    }

    public void setGameIsOver(){
        gameIsOver = true;
    }

    public void setScore(int s) {
        this.score = s; 
    }
    @Override
    public void draw(Canvas canvas) {
        if (gameIsOver) {
            drawGameOverStatus(canvas);
            drawScore(canvas);
        }
    }
}
