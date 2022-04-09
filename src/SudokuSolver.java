import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuSolver {

    public static void main(String[] args) {

        ArrayList<ArrayList<Integer>> startingGrid = new ArrayList<ArrayList<Integer>>();

        ArrayList<Integer> row = new ArrayList<Integer>();
        row.add(1);
        row.add(1);
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
        row.add(7);
        row.add(6);
        row.add(2);
        row.add(8);
        row.add(2);
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
        row.add(1);
        row.add(5);
        row.add(3);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);
        startingGrid.add(row);

        printGrid(startingGrid);
        if (validateGrid(startingGrid)) {
            System.out.println("The grid is valid");
        } else {
            System.out.println("The grid is not valid");
        }
    }

    public static void printGrid(ArrayList<ArrayList<Integer>> grid) {
        System.out.println("printGrid");
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

    public static boolean validateGrid(ArrayList<ArrayList<Integer>> grid) {
        System.out.println("validateGrid");
        boolean validGrid = true;
        for (int i = 0; i < grid.size(); i++) {
            boolean validRow = true;
            System.out.print("Row: "+ (i+1) +"\t");

            ArrayList<Integer> row = grid.get(i);

            Set<Integer> tempSet = new HashSet<>();

            for (int j = 0; j < row.size(); j++) {                
                System.out.print("Cell: "+ (j+1) +"\t");
                Integer cell = row.get(j);
                if (cell != null && !tempSet.add(cell)) {
                    System.out.print("[" + cell + "] - already exists in row\t");
                    validGrid = false;
                    validRow = false;
                }
            }
            System.out.println("The row is "+ (validRow ? "" : "not ") +"valid");
        }
        return validGrid;
    }
}
