package pl.lodz.uni;


public class Main {

    private static final int ROWS = 4;
    private static final int COLS = 4;

    public static void main(String[] args) {
        Grid grid = new Grid(ROWS, COLS);
        grid.getCellByRowCol(1, 0).setAlive(true);
        grid.getCellByRowCol(1, 1).setAlive(true);
        grid.getCellByRowCol(1, 2).setAlive(true);


        for (int i=0; i<10; i++) {
            System.out.println(grid);
            for (Cell cell : grid) {
                cell.prepareNextState();
            }
            for (Cell cell : grid) {
                cell.setAlive(cell.getNextState());
            }

        }

    }

}