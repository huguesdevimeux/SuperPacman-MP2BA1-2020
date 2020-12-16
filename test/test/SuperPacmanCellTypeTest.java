package test;

import ch.epfl.cs107.play.game.superpacman.area.SuperPacmanBehavior.SuperPacmanCellType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
