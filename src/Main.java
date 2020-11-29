import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        new Board (getRows(),getDelay());
    }

    private static int getRows() {
        int rows;

        //perform input validation
        rows = Integer.parseInt(JOptionPane.showInputDialog("Please enter the number of rows: "));

        return rows;
    }

    private static int getDelay() {
        int delay = -1;

        //perform input validation
        delay = Integer.parseInt(JOptionPane.showInputDialog("Please enter the time between each iteration in milliseconds: "));

        return delay;
    }
}