package ch.epfl.cs107.play.game.rpg.actor;

import ch.epfl.cs107.play.game.actor.Entity;
import ch.epfl.cs107.play.game.actor.ImageGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Canvas;


public class LightHalo extends Entity {

    private final float DX;
    private final float DY;

    /// Sprite of the actor
    private final ImageGraphics sprite;

    /**
     * Default Foreground Constructor
     * by default the Background image is using the area title as file name
     * @param area (Area): ownerArea, not null
     */
    public LightHalo(Area area) {
        super(Vector.ZERO);
        DX = area.getCameraScaleFactor()/2;
        DY = area.getCameraScaleFactor()/2;
        final float side = area.getCameraScaleFactor();

        sprite = new ImageGraphics(ResourcePath.getForegrounds("lightHalo"), side, side, null, Vector.ZERO, 1.0f, 999);
    }

    /// Foreground implements Graphics

    @Override
    public void draw(Canvas canvas) {

        final Transform transform = Transform.I.translated(canvas.getPosition().sub(DX, DY));
        sprite.setRelativeTransform(transform);
        sprite.draw(canvas);
    }

}
