package ch.epfl.cs107.play.game.areagame;

import java.util.HashMap;
import java.util.Map;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.window.Window;


/**
 * AreaGame: concept of Game displayed in a (MxN) Grid called Area
 */
abstract public class AreaGame implements Game {

	// Context objects
	private Window window;
	private FileSystem fileSystem;
	/// A map containing all the Area of the Game
	private Map<String, Area> areas;
	/// The current area the game is in
	private Area currentArea;

	/**
	 * Add an Area to the AreaGame list
	 * @param a (Area): The area to add, not null
	 */
	protected final void addArea(Area a){
		areas.put(a.getTitle(), a);
	}

	/**
	 * Setter for the current area: Select an Area in the list from its key
	 * - the area is then begin or resume depending if the area is already started or not and if it is forced
	 * @param key (String): Key of the Area to select, not null
	 * @param forceBegin (boolean): force the key area to call begin even if it was already started
	 * @return (Area): after setting it, return the new current area
	 */
	protected final Area setCurrentArea(String key, boolean forceBegin){
		Area newArea = areas.get(key);

		if(newArea == null) {
			System.out.println("New Area not found, keep previous one");
		}else {
			// Stop previous area if it exist
			if(currentArea != null){
				currentArea.suspend();
				currentArea.purgeRegistration(); // Is useful?
			}

			currentArea = newArea;

			// Start/Resume the new one
			if (forceBegin || !currentArea.isStarted()) {
				currentArea.begin(window, fileSystem);
			} else {
				currentArea.resume(window, fileSystem);
			}
		}

		return currentArea;
	}

	/**@return (Window) : the Graphic and Audio context*/
	protected final Window getWindow(){
		return window;
	}

	/**@return (FIleSystem): the linked file system*/
	protected final FileSystem getFileSystem(){
		return fileSystem;
	}

	/**
	 * Getter for the current area
	 * @return (Area)
	 */
	protected final Area getCurrentArea(){
		return this.currentArea;
	}

	/// AreaGame implements Playable

	@Override
	public boolean begin(Window window, FileSystem fileSystem) {

		// Keep context
		this.window = window;
		this.fileSystem = fileSystem;

		areas = new HashMap<>();
		return true;
	}


	@Override
	public void update(float deltaTime) {
		currentArea.update(deltaTime);
	}

	@Override
	public void end() {
		// by default does nothing
		// can save the game states if wanted
	}
}
