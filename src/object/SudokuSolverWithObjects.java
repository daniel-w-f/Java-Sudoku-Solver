package object;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuSolverWithObjects {

    public static void main(String[] args) {

        // if (validateGrid(startingGrid)) {
        //     System.out.println("The grid is valid");
        // } else {
        //     System.out.println("The grid is not valid");
        // }

        ArrayList<Cell> grid = new ArrayList<Cell>();
        int row = 1;
        grid.add(new Cell(row, 1, 1));
        grid.add(new Cell(row, 2, 2));
        grid.add(new Cell(row, 3, 3));
        grid.add(new Cell(row, 4, 4));
        grid.add(new Cell(row, 5, 5));
        grid.add(new Cell(row, 6, 6));
        grid.add(new Cell(row, 7, 7));
        grid.add(new Cell(row, 8, 8));
        grid.add(new Cell(row, 9, 9));
        row = 2;
        grid.add(new Cell(row, 1, 4));
        grid.add(new Cell(row, 2, 5));
        grid.add(new Cell(row, 3, 6));
        grid.add(new Cell(row, 4, 7));
        grid.add(new Cell(row, 5, 8));
        grid.add(new Cell(row, 6, 9));
        grid.add(new Cell(row, 7, 1));
        grid.add(new Cell(row, 8, 2));
        grid.add(new Cell(row, 9, 3));
        row = 3;
        grid.add(new Cell(row, 1, 7));
        grid.add(new Cell(row, 2, 8));
        grid.add(new Cell(row, 3, 9));
        grid.add(new Cell(row, 4, 1));
        grid.add(new Cell(row, 5, 2));
        grid.add(new Cell(row, 6, 3));
        grid.add(new Cell(row, 7, 4));
        grid.add(new Cell(row, 8, 5));
        grid.add(new Cell(row, 9, 6));
        row = 4;
        grid.add(new Cell(row, 1, 1));
        grid.add(new Cell(row, 2, 2));
        grid.add(new Cell(row, 3, 3));
        grid.add(new Cell(row, 4, 4));
        grid.add(new Cell(row, 5, 5));
        grid.add(new Cell(row, 6, 6));
        grid.add(new Cell(row, 7, 7));
        grid.add(new Cell(row, 8, 8));
        grid.add(new Cell(row, 9, 9));
        row = 5;
        grid.add(new Cell(row, 1, 4));
        grid.add(new Cell(row, 2, 5));
        grid.add(new Cell(row, 3, 6));
        grid.add(new Cell(row, 4, 7));
        grid.add(new Cell(row, 5, 8));
        grid.add(new Cell(row, 6, 9));
        grid.add(new Cell(row, 7, 1));
        grid.add(new Cell(row, 8, 2));
        grid.add(new Cell(row, 9, 3));
        row = 6;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, 8));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 1));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, 3));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, 5));
        grid.add(new Cell(row, 9, null));
        row = 7;
        grid.add(new Cell(row, 1, 1));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, 3));
        grid.add(new Cell(row, 4, 4));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, 6));
        grid.add(new Cell(row, 7, 7));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, 9));
        row = 8;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, 5));
        grid.add(new Cell(row, 3, 6));
        grid.add(new Cell(row, 4, 7));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, 9));
        grid.add(new Cell(row, 7, 1));
        grid.add(new Cell(row, 8, 2));
        grid.add(new Cell(row, 9, null));
        row = 9;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 1));
        grid.add(new Cell(row, 5, 2));
        grid.add(new Cell(row, 6, 3));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, 6));

        printGrid(grid);
        validateGrid(grid);

        // List<Cell> rowOneCells = grid.stream().filter(c -> c.getRow() == 1).collect(Collectors.toList());

        // System.out.println(grid);
        // System.out.println(rowOneCells);
    }

    public static void printGrid(ArrayList<Cell> grid) {
        System.out.println("printGrid");

        for (int i = 1; i < 10; i++) {
            int row = i;
            List<Cell> cellsFromRow = grid.stream().filter(c -> c.getRow() == row).collect(Collectors.toList());

            for (int j = 0; j < cellsFromRow.size(); j++) {
                Cell currentCell = cellsFromRow.get(j);
                System.out.print(currentCell);
                if (j == 2 || j == 5) {
                    System.out.print("|");
                }
            }

            System.out.println();
            if (i == 3 || i == 6) {
                System.out.println("---+---+---");
            }
        }
    }

    public static boolean validateGrid(ArrayList<Cell> grid) {
        System.out.println("validateGrid");
        boolean validGrid = true;
        for (int i = 1; i < 10; i++) {
            boolean validRow = true;
            System.out.print("Row: " + (i) + "\t");
            
            int row = i;
            List<Cell> cellsFromRow = grid.stream().filter(c -> c.getRow() == row).collect(Collectors.toList());

            Set<Integer> tempSet = new HashSet<>();

            for (int j = 0; j < cellsFromRow.size(); j++) {
                System.out.print("Cell: " + (j + 1) + "\t");
                Cell cell = cellsFromRow.get(j);
                if (cell != null && cell.getValue() != null && !tempSet.add(cell.getValue())) {
                    System.out.print("[" + cell + "] - already exists in row\t");
                    validGrid = false;
                    validRow = false;
                }
            }
            System.out.println(tempSet);
            System.out.println("The row is " + (validRow ? "" : "not ") + "valid");
        }
        return validGrid;
    }
}
