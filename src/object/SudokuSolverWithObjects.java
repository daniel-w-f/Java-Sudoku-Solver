package object;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SudokuSolverWithObjects {

    public static void main(String[] args) {

        // if (validateGrid(startingGrid)) {
        // System.out.println("The grid is valid");
        // } else {
        // System.out.println("The grid is not valid");
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
        grid.add(new Cell(row, 1, 2));
        grid.add(new Cell(row, 2, 3));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 5));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, 8));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, 1));
        row = 5;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, 4));
        grid.add(new Cell(row, 3, 5));
        grid.add(new Cell(row, 4, 6));
        grid.add(new Cell(row, 5, 7));
        grid.add(new Cell(row, 6, 8));
        grid.add(new Cell(row, 7, 9));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, 2));
        row = 6;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, 7));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 2));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, 1));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, 3));
        grid.add(new Cell(row, 9, null));
        row = 7;
        grid.add(new Cell(row, 1, 9));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, 8));
        grid.add(new Cell(row, 4, 3));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, 4));
        grid.add(new Cell(row, 7, 2));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, null));
        row = 8;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, 1));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 8));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, 2));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, null));
        row = 9;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, 2));
        grid.add(new Cell(row, 4, 9));
        grid.add(new Cell(row, 5, 1));
        grid.add(new Cell(row, 6, 5));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, 4));

        printGrid(grid);
        validateGrid(grid);

        // List<Cell> rowOneCells = grid.stream().filter(c -> c.getRow() ==
        // 1).collect(Collectors.toList());

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

        // Todo: How to use a variable for the filter? e.g. "c.getRow() == row"

        // check rows
        for (int i = 1; i < 10; i++) {
            System.out.print("Row: " + (i) + "\t");

            int row = i;
            List<Cell> cellsFromRow = grid.stream().filter(c -> c.getRow() == row).collect(Collectors.toList());

            validListOfCells(cellsFromRow, validGrid);
        }

        // check columns
        for (int i = 1; i < 10; i++) {
            System.out.print("Column: " + (i) + "\t");

            int column = i;
            List<Cell> cellsFromColumn = grid.stream().filter(c -> c.getColumn() == column).collect(Collectors.toList());

            validListOfCells(cellsFromColumn, validGrid);
        }

        // check boxes
        for (int i = 1; i < 10; i++) {
            System.out.print("Box: " + (i) + "\t");

            int box = i;
            List<Cell> cellsFromColumn = grid.stream().filter(c -> c.getBox() == box).collect(Collectors.toList());

            validListOfCells(cellsFromColumn, validGrid);
        }
        
        System.out.println("The grid is " + (validGrid ? "" : "not ") + "valid");
        return validGrid;
    }

    public static boolean validListOfCells(List<Cell> cells, boolean validGrid) {
        System.out.println("validListOfCells");
        boolean valid = true;
        Set<Integer> tempSet = new HashSet<>();
        for (int i = 0; i < cells.size(); i++) {
            // System.out.print("Cell: " + (i + 1) + "\t");
            Cell cell = cells.get(i);
            if (cell != null && cell.getValue() != null && !tempSet.add(cell.getValue())) {
                System.out.print("[" + cell + "] - already exists in list\t");
                valid = false;
            }
        }
        System.out.print(tempSet);
        System.out.println("\tThe list is " + (valid ? "" : "not ") + "valid");

        // If the list is valid keep the current value for validGrid
        validGrid = valid ? validGrid : valid;

        return valid;
    }
}
