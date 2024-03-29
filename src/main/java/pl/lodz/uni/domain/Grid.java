package pl.lodz.uni.domain;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Grid implements Iterable<Cell> {
    private final Cell[][] grid;
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
    public void prepareNextState() {
        for (Cell cell : this) {
            cell.prepareNextState();
        }
    }
    public void setNextState() {
        for (Cell cell : this) {
            cell.setAlive(cell.getNextState());
        }
    }

    public Cell getCellByRowCol(int row, int col) {
        return this.grid[row][col];
    }
    public void setAlive(int row, int col) {
        getCellByRowCol(row, col).setAlive(true);
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
    public int getRows() {
        return this.rows;
    }
    public int getCols() {
        return this.cols;
    }
}
