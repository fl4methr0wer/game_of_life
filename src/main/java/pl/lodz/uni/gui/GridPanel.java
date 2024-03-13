package pl.lodz.uni.gui;

import pl.lodz.uni.domain.Cell;
import pl.lodz.uni.domain.Grid;
import javax.swing.*;
import java.awt.*;
class GridPanel extends JPanel {
    private Grid grid;
    private static int CELL_SIZE = 20;
    private static int X_OFFSET = 50;
    private static int Y_OFFSET = 50;
    public GridPanel(Grid grid) {
        this.grid = grid;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (Cell cell : grid) {
            int row = cell.getRow();
            int col = cell.getCol();
            if (cell.isAlive()) {
                graphics.fillRect(X_OFFSET + col * CELL_SIZE, Y_OFFSET + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            } else {
                graphics.drawRect(X_OFFSET + col * CELL_SIZE, Y_OFFSET + row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        int width = grid.getCols() * CELL_SIZE + X_OFFSET * 2;
        int height = grid.getRows() * CELL_SIZE + Y_OFFSET * 2;
        return new Dimension(width, height);
    }
}
