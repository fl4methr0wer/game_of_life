package pl.lodz.uni;

import pl.lodz.uni.gui.GameFrame;
import pl.lodz.uni.domain.Cell;
import pl.lodz.uni.domain.Grid;

import javax.swing.*;

public class Main {

    private static final int ROWS = 20;
    private static final int COLS = 20;

    public static void main(String[] args) {
        Grid grid = new Grid(ROWS, COLS);
        grid.setAlive(0,1);
        grid.setAlive(1,2);
        grid.setAlive(2,2);
        grid.setAlive(2,1);
        grid.setAlive(2,0);

        SwingUtilities.invokeLater(() -> new GameFrame(grid));
    }

}