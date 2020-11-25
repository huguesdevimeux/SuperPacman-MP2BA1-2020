package ch.epfl.cs107.play.game.superpacman.actor;

import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.AreaEntity;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.RPGSprite;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.RegionOfInterest;
import ch.epfl.cs107.play.window.Canvas;

public class Wall extends AreaEntity{

	Sprite sprite;

	/**
	 * Build a Wall from its position and its neighborhood.
	 * The neighborhood allows to select the sprite, it is a 3x3 matrix
	 * that indicate if a wall is present in the neighborhood,
	 * the center corresponds to the current wall position
	 * @param area (Area): Owner area. Not null 
	 * @param position (DiscreteCoordinates): The wall position. Not null
	 * @param neighborhood (boolean[][]):. The 3x3 matrix. Not null
	 */
	public Wall(Area area, DiscreteCoordinates position, boolean[][] neighborhood) {
		super(area, Orientation.DOWN, position);

		if(neighborhood[0][1] && neighborhood[2][1] && !neighborhood[1][0] && !neighborhood[1][2]) {
			//horizontal
			sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 0*64, 64, 64));
		}else if(!neighborhood[0][1] && !neighborhood[2][1] && neighborhood[1][0] && neighborhood[1][2]) {
			//vertical
			sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 1*64, 64, 64));
		}else if(neighborhood[0][1] && !neighborhood[2][1] && !neighborhood[1][0] && !neighborhood[1][2]) {
			//horizontal dead end right
			sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 2*64, 64, 64));
		}else if(!neighborhood[0][1] && neighborhood[2][1] && !neighborhood[1][0] && !neighborhood[1][2]) {
			//horizontal dead end left
			sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 3*64, 64, 64));
		}else if(!neighborhood[0][1] && !neighborhood[2][1] && neighborhood[1][0] && !neighborhood[1][2]) {
			//vertical dead end bottom
			sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 4*64, 64, 64));
		}else if(!neighborhood[0][1] && !neighborhood[2][1] && !neighborhood[1][0] && neighborhood[1][2]) {
			//vertical dead end top
			sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 5*64, 64, 64));
		}else if(neighborhood[0][1] && !neighborhood[2][1] && neighborhood[1][0] && !neighborhood[1][2]) {
			//corner left to top
			if(neighborhood[0][0]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 15*64, 64, 64));
			}else {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 6*64, 64, 64));
			}
		}else if(!neighborhood[0][1] && neighborhood[2][1] && neighborhood[1][0] && !neighborhood[1][2]) {
			//corner right to top
			if(neighborhood[2][0]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 16*64, 64, 64));
			}else {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 7*64, 64, 64));
			}
		}else if(neighborhood[0][1] && !neighborhood[2][1] && !neighborhood[1][0] && neighborhood[1][2]) {
			//corner left to bottom
			if(neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 17*64, 64, 64));
			}else {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 8*64, 64, 64));
			}
		}else if(!neighborhood[0][1] && neighborhood[2][1] && !neighborhood[1][0] && neighborhood[1][2]) {
			//corner right to bottom
			if(neighborhood[2][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 18*64, 64, 64));
			}else {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 9*64, 64, 64));
			}
		}else if(!neighborhood[0][1] && neighborhood[2][1] && neighborhood[1][0] && neighborhood[1][2]) {
			//3 way but left
			if(neighborhood[2][0] && !neighborhood[2][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 19*64, 64, 64));
			}else if(!neighborhood[2][0] && neighborhood[2][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 20*64, 64, 64));
			}else if(neighborhood[2][0] && neighborhood[2][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 21*64, 64, 64));
			}else {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 10*64, 64, 64));
			}
		}else if(neighborhood[0][1] && !neighborhood[2][1] && neighborhood[1][0] && neighborhood[1][2]) {
			//3 way but right
			if(neighborhood[0][0] && !neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 22*64, 64, 64));
			}else if(!neighborhood[0][0] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 23*64, 64, 64));
			}else if(neighborhood[0][0] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 24*64, 64, 64));
			}else {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 11*64, 64, 64));
			}
		}else if(neighborhood[0][1] && neighborhood[2][1] && !neighborhood[1][0] && neighborhood[1][2]) {
			//3 way but top
			if(neighborhood[0][2] && !neighborhood[2][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 25*64, 64, 64));
			}else if(!neighborhood[0][2] && neighborhood[2][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 26*64, 64, 64));
			}else if(neighborhood[0][2] && neighborhood[2][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 27*64, 64, 64));
			}else {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 12*64, 64, 64));
			}
		}else if(neighborhood[0][1] && neighborhood[2][1] && neighborhood[1][0] && !neighborhood[1][2]) {
			//3 way but bottom
			if(neighborhood[0][0] && !neighborhood[2][0]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 28*64, 64, 64));
			}else if(!neighborhood[0][0] && neighborhood[2][0]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 29*64, 64, 64));
			}else if(neighborhood[0][0] && neighborhood[2][0]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 30*64, 64, 64));
			}else {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 13*64, 64, 64));
			}
		}else if(neighborhood[0][1] && neighborhood[2][1] && neighborhood[1][0] && neighborhood[1][2]) {
			//4 way
			if(!neighborhood[0][0] && neighborhood[2][0] && neighborhood[2][2] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 31*64, 64, 64));
			}else if(neighborhood[0][0] && !neighborhood[2][0] && neighborhood[2][2] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 32*64, 64, 64));
			}else if(neighborhood[0][0] && neighborhood[2][0] && !neighborhood[2][2] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 33*64, 64, 64));
			}else if(neighborhood[0][0] && neighborhood[2][0] && neighborhood[2][2] && !neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 34*64, 64, 64));
			}else if(!neighborhood[0][0] && !neighborhood[2][0] && neighborhood[2][2] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 35*64, 64, 64));
			}else if(neighborhood[0][0] && !neighborhood[2][0] && !neighborhood[2][2] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 36*64, 64, 64));
			}else if(neighborhood[0][0] && neighborhood[2][0] && !neighborhood[2][2] && !neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 37*64, 64, 64));
			}else if(!neighborhood[0][0] && neighborhood[2][0] && neighborhood[2][2] && !neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 38*64, 64, 64));
			}else if(!neighborhood[0][0] && neighborhood[2][0] && !neighborhood[2][2] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 39*64, 64, 64));
			}else if(neighborhood[0][0] && !neighborhood[2][0] && neighborhood[2][2] && !neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 40*64, 64, 64));
			}else if(!neighborhood[0][0] && !neighborhood[2][0] && !neighborhood[2][2] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 41*64, 64, 64));
			}else if(neighborhood[0][0] && !neighborhood[2][0] && !neighborhood[2][2] && !neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 42*64, 64, 64));
			}else if(!neighborhood[0][0] && neighborhood[2][0] && !neighborhood[2][2] && !neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 43*64, 64, 64));
			}else if(!neighborhood[0][0] && !neighborhood[2][0] && neighborhood[2][2] && !neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 44*64, 64, 64));
			}else if(neighborhood[0][0] && neighborhood[2][0] && neighborhood[2][2] && neighborhood[0][2]) {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 45*64, 64, 64));
			}else {
				sprite = new RPGSprite("superpacman/wall", 1, 1, this, new RegionOfInterest(0, 14*64, 64, 64));
			}
		}
	}

	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public boolean takeCellSpace() {
		return true;
	}

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
		//No interaction
	}

	@Override
	public void draw(Canvas canvas) {
		if(sprite != null)
			sprite.draw(canvas);
	}
}
