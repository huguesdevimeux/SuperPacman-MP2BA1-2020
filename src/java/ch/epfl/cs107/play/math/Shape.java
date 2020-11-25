package ch.epfl.cs107.play.math;

import java.awt.geom.Path2D;

/**
 * Base class of all physical shapes.
 */
public abstract class Shape {
        
    /** @return shape area */
    public abstract float getArea();
    
    /** @return shape perimeter */
    public abstract float getPerimeter();
    
    // methods for bounding box handling can be added here if needed
    
    // methods applying transform (or simple translation, scale, rotation) to shape
    // can be added if needed
    
    /**
     * Sample uniform point inside shape, including border.
     * @return (Vector): a uniform sample, not null
     */
    public abstract Vector sample();
    
    /** @return (Path2D): AWT path used for drawing */
    public abstract Path2D toPath();
}
