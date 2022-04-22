package object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuSolverWithObjects {

    public static void main(String[] args) {

        ArrayList<Cell> grid = new ArrayList<Cell>();
        int row = 1;
        grid.add(new Cell(row, 1, 4));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, 7));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, 9));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, null));
        row = 2;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, 6));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, 9));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, 1));
        grid.add(new Cell(row, 8, 5));
        grid.add(new Cell(row, 9, 8));
        row = 3;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, 8));
        grid.add(new Cell(row, 4, 1));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, 3));
        grid.add(new Cell(row, 8, 4));
        grid.add(new Cell(row, 9, null));
        row = 4;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, 4));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, null));
        row = 5;
        grid.add(new Cell(row, 1, 3));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, 6));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, 4));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, 2));
        grid.add(new Cell(row, 8, 8));
        grid.add(new Cell(row, 9, null));
        row = 6;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, 1));
        grid.add(new Cell(row, 7, 7));
        grid.add(new Cell(row, 8, 3));
        grid.add(new Cell(row, 9, 5));
        row = 7;
        grid.add(new Cell(row, 1, 8));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, 5));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, 9));
        grid.add(new Cell(row, 9, 4));
        row = 8;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, null));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, null));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, null));
        row = 9;
        grid.add(new Cell(row, 1, null));
        grid.add(new Cell(row, 2, 7));
        grid.add(new Cell(row, 3, null));
        grid.add(new Cell(row, 4, null));
        grid.add(new Cell(row, 5, 8));
        grid.add(new Cell(row, 6, null));
        grid.add(new Cell(row, 7, null));
        grid.add(new Cell(row, 8, null));
        grid.add(new Cell(row, 9, null));

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

    public static List<Cell> getEmptyCells(List<Cell> list) {
        return list.stream().filter(c -> c.getValue() == null).collect(Collectors.toList());
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

                    // Todo: Performance improvement to remove empty cells from list?
                    removePossibleValues(possibleValues, getCellsForBox(grid, cell.getBox()));
                    removePossibleValues(possibleValues, getCellsForColumn(grid, cell.getColumn()));
                    removePossibleValues(possibleValues, getCellsForRow(grid, cell.getRow()));
                    // Todo: Check if there is only one cell for a value per Box/Column/Row
                    // so ignore other possible values for that cell.

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

        findHiddenSingels(grid);

        System.out.println("solved");
    }

    private static void findHiddenSingels(ArrayList<Cell> grid) {
        System.out.println("findHiddenSingels");

        if ( findHiddenSingelsPerType(grid, "Box") ) { return; }
        if ( findHiddenSingelsPerType(grid, "Column") ) { return; }
        if ( findHiddenSingelsPerType(grid, "Row") ) { return; }

        printGrid(grid);
    }

    private static boolean findHiddenSingelsPerType(ArrayList<Cell> grid, String type) {
        System.out.println(type);
        boolean foundSomething = false;
        for (int i = 1; i < 10; i++) {
            if ( frequency(getEmptyCells(getCellsForType(grid, type, i))) ) {
                findPossibleValues(grid);
                return true;
            }
        }
        return foundSomething;
    }

    private static List<Cell> getCellsForType(ArrayList<Cell> grid, String type, int i) {
        switch (type) {
            case "Box":
                return getCellsForBox(grid, i);
            case "Column":
                return getCellsForColumn(grid, i);
            case "Row":
                return getCellsForRow(grid, i);
            default:
                return null;
        }
    }

    private static boolean frequency(List<Cell> cells) {
        System.out.println("\nfrequency");

        // Todo: Make nice way to get a combined list of all possible numbers in a nice way
        // List<Integer> numbers = cells.stream().flatMap(Collection::stream).collect(Collectors.toList());
        // List<Integer> numbers = cells.stream().flatMapToInt(Cell::getPossibleValues).collect(Collectors.toList());
        // List<Integer> joinedList = (List<Integer>) Collection.stream().flatMap(Collection::stream).collect(Collectors.toList());

        List<Integer> allPossibleNumbers = new ArrayList<Integer>();
        for (Cell cell : cells) {
            if (cell.getPossibleValues() != null) {
                allPossibleNumbers.addAll(cell.getPossibleValues());
            }
        }
        List<Integer> uniqueNumbers = allPossibleNumbers.stream().distinct().collect(Collectors.toList());

        boolean foundSomething = false;

        for (Integer integer : uniqueNumbers) {
            // System.out.println("integer: "+ integer +" | frequency: "+ Collections.frequency(allPossibleNumbers, integer));
            if (Collections.frequency(allPossibleNumbers, integer) == 1) {
                System.out.println("- - - - only 1 place for the number: "+ integer );
                // Todo: find cell that belongs to this number that only occures once
                Cell cell = cells.stream().filter(c -> c.getPossibleValues().contains(integer)).findFirst().get();
                System.out.println(cell.getDescription());

                cell.setValue(integer);
                // as soon as 1 value is set all the other values must be reevaluated.....!!!

                // keep track of this, but continue to find more 
                foundSomething = true;
                
                System.out.println(cell.getDescription());
            }
        }

        System.out.println("foundSomething: "+ foundSomething);

        System.out.println(allPossibleNumbers);

        return foundSomething;
    }

    private static void removePossibleValues(List<Integer> possibleValues, List<Cell> cells) {
        List<Integer> numbers = cells.stream().map(Cell::getValue).collect(Collectors.toList());
        possibleValues.removeAll(numbers);
        // System.out.print("numbers to remove:" + numbers);
    }
}
