package ch.epfl.cs107.play.game.superpacman.SuperPacmanGraphics;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.game.superpacman.actor.SuperPacmanPlayer;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;

/**
 * Handle status graphics for superpacman entities.
 */
public class SuperPacmanPlayerStatusGUI implements Graphics {
    // DEPTH determines the order of drawing. Low value means drawn in first. 
    private final float DEPTH = 0;
    private final float FONT_SIZE = 0.5f;
    private final float WIDTH_LIFE_UNIT = 1.0f;
	private int amountLife;
       // Set by default to -1 to easily debug.
	private int scoreDisplayed = -1; 

    private TextGraphics score;
    private TextGraphics scoreTitle;

    public SuperPacmanPlayerStatusGUI(SuperPacmanPlayer player) {
        assert (player.getClass() == SuperPacmanPlayer.class); 
    }

    /**
     * Draw a single life unit in the bar (i.e, one little pacman) on the left top of the screen. 
     * @param canvas
     * @param colorLife
     * @param unitPosition The position of the unit (ie first, second, etc..)
     */
    private void drawLifeUnit(Canvas canvas, Color colorLife, int unitPosition) {

        // This corresponds by default to yellow. 
        int offsetRegionInterest = 0;
        if (colorLife.equals(Color.GRAY)) {
            offsetRegionInterest = 64;
        }
        Vector anchorLeftTop = getLeftTopCorner(canvas);
        float offsetUnitLife = WIDTH_LIFE_UNIT * unitPosition;
        ImageGraphics life = new ImageGraphics(ResourcePath.getSprite("superpacman/lifeDisplay"), 1.f, 1.f,
                new RegionOfInterest(offsetRegionInterest, 0, 64, 64),
                anchorLeftTop.add(new Vector(offsetUnitLife,- 1.375f)), 1, DEPTH);
        life.draw(canvas);
    }

    /**
     * Draw all the life units in a row.
     * 
     * @param canvas
     * @param amountLife  Life remaining (between 0 and maxNmberUnitsLifePlayer)
     */
    private void drawLifeBar(Canvas canvas, int amountLife) {
        int maxNmberUnitsLifePlayer = 5; // TODO : modularize it into a getter for player. (so it can be changed ;)
        for (int i = 0; i < maxNmberUnitsLifePlayer; i++) {
            if (i >= amountLife) drawLifeUnit(canvas, Color.GRAY, i);
            else drawLifeUnit(canvas, Color.YELLOW, i);
        }

    }
    /**
     * Draw the score. ("Score ", and right after the score.)
     * @param canvas
     * @param scoreToDisplay
     */
    private void drawScore(Canvas canvas, int scoreToDisplay) {
        scoreTitle = new TextGraphics(String.valueOf("Score :"), FONT_SIZE, Color.YELLOW);
        scoreTitle.setOutlineColor(Color.RED);
        scoreTitle.setAnchor(getLeftTopCorner(canvas).add(new Vector(5 * WIDTH_LIFE_UNIT + 0.3f, -0.7f))); // TODo change this cf l.11
        scoreTitle.draw(canvas);
      
        score = new TextGraphics(String.valueOf(scoreToDisplay), FONT_SIZE, Color.YELLOW);
        score.setOutlineColor(Color.RED);
        score.setAnchor(getLeftTopCorner(canvas).add(new Vector(5 * WIDTH_LIFE_UNIT + 0.3f, - 1.25f))); // TODo change this to a getter cf l.11
        score.draw(canvas);
    }

    /**
     * Get the left-top corner position of the canva. 
     * @param canvas
     * @return A positional-vector. 
     */
    static private Vector getLeftTopCorner(Canvas canvas) {
        return canvas.getTransform().getOrigin()
                .add(new Vector(- canvas.getScaledWidth() / 2, canvas.getScaledHeight() / 2));
    }

    public void setAmountLife(int amountLife) {
        this.amountLife = amountLife;

    }

    public void setScore(int score) {
        this.scoreDisplayed = score;
    }
    
	@Override
    public void draw(Canvas canvas) {
        drawLifeBar(canvas, this.amountLife);
        drawScore(canvas, this.scoreDisplayed);		
	}
}
