import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class CellFactory {
    private static final Map<String, Cell> cellCache = new HashMap<>();

    public static Cell getCell(int height, int width, Color color) {
        Cell cell = cellCache.get(color.toString());

        if(cell == null) {
            cell = new Cell(height, width, color);

            cellCache.put(color.toString(), cell);
        }

        return cell;
    }
}

