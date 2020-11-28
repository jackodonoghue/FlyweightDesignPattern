import java.awt.*;

public class Cell  {
    private int height;
    private int width;
    private Color color;


    public Cell(int height, int width, Color color) {
        this.height = height;
        this.width = width;
        this.color = color;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}