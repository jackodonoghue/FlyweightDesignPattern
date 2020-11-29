import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BoardTest {
    Board board;

    @BeforeEach
    public void setUp() throws InterruptedException {
         board = new Board(100, 10);
        sleep(200);
    }

    @Test
    public void testGetGridSize() {
        assertEquals(100*100, board.getGridSize());
    }

}
