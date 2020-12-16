package ch.epfl.cs107.play.game.tutosSolution.actor;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.MovableAreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.tutosSolution.area.Tuto2Area;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class GhostPlayer extends MovableAreaEntity {
	private float hp;
	private TextGraphics message;
	private Sprite sprite;
	private boolean isPassingADoor;
	/// Animation duration in frame number
    private final static int ANIMATION_DURATION = 8;
	/**
	 * Demo actor
	 * 
	 */
	public GhostPlayer(Area owner, Orientation orientation, DiscreteCoordinates coordinates, String spriteName) {
		super(owner, orientation, coordinates);
		this.hp = 10;
		message = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
		message.setParent(this);
		message.setAnchor(new Vector(-0.3f, 0.1f));
		sprite = new Sprite(spriteName, 1.f, 1.f,this);

		resetMotion();
	}
	 
	 @Override
	    public void update(float deltaTime) {
		 if (hp > 0) {
				hp -=deltaTime;
				message.setText(Integer.toString((int)hp));
			}
			if (hp < 0) hp = 0.f;
			Keyboard keyboard= getOwnerArea().getKeyboard();
	        moveOrientate(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
	        moveOrientate(Orientation.UP, keyboard.get(Keyboard.UP));
	        moveOrientate(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
	        moveOrientate(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
	            
	        super.update(deltaTime);
	        
	        //List<DiscreteCoordinates> coords = getEnteredCells();
	        List<DiscreteCoordinates> coords = getCurrentCells();
	        if (coords != null) {
	        	for (DiscreteCoordinates c : coords) {
	        		if (((Tuto2Area)getOwnerArea()).isDoor(c)) setIsPassingADoor();
	        	}
	        }
	    }

	    /**
	     * Orientate or Move this player in the given orientation if the given button is down
	     * @param orientation (Orientation): given orientation, not null
	     * @param b (Button): button corresponding to the given orientation, not null
	     */
	    private void moveOrientate(Orientation orientation, Button b){
	    
	        if(b.isDown()) {
	            if(getOrientation() == orientation) move(ANIMATION_DURATION);
	            else orientate(orientation);
	        }
	    }
	    /**
	     * Indicate the player just passed a door
	     */
	    protected void setIsPassingADoor(){ // 
	        isPassingADoor = true;
	    }

	    /**@return (boolean): true if the player is passing a door*/
	    public boolean isPassingADoor(){
	        return isPassingADoor;
	    }
	 
	    public void resetDoorState() {
	    	isPassingADoor = false;
	    }
	    /**
	    /*
	     * Leave an area by unregister this player
	     */
	    public void leaveArea(){
	        getOwnerArea().unregisterActor(this);
	    }

	    /**
	     *
	     * @param area (Area): initial area, not null
	     * @param position (DiscreteCoordinates): initial position, not null
	     */
	    public void enterArea(Area area, DiscreteCoordinates position){
	        area.registerActor(this);
	        area.setViewCandidate(this);
	        setOwnerArea(area);
	        setCurrentPosition(position.toVector());
	        resetMotion();
	    }
    
	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);	
		message.draw(canvas);
	}

	public boolean isWeak() {
		return (hp <= 0.f);
	}

	public void strengthen() {
		hp = 10;
	}

	///Ghost implements Interactable

	@Override
	public boolean takeCellSpace() {
		return true;
	}

	@Override
	public boolean isCellInteractable() {
		return true;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
	}
}
