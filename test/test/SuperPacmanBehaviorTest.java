package test;

import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior;
import ch.epfl.cs107.play.io.DefaultFileSystem;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.io.ResourceFileSystem;
import ch.epfl.cs107.play.window.swing.SwingWindow;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class SuperPacmanBehaviorTest {
    private FileSystem standardFilesytem;  
    private SuperPacmanBehavior Level0SuperPacmanBehavior;

    @Before
    public void initFixtures() {
        standardFilesytem = new ResourceFileSystem(DefaultFileSystem.INSTANCE);
        Level0SuperPacmanBehavior = new SuperPacmanBehavior(new SwingWindow("test", standardFilesytem, 10, 10),"superpacman/Level0"); 
    }
    

    @Test
    public void TestIsWall() {
        assertTrue(Level0SuperPacmanBehavior.isWall(1, 2));
        assertTrue(Level0SuperPacmanBehavior.isWall(1, 1));
    }
    @Test
    public void TestOutOfBoundIsWall() {
        assertTrue(Level0SuperPacmanBehavior.isWall(-1, -1));
        assertTrue(Level0SuperPacmanBehavior.isWall(Level0SuperPacmanBehavior.getHeight() + 1, Level0SuperPacmanBehavior.getWidth() + 1));
    }
    
    @Test
    public void TestEverythingIsNotWall() {
        for (int y = 0; y < Level0SuperPacmanBehavior.getHeight(); y++) {
            for (int x = 0; x < Level0SuperPacmanBehavior.getWidth(); x++) {
                if (!Level0SuperPacmanBehavior.isWall(x, y)) {
                    fail();
                    return;
                }
            }
        }
        fail("Everything is a wall!");
    }
    


}
