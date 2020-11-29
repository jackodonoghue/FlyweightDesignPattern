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

        initBoard();
        initGame();
    }

    private void initBoard() {
        int length = this.ROWS_COLS * 10;

        setTitle("Game of Life");
        setSize(length,length);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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

                Cell cell;

                if(grid[i][j]){
                    cell = CellFactory.getCell(height,width, Color.BLUE);
                }
                else{
                    cell = CellFactory.getCell(height,width, Color.WHITE);
                }
                cell.draw(graphics, x, y);
            }
        }
    }

    private int getLength(int totalLengthOfBoard) {
        return totalLengthOfBoard/ROWS_COLS;
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
                int surroundingCellsAlive = 0;

                surroundingCellsAlive = getSurroundingCellsAlive(i, j, surroundingCellsAlive);

                if (grid[i][j]) { //is alive
                    surroundingCellsAlive--;

                    future[i][j] = (surroundingCellsAlive == 2) || (surroundingCellsAlive == 3);
                } else {//if dead
                    future[i][j] = surroundingCellsAlive == 3;
                }
            }
        }

        grid = future;
    }

    private int getSurroundingCellsAlive(int x, int y, int surrounding) {
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

    public int getGridSize() {
        return grid.length * grid[0].length;
    }
}
