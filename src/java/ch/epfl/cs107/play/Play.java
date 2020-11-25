package ch.epfl.cs107.play;

import java.awt.GraphicsEnvironment;

import ch.epfl.cs107.play.game.Game;
import ch.epfl.cs107.play.game.areagame.io.ResourcePath;
import ch.epfl.cs107.play.io.DefaultFileSystem;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.ResourceFileSystem;
import ch.epfl.cs107.play.io.XMLTexts;
import ch.epfl.cs107.play.recorder.RecordReplayer;
import ch.epfl.cs107.play.recorder.Recorder;
import ch.epfl.cs107.play.window.Window;
import ch.epfl.cs107.play.window.swing.SwingWindow;

/**
 * Main entry point.
 */
public class Play {

	/** One second in nano second */
    private static final float ONE_SEC = 1E9f;

	/**
	 * Main entry point.
	 * @param args (Array of String): ignored
	 */
	public static void main(String[] args) {

		// Define cascading file system
		final FileSystem fileSystem = new ResourceFileSystem(DefaultFileSystem.INSTANCE);

        // Create a demo game :
		// (it is expected that at the beginning, the provided file does not compile)
       
        //final Game game = new SuperPacman();

		// Use Swing display
		final Window window = new SwingWindow(game.getTitle(), fileSystem, 550, 550);
		window.registerFonts(ResourcePath.FONTS);
		
		Recorder recorder = new Recorder(window);
		RecordReplayer replayer = new RecordReplayer(window);
		try {

			if (game.begin(window, fileSystem)) {
				//recorder.start();
				//replayer.start("record1.xml");

				// Use system clock to keep track of time progression
                long currentTime = System.nanoTime();
				long lastTime;
				final float frameDuration = ONE_SEC / game.getFrameRate();

				// Run until the user try to close the window
				while (!window.isCloseRequested()) {

					// Compute time interval
                    lastTime = currentTime;
                    currentTime = System.nanoTime();
					float deltaTime = (currentTime - lastTime);

                    try {
                        int timeDiff = Math.max(0, (int) (frameDuration - deltaTime));
                        Thread.sleep((int) (timeDiff / 1E6), (int) (timeDiff % 1E6));
                    } catch (InterruptedException e) {
                        System.out.println("Thread sleep interrupted");
                    }

                    currentTime = System.nanoTime();
                    deltaTime = (currentTime - lastTime) / ONE_SEC;

                    // Let the game do its stuff
                    game.update(deltaTime);

                    // Render and update input
                    window.update();
                    //recorder.update();
                    //replayer.update();
				}
			}
			//recorder.stop("record1.xml");
			game.end();

		} finally {
			// Release resources
			window.dispose();
		}
	}

}
