package pl.lodz.uni;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Grid implements Iterable<Cell> {
    private Cell[][] grid;
    private int rows;
    private int cols;
    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        grid = new Cell[rows][cols];
        init();
    }
    private void init() {
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                grid[i][j] = new Cell(i, j, this);
            }
        }
    }

    public Cell getCellByRowCol(int row, int col) {
        return this.grid[row][col];
    }
    public boolean cellIsAliveAndExistsByRowCol(int row, int col) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }
        return getCellByRowCol(row, col).isAlive();
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.append(grid[i][j].isAlive() ? "X " : ". "); // Using "X" for alive and "." for dead cells
            }
            result.append("\n");
        }

        return result.toString();
    }
    @Override
    public Iterator<Cell> iterator() {
        return new GridIterator();
    }
    private class GridIterator implements Iterator<Cell> {
        private int currentRow = 0;
        private int currentCol = 0;
        @Override
        public boolean hasNext() {
            return currentRow < rows && currentCol < cols;
        }
        @Override
        public Cell next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the grid");
            }
            Cell cell = grid[currentRow][currentCol];
            currentCol++;
            if (currentCol >= cols) {
                currentCol = 0;
                currentRow++;
            }
            return cell;
        }
    }
}
