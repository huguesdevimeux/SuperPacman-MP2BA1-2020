package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior.SuperPacmanCell;
import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior.SuperPacmanCellType;

public class SuperPacmanCellTypeTest {

    @Test
    void TestToType() {
        SuperPacmanCellType type = SuperPacmanCellType.toType(-16777216);
        assertEquals(SuperPacmanCellType.WALL, type);  
        SuperPacmanCellType type2 = SuperPacmanCellType.toType(0);
        assertEquals(SuperPacmanCellType.NONE, type2);
        SuperPacmanCellType type3 = SuperPacmanCellType.toType(656565);
        assertEquals(SuperPacmanCellType.NONE, type3);
        
    }

    
}
