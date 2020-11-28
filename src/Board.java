import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame implements ActionListener {
    private final int ROWS_COLS;

    private boolean[][] grid;

    private final Timer TIMER;

    public Board(int rows_cols, int delay) {
        this.ROWS_COLS = rows_cols;
        grid = new boolean[this.ROWS_COLS][this.ROWS_COLS];
        TIMER = new Timer(delay, this);
        int length = this.ROWS_COLS * 10;

        setTitle("Game of Life");
        setSize(length,length);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        initGame();
    }

    private void initGame() {
        fillGrid();
        TIMER.start();
    }

    @Override
    public void paint(Graphics graphics) {
        int x, y;
        int height = getLength(getHeight());
        int width = getLength(getWidth());

        for (int i = 0; i < ROWS_COLS; i++)  {
            for (int j = 0; j < ROWS_COLS; j++) {
                x = getCoordinate(i);
                y = getCoordinate(j);

                Cell cell = new Cell(x, y, height, width);

                if(grid[i][j]){
                    cell.setColor(Color.BLUE);
                }
                else{
                    cell.setColor(Color.WHITE);
                }
                cell.draw(graphics);
            }
        }
    }

    private int getLength(int totalLength) {
        return totalLength/ROWS_COLS;
    }

    private int getCoordinate(int currentRowCol) {
        return (getWidth()*currentRowCol)/ROWS_COLS;
    }

    private void fillGrid() {
        for (int i = 0; i < ROWS_COLS; i++) {
            for (int j = 0; j < ROWS_COLS; j++)
                grid[i][j] = isAliveProbability();
        }
    }

    private boolean isAliveProbability() {
        int probabilityOfAlive = 20;

        return Math.random() * 100 < probabilityOfAlive;
    }

    private void iterate() {
        boolean[][] future = new boolean[ROWS_COLS][ROWS_COLS];

        for (int i = 1; i < ROWS_COLS - 1; i++) {
            for (int j = 1; j < ROWS_COLS - 1; j++) {
                int surrounding = 0;

                surrounding = getSurrounding(i, j, surrounding);

                if (grid[i][j]) { //is alive
                    surrounding--;

                    future[i][j] = (surrounding == 2) || (surrounding == 3);
                } else {//if dead
                    if (surrounding == 3) {
                        future[i][j] = true;
                    }
                }
            }
        }

        grid = future;
    }

    private int getSurrounding(int x, int y, int surrounding) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (grid[x + i][y + j])
                    surrounding++;
            }
        }
        return surrounding;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        iterate();
        repaint();
    }
}
