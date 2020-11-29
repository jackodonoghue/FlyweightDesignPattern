import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CellFactoryTest {
    @BeforeEach
    public void setUp() throws InterruptedException {
        Board board = new Board(100, 10);
        sleep(200);
    }

    @Test
    public void testCellCacheCount() {
        assertEquals(2, CellFactory.getCellCacheCount());
    }
}
