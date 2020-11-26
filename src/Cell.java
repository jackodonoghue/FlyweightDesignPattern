import java.awt.*;

public class Cell  {
    private int x;
    private int y;
    private int height;
    private int width;
    private Color color;


    public Cell(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}