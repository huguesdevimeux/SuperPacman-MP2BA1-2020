package ch.epfl.cs107.play.game.rpg.actor;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.handler.RPGInteractionVisitor;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Polyline;
import ch.epfl.cs107.play.math.Transform;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Canvas;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Door extends AreaEntity{

	/// Door Debug flag. Door is an invisible Entity. When debug, we draw something visible
	private static final boolean DEBUG_DOOR = false;

	/// Debug variable : useful only for drawing door debug shape
	private Polyline debugSquare;
	private final List<DiscreteCoordinates> currentCells;

	/// Name of the destination area
	private final String destination;
	/// Coordinates of the other side
	private final DiscreteCoordinates otherSideCoordinates;
	/// Signal conditioning the door
	private Logic signal;

	/**
	 * Default Door constructor
	 * @param destination        (String): Name of the destination area, not null
	 * @param otherSideCoordinates (DiscreteCoordinate):Coordinates of the other side, not null
	 * @param signal (Logic): LogicGate signal opening the door, may be null
	 * @param area        (Area): Owner area, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 */
	public Door(String destination, DiscreteCoordinates otherSideCoordinates, Logic signal, Area area, Orientation orientation, DiscreteCoordinates position) {
		super(area, orientation, position);
		this.destination = destination;
		this.otherSideCoordinates = otherSideCoordinates;
		this.signal = signal;
		this.currentCells = new ArrayList<>();
		this.currentCells.add(position);
	}

	/**
	 * Complementary Door constructor
	 * @param destination        (String): Name of the destination area, not null
	 * @param otherSideCoordinates (DiscreteCoordinate):Coordinates of the other side, not null
	 * @param signal (Logic): LogicGate signal opening the door, may be null
	 * @param area        (Area): Owner area, not null
	 * @param position    (DiscreteCoordinate): Initial position of the entity, not null
	 * @param orientation (Orientation): Initial orientation of the entity, not null
	 * @param otherCells (DiscreteCoordinates...): Other cells occupied by the AreaEntity if any. Assume absolute coordinates, not null
	 */
	public Door(String destination, DiscreteCoordinates otherSideCoordinates, Logic signal, Area area, Orientation orientation, DiscreteCoordinates position, DiscreteCoordinates... otherCells) {
		this(destination, otherSideCoordinates, signal, area, orientation, position);
		this.currentCells.addAll(Arrays.asList(otherCells));
	}

	/**
	 * Getter for the door's Destination
	 * @return (String)
	 */
	public String getDestination(){
		return destination;
	}

	/**
	 * Getter for the door's other side coordinates
	 * @return (DiscreteCoordinates)
	 */
	public DiscreteCoordinates getOtherSideCoordinates() {
		return otherSideCoordinates;
	}

	/// Door extends AreaEntity

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return currentCells;
	}

	/// Door implements Graphics

	@Override
	public void draw(Canvas canvas) {

		if(DEBUG_DOOR){

			if(debugSquare == null){
				List<Vector> points = new ArrayList<>();

				// Add all vertical lines as a snake from top left to top/bottom right
				points.add(new Vector(0, 0));
				points.add(new Vector(1, 0));
				points.add(new Vector(1, 1));
				points.add(new Vector(0, 1));

				// Add all horizontal lines as a snake
				// Convert the point into a opened poly line
				debugSquare = new Polyline(true, points);

			}
			canvas.drawShape(debugSquare, Transform.I.transformed(getTransform()), Color.RED, null, 0.1f, 1.0f, 100);

			if(currentCells != null){
				for (DiscreteCoordinates aPositionForDebug : currentCells) {
					canvas.drawShape(debugSquare, Transform.I.translated(aPositionForDebug.x, aPositionForDebug.y), Color.RED, null, 0.1f, 1.0f, 100);
				}
			}
		}
	}

	public boolean isOpen() {
		return signal.isOn();
	}

	protected void setSignal(Logic signal){
		this.signal = signal;
	}

	/// Door Implements Interactable

	@Override
	public boolean takeCellSpace() {
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return signal == null || signal.isOn();
	}

	@Override
	public boolean isViewInteractable(){
		return false;
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
		((RPGInteractionVisitor)v).interactWith(this);
	}
}
