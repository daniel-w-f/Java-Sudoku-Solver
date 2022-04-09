import java.util.ArrayList;

public class SudokuSolver {

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> startingGrid = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> row = new ArrayList<Integer>();
        row.add(1);
        row.add(null);
        row.add(2);
        row.add(null);
        row.add(null);
        row.add(3);
        row.add(null);
        row.add(5);
        row.add(null);

        startingGrid.add(row);
        row = new ArrayList<Integer>();
        row.add(2);
        row.add(null);
        row.add(6);
        row.add(null);
        row.add(null);
        row.add(8);
        row.add(null);
        row.add(9);
        row.add(null);
        startingGrid.add(row);
        row = new ArrayList<Integer>();
        row.add(null);
        row.add(null);
        row.add(2);
        row.add(null);
        row.add(null);
        row.add(null);
        row.add(null);
        row.add(1);
        row.add(3);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);

        printGrid(startingGrid);

    }

    public static void printGrid(ArrayList<ArrayList<Integer>> grid) {
        for (int i = 0; i < grid.size(); i++) {
            ArrayList<Integer> row = grid.get(i);
            for (int j = 0; j < row.size(); j++) {
                System.out.print(row.get(j) != null ? row.get(j) : ".");
                if (j == 2 || j == 5) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println("---+---+---");
            }
        }
    }
}
