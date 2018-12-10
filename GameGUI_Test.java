import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
/**
 * @author Chris
 *      This class is JUnit test suite for at least three functions  
 *      Extends TestCase.
 */

import static org.junit.Assert.*;

/**
 * @author Chris
 * JUnit test suite for at least three functions
 * Extends TestCase
 */
public class GameGUI_Test extends TestCase {

    private static GameGUI Game;

    @Before
    public void setUp() throws IOException, InterruptedException {
        Game = new GameGUI();
    }

    @Test
    public static void testPrime() {
        assertEquals(24.0, Double.valueOf(Game.getSgstExp(7, 2, 1, 10)));
/*        (7*2)+(1*10) = 24
        (7*2)+1*10 = 24
        (7*2+1*10) = 24
        (7*2+1*10) = 24
        7*2+(1*10) = 24
        7*2+(1*10) = 24
        (7*2)*1+10 = 24
        (7*2*1)+10 = 24
        (7*2*1)+10 = 24
        (7*2*1+10) = 24
        (7*2*1+10) = 24*/
    }

    @Test
    public static void testPrime2() {
        assertEquals(24.0, Double.valueOf(Game.getSgstExp(5, 9, 2, 6)));
/*        (5/2)*6+9 = 24
        (5/2*6)+9 = 24
        (5/2*6)+9 = 24
        (5/2*6+9) = 24
        (5/2*6+9) = 24
        5/(2/6)+9 = 24
        5/(2/6)+9 = 24
        (5*6)/2+9 = 24
        (5*6/2)+9 = 24
        (5*6/2)+9 = 24*/
    }

    @Test
    public static void testPrime3() {
        assertEquals(24.0, Double.valueOf(Game.getSgstExp(4, 11, 8, 4)));
/*        (4+4)*(11-8) = 24
        (11-4-4)*8 = 24
        (11-4-4)*8 = 24
        (11-8)*(4+4) = 24
        (11-8)*(4+4) = 24
        (11-4-4)*8 = 24
        (11-4-4)*8 = 24
        8*(11-4-4) = 24
        8*(11-4-4) = 24
        8*(11-4-4) = 24
        8*(11-4-4) = 24*/
    }
}