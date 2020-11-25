package ch.epfl.cs107.play.game.rpg.actor;

import ch.epfl.cs107.play.game.actor.Graphics;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.TextAlign;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;


public class Dialog implements Graphics {

    /// Static variables for positioning the text and the dialog window
    private static final float FONT_SIZE = 0.6f;

    /// Number max of char per line of text
    private static final int MAX_LINE_SIZE = 35;

    /// Sprite and text graphics line
    private final ImageGraphics sprite;
    private final TextGraphics[] lines;

    /// Full Text of the dialog
    private String text;
    /// Index of the next char to print
    private int cursor;

    /**
     * Default Dialog Constructor
     * @param text (String): string of the dialog, not null
     * @param backgroundName (String): Background file name (i.e only the name, with neither path, nor file extension), may be null
     * @param area (Area): this owner area to compute scale factor ratios, not null
     */
    public Dialog(String text, String backgroundName, Area area) {
        final float height = area.getCameraScaleFactor() / 4;
        final float width = area.getCameraScaleFactor();

        final Vector firstLineAnchor = new Vector(0.5f, height - FONT_SIZE);
        final Vector secondLineAnchor = new Vector(0.5f, height - 2.5f * FONT_SIZE);
        final Vector thirdLineAnchor = new Vector(0.5f, height - 4 * FONT_SIZE);

        sprite = new ImageGraphics(ResourcePath.getSprite(backgroundName), width, height, null, Vector.ZERO, 1.0f, 3000);

        lines = new TextGraphics[3];

        lines[0] = new TextGraphics("", FONT_SIZE, Color.BLACK, null, 0.0f, false, false, firstLineAnchor, TextAlign.Horizontal.LEFT, TextAlign.Vertical.MIDDLE,  1.0f, 3001);
        lines[1] = new TextGraphics("", FONT_SIZE, Color.BLACK, null, 0.0f, false, false, secondLineAnchor, TextAlign.Horizontal.LEFT, TextAlign.Vertical.MIDDLE, 1.0f, 3001);
        lines[2] = new TextGraphics("", FONT_SIZE, Color.BLACK, null, 0.0f, false, false, thirdLineAnchor, TextAlign.Horizontal.LEFT, TextAlign.Vertical.MIDDLE, 1.0f, 3001);

        resetDialog(text);
    }

    /**
     * Reset the dialog window with a new dialog text
     * @param newText (String), not null
     */
    public void resetDialog(String newText){
        this.text = newText;
        this.cursor = 0;
        push();
    }

    /**
     * From the cursor display on the three available lines the next chars of the stream
     * Notice: this method assume it will never face to single word longer or equals than MAX_LINE_SIZE
     * @return (boolean) if the dialog can be closed or not
     */
    public boolean push(){

        int lengthToPush = text.length()-cursor;
        // Simply close the dialog
        if(lengthToPush <= 0)
            return true;

        // For each line
        for(int i = 0; i<3; i++){

            // If some text still need to be pushed : fill the next line
            if(lengthToPush <= 0) {
                lines[i].setText("");
            }
            else if(lengthToPush <= MAX_LINE_SIZE) {
                lines[i].setText(text.substring(cursor));
                cursor += lengthToPush;
            }
            else{
                int maxSize = MAX_LINE_SIZE;
                String toConcat = "";

                if(i == 2){
                    maxSize -= 4;
                    toConcat += " ...";
                }

                String sub = text.substring(cursor, cursor+maxSize+1);
                int last = sub.lastIndexOf(' ');
                if(last == -1){
                    System.out.println("Error: You get a Word longer than " + MAX_LINE_SIZE);
                }

                lines[i].setText(sub.substring(0, last)+toConcat);
                cursor = cursor+last+1;
            }

            lengthToPush = text.length()-cursor;
        }
        return false;
    }


    /// Dialog implements Graphics

    @Override
    public void draw(Canvas canvas) {
    	// Compute width, height and anchor
		float width = canvas.getTransform().getX().getX();
		float height = canvas.getTransform().getY().getY();
		
		float ratio = canvas.getWidth()/(float)canvas.getHeight();
		if(ratio > 1)
			height = width / ratio;
		else
			width = height * ratio;
    	
        final Transform transform = Transform.I.translated(canvas.getPosition().sub(width/2, height/2));
        sprite.setRelativeTransform(transform);
        sprite.setWidth(width);
        sprite.setHeight(height/4);
        sprite.draw(canvas);
        
        lines[0].setAnchor(new Vector(0.5f, height/4 - FONT_SIZE));
        lines[1].setAnchor(new Vector(0.5f, height/4 - 2.5f * FONT_SIZE));
        lines[2].setAnchor(new Vector(0.5f, height/4 - 4 * FONT_SIZE));
        for(int i = 0; i < 3; i++){
            lines[i].setRelativeTransform(transform);
            lines[i].draw(canvas);
        }
    }
}
