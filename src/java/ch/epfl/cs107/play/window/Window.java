package ch.epfl.cs107.play.window;

import ch.epfl.cs107.play.math.Attachable;

/**
 * Represents a context frame, which can act as a canvas.
 * Moreover, the camera can be attached to any positionable entity.
 */
public interface Window extends Canvas, Audio, Attachable {
    
    /** @return (Button): whether the windows is active */
    Button getFocus();
    
    /** @return (Mouse): associated mouse controller */
    Mouse getMouse();
    
    /** @return (Keyboard): associated keyboard controller */
    Keyboard getKeyboard();
    
    // handling gamepads can be added if wanted
        
    /** @return (boolean): whether the user tried to close the window */
    boolean isCloseRequested();
    
    // additional update with delta time parameter can be added if wanted
    // e.g. for mouse interpolation
    void update();
    
    /** Destroys and closes the window */
    void dispose();
}
