package ch.epfl.cs107.play.game.superpacman.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Audio;
import ch.epfl.cs107.play.window.Canvas;

import java.util.Collections;
import java.util.List;

public class Gate extends AreaEntity {
    private Sprite gate;
    private Logic signal;

    /**
     * Gate constructor that will enable the creation of gates on the
     * different levels
     *
     * @param area        (Area): Owner area. Not null
     * @param orientation (Orientation): Initial orientation of the entity in the Area. Not null
     * @param position    (DiscreteCoordinate): Initial position of the entity in the Area. Not null
     */
    public Gate(Area area, Orientation orientation, DiscreteCoordinates position, Logic signal) {
        super(area, orientation, position);
        this.signal = signal;
        if (orientation == Orientation.DOWN || orientation == Orientation.UP) {
            extractGates(0, 0, 64, 64);
        } else {
            extractGates(0, 64, 64, 64);
        }
    }

    //method that will "extract gates" and will vary with orientation
    public void extractGates(int x, int y, int w, int h) {
        gate = new Sprite("superpacman/gate", 1.f, 1.f,
                this, new RegionOfInterest(x, y, w, h));
    }

    @Override
    public void bip(Audio audio) {
    }

     /**
      @return true only if the signal is on
     */
    @Override
    public void draw(Canvas canvas) {
        if (signal.isOn()) gate.draw(canvas);
    }

    @Override
    public List<DiscreteCoordinates> getCurrentCells() {
        return Collections.singletonList((getCurrentMainCellCoordinates()));
    }

    /**
     @return true only if the signal is on
     */
    @Override
    public boolean takeCellSpace() {
        return signal.isOn();
    }

    //interactions are not taken into account so both types return false
    @Override
    public boolean isCellInteractable() {
        return false;
    }

    @Override
    public boolean isViewInteractable() {
        return false;
    }

    @Override
    public void acceptInteraction(AreaInteractionVisitor v) {
    }

}
