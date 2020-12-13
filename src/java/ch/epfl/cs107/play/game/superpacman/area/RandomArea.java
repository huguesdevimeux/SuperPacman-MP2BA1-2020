package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.rpg.actor.Door;
import ch.epfl.cs107.play.game.superpacman.behavior.RandomBehavior;
import ch.epfl.cs107.play.game.superpacman.behavior.SuperPacmanBehavior;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.signal.logic.Logic;
import ch.epfl.cs107.play.window.Window;

public class RandomArea extends SuperPacmanArea {

	private int indexOfArea;
	private String nextArea;
	private RandomBehavior associatedBehavior;
	// this determines how many diamonds must be collected before the next gate is opened. 
	private float RATIO_DIAMONDS_TO_COLLECT = 0.70f; 
	
	public RandomArea(int indexOfArea, String nextArea) {
		super();
		this.indexOfArea = indexOfArea;
		this.nextArea = nextArea;
	}

	public int getIndexOfArea() {
		return indexOfArea;
	}


	@Override
	public String getTitle() {
		return "randomAreaLevel" + indexOfArea;
	}

	@Override
	public DiscreteCoordinates getSpawnLocation() {
		return new DiscreteCoordinates(1, 1);
	}

	@Override
	protected SuperPacmanBehavior getBehaviorTypeNewInstance(Window window) {
		associatedBehavior = new RandomBehavior(window, getTitle(), this.nextArea);
		return associatedBehavior; 
	}
	
	@Override
	protected void createArea() {
		super.createArea();
		for (DiscreteCoordinates pCoordinates : associatedBehavior.getDoorsPosition()) {
			this.registerActor(new Door(nextArea, new DiscreteCoordinates(2, 2), Logic.TRUE, this,
					Orientation.UP, new DiscreteCoordinates(pCoordinates.x, pCoordinates.y),
					new DiscreteCoordinates(6, 9)));
			// We create gate on top of the doors. 
			createGate(Orientation.LEFT, pCoordinates.x, pCoordinates.y, this);		
		}
	}

	/**
	 * The next level will be open if enough diamonds are collected. 
	 */
	@Override
	public boolean isOn() {
		return !(getCurrentDiamonds() < getOriginalNumberDiamonds() * RATIO_DIAMONDS_TO_COLLECT);
	}

	@Override
	public boolean isOff() {
		return false;
	}

	@Override
	public float getIntensity() {
		return 0;
	}
}
