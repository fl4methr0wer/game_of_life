package pl.lodz.uni.domain;

public class Cell {
    private int row;
    private int col;
    private boolean isAlive;
    private boolean isAliveInNextStep;
    private Grid grid;

    public Cell(Grid grid) {
        this.grid = grid;
    }
    public Cell(int row, int col, Grid grid) {
        this.row = row;
        this.col = col;
        this.isAlive = false;
        this.isAliveInNextStep = false;
        this.grid = grid;
    }

    public void prepareNextState() {
        int aliveNeighbours = countAliveNeighbours();
        boolean isAliveAndShouldNotDie = isAlive && !shouldDie(aliveNeighbours);
        boolean isDeadAndShouldGetBorn = !isAlive && shouldGetBorn(aliveNeighbours);
        isAliveInNextStep = isAliveAndShouldNotDie || isDeadAndShouldGetBorn;
    }
    public boolean shouldDie(int neighbours) {
        boolean isUnacceptableAmount = neighbours < 2 || 3 < neighbours;
        return isAlive && isUnacceptableAmount;
    }
    public boolean shouldGetBorn(int neigbours) {
        boolean properAmount = neigbours == 3;
        return !isAlive && properAmount;
    }
    private int countAliveNeighbours() {
        int counter = 0;
        counter = cellIsAliveAndExistsByRowCol(row - 1, col - 1) ? counter + 1 : counter;
        counter = cellIsAliveAndExistsByRowCol(row - 1, col) ? counter + 1 : counter;
        counter = cellIsAliveAndExistsByRowCol(row - 1, col + 1) ? counter + 1 : counter;

        counter = cellIsAliveAndExistsByRowCol(row, col -1) ? counter + 1 : counter;
        counter = cellIsAliveAndExistsByRowCol(row, col + 1) ? counter + 1 : counter;

        counter = cellIsAliveAndExistsByRowCol(row + 1, col -1) ? counter + 1 : counter;
        counter = cellIsAliveAndExistsByRowCol(row + 1, col) ? counter + 1 : counter;
        counter = cellIsAliveAndExistsByRowCol(row + 1, col + 1) ? counter + 1 : counter;
        return counter;
    }
    private boolean cellIsAliveAndExistsByRowCol(int row, int col) {
        int rows = grid.getRows();
        int cols = grid.getCols();
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        }
        return grid.getCellByRowCol(row,col).isAlive();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean getNextState() {
        return isAliveInNextStep;
    }

    public void setNextState(boolean nextState) {
        this.isAliveInNextStep = nextState;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
