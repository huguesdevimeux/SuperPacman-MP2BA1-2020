package ch.epfl.cs107.play.game.superpacman.menu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Text;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Keyboard;
import ch.epfl.cs107.play.window.Window;

public class WelcomeMenu extends Area {

	private static final String FONT = "CMU Serif";
	private static final String STORY_MODE_TEXT = "Story mode";
	private String TITLE_TEXT = "Super Pacman!";
	private Text title;
	private List<Text> choices = new ArrayList<Text>();
	Keyboard keyboard;  

	private int indexSelection;
	private boolean canQuit;
	private boolean wantToQuit;
	
	
	public WelcomeMenu() {
		super();
		indexSelection = -1;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "menu";
	}

	@Override
	public float getCameraScaleFactor() {
		// TODO Auto-generated method stub
		return 30.f;
	}
	
	/**
	 * @return index chosen of the choices. -1 if none.
	 */
	private int getIndexChoiceFromKeyboard() {
		if (keyboard.get(Keyboard.DOWN).isPressed() && indexSelection + 1 < choices.size()) {
			return indexSelection + 1;
		}
		else if (keyboard.get(Keyboard.UP).isPressed() && indexSelection - 1 >= 0) {
			return indexSelection - 1;
		}
		return -1; 
	}

	private boolean wantToQuit() {
		wantToQuit = keyboard.get(Keyboard.ENTER).isPressed();
		return wantToQuit;
	}
	
	/**
	 * Indicate wether the player is allowed to quit the menu.
	 */
	public boolean canQuit() {
		return canQuit; 
	}

	/**
	 * Return the selected option. 
	 * @return
	 */
	public int getSelectedOption() {
		return indexSelection; 
	}

	@Override
	public void update(float deltaTime) {
		if (wantToQuit()) {
			System.out.println("fuck?");
			canQuit = true; 
		}
		int indexChosen = getIndexChoiceFromKeyboard();
		// -1 means no choice. 
		if (indexChosen != -1) updateSelection(indexChosen);
		super.update(deltaTime);
	}

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {
		super.begin(window, fileSystem);
		keyboard = getKeyboard(); 
		setBehavior(new AreaBehavior(window, 30, 30) {});
		generateMenu();
		return true;
	}
	
	/**
	 * Add all the different options of the menu.
	 */
	private void generateMenu() {
		Text gamemode1 = new Text(STORY_MODE_TEXT, new DiscreteCoordinates(getWidth() / 2 - 1, getHeight() - 13), this, true,
				1.5f, Color.YELLOW);
		Text gamemode2 = new Text("Infinite mode", new DiscreteCoordinates(getWidth() / 2 - 1, getHeight() - 16), this,
				true, 1.5f, Color.YELLOW);
		gamemode1.setFontName(FONT);
		gamemode2.setFontName(FONT);
		choices.add(gamemode1);
		choices.add(gamemode2);
		registerActor(gamemode1);
		registerActor(gamemode2);

		title = new Text(TITLE_TEXT, new DiscreteCoordinates(getWidth() / 2 - 1, getHeight() - 7), this, true, 2.6f,
				Color.RED);
		title.setFontName(FONT);
		title.makeItAppear(0.05f);
		registerActor(title);

		Text credits = new Text("Credits: Luca Mouchel/Hugues Devimeux", new DiscreteCoordinates(getWidth() / 2 - 1, 3), this, true, 1.f,
				Color.GRAY);
		credits.setFontName(FONT);
		registerActor(credits);
		
		Text license = new Text("Code is under WTFPL license. Resources : © EPFL 2020.", new DiscreteCoordinates(getWidth() / 2 - 1, 2), this, true, 1.f,
				Color.GRAY);
		license.setFontName(FONT);
		registerActor(license); 		
	}
	
	/**
	 * Update the visual of the things to select.
	 * @param nextIndex
	 */
	private void updateSelection(int nextIndex) {
		// indexSelection is basically the previous index.
		if (indexSelection != -1) markFieldAsUnselected(indexSelection);
		markFieldAsSelected(nextIndex);
		indexSelection = nextIndex;
	}

	private void markFieldAsSelected(int index) {
		choices.get(index).setFillColor(Color.RED);
	}

	private void markFieldAsUnselected(int index) {
		choices.get(index).setFillColor(Color.YELLOW);
	}
	
    
}
