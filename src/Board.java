import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Board extends JFrame implements ActionListener {
    private final int ROWS_COLS = 100;

    private final int DELAY = 200;
    private final Timer TIMER = new Timer(DELAY, this);

    private boolean[][] grid = new boolean[ROWS_COLS][ROWS_COLS];


    public Board() {
        setTitle("Game of Life");
        setSize(500,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        initGame();
    }

    public void initGame() {
        fillGrid();
        TIMER.start();
    }

    @Override
    public void paint(Graphics graphics) {
        for (int i = 0; i < ROWS_COLS; i++)  {
            for (int j = 0; j < ROWS_COLS; j++) {
                int x = (getWidth()*i)/ROWS_COLS;
                int y = (getHeight()*j)/ROWS_COLS;

                System.out.println(getHeight()/ROWS_COLS);

                Cell cell = new Cell(x, y, getHeight()/ROWS_COLS, getWidth()/ROWS_COLS);

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

    private void fillGrid() {
        for (int i = 0; i < ROWS_COLS; i++) {
            for (int j = 0; j < ROWS_COLS; j++) {
                if (Math.random() * 100 < 20 && (i != 0 && j != 0) && (i != ROWS_COLS - 1 && j != ROWS_COLS - 1)) {
                    grid[i][j] = true;
                } else {
                    grid[i][j] = false;
                }
            }
        }
    }

    private void iterate() {
        boolean[][] future = new boolean[ROWS_COLS][ROWS_COLS];

        for (int i = 1; i < ROWS_COLS - 1; i++) {
            for (int j = 1; j < ROWS_COLS - 1; j++) {
                int surrounding = 0;

                for (int k = -1; k <= 1; k++) {
                    for (int l = -1; l <= 1; l++) {
                        if (grid[i + k][j + l])
                            surrounding++;
                    }
                }

                if (grid[i][j]) { //is alive
                    surrounding--;

                    if ((surrounding == 2) || (surrounding == 3)) { //survives
                        future[i][j] = true;
                    } else {//cell dies
                        future[i][j] = false;
                    }
                } else {//if dead
                    if (surrounding == 3) {
                        future[i][j] = true;
                    }
                }
            }
        }

        grid = future;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        iterate();
        repaint();
    }
}
