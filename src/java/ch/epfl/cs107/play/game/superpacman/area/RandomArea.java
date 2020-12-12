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
		// TODO Auto-generated method stub
		super.createArea();
		for (DiscreteCoordinates pCoordinates : associatedBehavior.getDoorsPosition()) {
			this.registerActor(new Door(nextArea, new DiscreteCoordinates(2, 2), Logic.TRUE, this,
					Orientation.UP, new DiscreteCoordinates(pCoordinates.x, pCoordinates.y), new DiscreteCoordinates(6, 9)));
		}
	}

	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isOff() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getIntensity() {
		// TODO Auto-generated method stub
		return 0;
	}
}
