package object;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuSolverWithObjects {

    public static void main(String[] args) {

        // if (validateGrid(startingGrid)) {
        // System.out.println("The grid is valid");
        // } else {
        // System.out.println("The grid is not valid");
        // }

        ArrayList<Cell> grid = new ArrayList<Cell>();
        int row = 1;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 7));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, 2));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, null));
        row = 2;
        grid.add(new Cell(row, 1, 2));
        grid.add(new Cell(row, 2, 7));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 1));
        grid.add(new Cell(row, 5, 4));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, 8));
        grid.add(new Cell(row, 9, null));
        row = 3;
        grid.add(new Cell(row, 1, 3));
        grid.add(new Cell(row, 2, 6));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 9));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, 4));
        grid.add(new Cell(row, 9, null));
        row = 4;
        grid.add(new Cell(row, 1, 1));
        grid.add(new Cell(row, 2, 9));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 4));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, 7));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, null));
        row = 5;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, 7));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, 1));
        grid.add(new Cell(row, 6, 9));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, 4));
        row = 6;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 6));
        grid.add(new Cell(row, 5, 5));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, 1));
        grid.add(new Cell(row, 9, null));
        row = 7;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, 4));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, 9));
        grid.add(new Cell(row, 6, 1));
        grid.add(new Cell(row, 7, 6));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, 8));
        row = 8;
        grid.add(new Cell(row, 1, 5));
        grid.add(new Cell(row, 2, 1));
        grid.add(new Cell(row, 3, 6));
        grid.add(new Cell(row, 4, 8));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, 9));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, null));
        row = 9;
        grid.add(new Cell(row, 1, 9));
        grid.add(new Cell(row, 2, 8));
        grid.add(new Cell(row, 3, 3));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, 2));
        grid.add(new Cell(row, 9, 1));

        printGrid(grid);
        validateGrid(grid);
        findPossibleValues(grid);
    }

    public static void printGrid(ArrayList<Cell> grid) {
        System.out.println("printGrid");

        for (int i = 1; i < 10; i++) {
            int row = i;
            List<Cell> cellsFromRow = grid.stream().filter(c -> c.getRow() == row)
                    .collect(Collectors.toList());

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
            List<Cell> cellsFromRow = getCellsForRow(grid, row);

            validListOfCells(cellsFromRow, validGrid);
        }

        // check columns
        for (int i = 1; i < 10; i++) {
            System.out.print("Column: " + (i) + "\t");

            int column = i;
            List<Cell> cellsFromColumn = getCellsForColumn(grid, column);

            validListOfCells(cellsFromColumn, validGrid);
        }

        // check boxes
        for (int i = 1; i < 10; i++) {
            System.out.print("Box: " + (i) + "\t");

            int box = i;
            List<Cell> cellsFromColumn = getCellsForBox(grid, box);

            validListOfCells(cellsFromColumn, validGrid);
        }

        System.out.println("The grid is " + (validGrid ? "" : "not ") + "valid");
        return validGrid;
    }

    public static List<Cell> getCellsForBox(ArrayList<Cell> grid, Integer box) {
        return grid.stream().filter(c -> c.getBox() == box).collect(Collectors.toList());
    }

    public static List<Cell> getCellsForColumn(ArrayList<Cell> grid, Integer column) {
        return grid.stream().filter(c -> c.getColumn() == column).collect(Collectors.toList());
    }

    public static List<Cell> getCellsForRow(ArrayList<Cell> grid, Integer row) {
        return grid.stream().filter(c -> c.getRow() == row).collect(Collectors.toList());
    }

    public static List<Cell> getEmptyCells(ArrayList<Cell> grid) {
        return grid.stream().filter(c -> c.getValue() == null).collect(Collectors.toList());
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

    private static void findPossibleValues(ArrayList<Cell> grid) {
        System.out.println("validListOfCells");

        int emptyCells = -1;

        while (emptyCells != 0) {

            for (Cell cell : grid) {

                if (cell.getValue() == null) {
                    System.out.print(cell.getDescription());

                    List<Integer> possibleValues = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                            .collect(Collectors.toList());

                    removePossibleValues(possibleValues, getCellsForBox(grid, cell.getBox()));
                    removePossibleValues(possibleValues, getCellsForColumn(grid, cell.getColumn()));
                    removePossibleValues(possibleValues, getCellsForRow(grid, cell.getRow()));

                    cell.setPossibleValues(possibleValues);

                    System.out.println(" | possibleValues:" + possibleValues);
                }
            }

            printGrid(grid);
            System.out.println();

            // for (Cell cell : grid) {
            // System.out.println(cell.getDescription());
            // }

            int tmp = getEmptyCells(grid).size();
            if (tmp == emptyCells) {
                System.out.println("Abort, not better than before. Impending endless loop");
                break;
            }
            emptyCells = tmp;
        }

        System.out.println("solved");
    }

    private static void removePossibleValues(List<Integer> possibleValues, List<Cell> cells) {
        List<Integer> numbers = cells.stream().map(Cell::getValue).collect(Collectors.toList());
        possibleValues.removeAll(numbers);
        // System.out.print("numbers to remove:" + numbers);
    }
}
