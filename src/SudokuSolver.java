import java.util.ArrayList;

public class SudokuSolver {

    public static void main(String[] args) {
        System.out.println("Hello World");

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
        row.clear();
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
        startingGrid.add(row);

        System.out.println(startingGrid);

        printGrid(startingGrid);

    }

    public static void printGrid(ArrayList<ArrayList<Integer>> grid) {
        for (ArrayList<Integer> arrayList : grid) {
            System.out.println(arrayList);
        }
    }
}
