package object;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SudokuSolverWithObjects {

    private static List<Cell> grid;

    public static void main(String[] args) {

        grid = new ArrayList<Cell>();
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

        printGrid();
        // only try to solve it, if the initial grid is valid
        if (validateGrid()) {
            solveGrid();
        }
    }

    private static void solveGrid() {
        System.out.println("solveGrid");

        int emptyCells = -1;
        while (emptyCells != 0) {

            // Find all possible values for a cell by checking which numbers are not yet already
            // used within the same box/column/row
            findPossibleValues(grid);

            int possibleNumbers = -1;
            while (possibleNumbers != 0) {

                // Check if there is only one cell for a value per box/column/row so ignore other 
                // possible values for that cell.
                findHiddenSingels(grid);

                // Locked Candidates Type 1 (Pointing): If in a block all candidates of a certain 
                // digit are confined to a row or column, that digit cannot appear outside of that 
                // block in that row or column.
                // Locked Candidates Type 2 (Claiming): Locked Candidates Type 2 works exactly the 
                // other way round: If in a row (or column) all candidates of a certain digit are 
                // confined to one block, that candidate that be eliminated from all other cells in 
                // that block.
                findLockedCandidates(grid);

                int tmp = getAllPossibleNumbers(grid).size();
                if (tmp == possibleNumbers) {
                    System.out.println("Abort, not better than before. Impending endless loop");
                    break;
                }
                possibleNumbers = tmp;
            }

            // get inspiration from here: http://hodoku.sourceforge.net/en/tech_intersections.php

            printGrid();

            int tmp = getEmptyCells(grid).size();
            if (tmp == emptyCells) {
                System.out.println("Abort, not better than before. Impending endless loop");
                break;
            }
            emptyCells = tmp;
        }

        if (getEmptyCells(grid).size() == 0) {
            System.out.println("Solved! :-)");
        } else {
            System.out.println("Not solved :-(");
        }
    }

    private static void findLockedCandidates(List<Cell> grid) {
        /*
        - filter grid, to get only empty cells
        - type 1
            - get all cells from one box
            - get unique list of numbers of possible values for the cells
            - iterate over numbers
            - break loop when you find the same number in a different row
            - if loop ended without breaking, all occourences of this number are in the same row
                that means the number cannot occour in the same row, within a different box
            - find cells in other boxes container that number
            - remove the number from the list of possible values
        */
        printGrid();
        List<Cell> emptyCells = getEmptyCells(grid);
        for (int i = 1; i < 10; i++) {
            System.out.println("Box: "+ i);
            List<Cell> boxCells = getCellsForType(emptyCells, "Box", i);
            List<Integer> allPossibleNumbers = getAllPossibleNumbers(boxCells);
            List<Integer> uniqueNumbers = allPossibleNumbers.stream().distinct().collect(Collectors.toList());
            for (Integer integer : uniqueNumbers) {
                System.out.println("uniqueNumber: "+ integer);
                List<Cell> cellsWithPossibleValue = getCellsContainPossibleNumber(boxCells,integer);
                
                int row = 0;
                for (Cell cell : cellsWithPossibleValue) {
                    if (row == 0 ) {
                        row = cell.getRow();
                    }
                    if ( row != cell.getRow() ) {
                        row = -1;
                        break;
                    }
                }
                if ( row != -1 ) {
                    int startingBox = (int)((Math.ceil(row / 3.0)-1) * 3)+1;

                    // Go through the 3 boxes from the same row
                    for (int j = startingBox; j < startingBox+3; j++) {
                        // Skip the box for that we have checked initially the cells
                        if (j != i) {
                            System.out.println("j/Box: "+ j);
                            List<Cell> cellsFromBox = getCellsForType(grid, "Box", j);
                            List<Cell> cellsFromRow = getCellsForType(cellsFromBox, "Row", row);
                            List<Cell> cellsToCheck = getEmptyCells(cellsFromRow);
                            removeNumberAsPossible(integer, cellsToCheck);
                        }
                    }
                }
            }
        }
    }

    private static void removeNumberAsPossible(Integer integer, List<Cell> cellsToCheck) {
        List<Cell> cellsContainignNumber = getCellsContainPossibleNumber(cellsToCheck, integer);
        for (Cell cell : cellsContainignNumber) {
            System.out.println("Remove ["+ integer +"] from: "+ cell.getDescription());
            cell.getPossibleValues().remove(integer);
            // System.out.println(cell.getDescription());
        }
    }

    public static void printGrid() {
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

    public static boolean validateGrid() {
        System.out.println("validateGrid");
        boolean validGrid = true;

        validateGridPerType(grid, "Box", validGrid);
        validateGridPerType(grid, "Column", validGrid);
        validateGridPerType(grid, "Row", validGrid);

        System.out.println("The grid is " + (validGrid ? "" : "not ") + "valid");
        return validGrid;
    }

    private static void validateGridPerType(List<Cell> grid, String type, boolean validGrid) {
        for (int i = 1; i < 10; i++) {
            System.out.print(type + ": " + (i) + "\t");

            validListOfCells(getCellsForType(grid, type, i), validGrid);
        }
    }

    public static List<Cell> getCellsForBox(List<Cell> cells, Integer box) {
        // Todo: How to use a variable for the filter? e.g. "c.getRow() == row"
        return cells.stream().filter(c -> c.getBox() == box).collect(Collectors.toList());
    }

    public static List<Cell> getCellsForColumn(List<Cell> cells, Integer column) {
        return cells.stream().filter(c -> c.getColumn() == column).collect(Collectors.toList());
    }

    public static List<Cell> getCellsForRow(List<Cell> cells, Integer row) {
        return cells.stream().filter(c -> c.getRow() == row).collect(Collectors.toList());
    }

    public static List<Cell> getEmptyCells(List<Cell> cells) {
        return cells.stream().filter(c -> c.getValue() == null).collect(Collectors.toList());
    }

    private static List<Cell> getCellsContainPossibleNumber(List<Cell> cells, Integer integer) {
        return cells.stream().filter(c -> c.getPossibleValues().contains(integer)).collect(Collectors.toList());
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

    private static void findPossibleValues(List<Cell> grid) {
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

                    cell.setPossibleValues(possibleValues);

                    System.out.println(" | possibleValues:" + possibleValues);
                }
            }

            printGrid();
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
    }

    private static void findHiddenSingels(List<Cell> grid) {
        System.out.println("findHiddenSingels");

        // Early return from method if any hidden single was found, because in that case we need to
        // check again for possible values within the loop in 'solveGrid'.
        if (findHiddenSingelsPerType(grid, "Box")) {
            return;
        }
        if (findHiddenSingelsPerType(grid, "Column")) {
            return;
        }
        if (findHiddenSingelsPerType(grid, "Row")) {
            return;
        }
    }

    private static boolean findHiddenSingelsPerType(List<Cell> grid, String type) {
        System.out.println(type);
        for (int i = 1; i < 10; i++) {
            if (frequency(getEmptyCells(getCellsForType(grid, type, i)))) {
                return true;
            }
        }
        return false;
    }

    private static List<Cell> getCellsForType(List<Cell> cells, String type, int i) {
        switch (type) {
            case "Box":
                return getCellsForBox(cells, i);
            case "Column":
                return getCellsForColumn(cells, i);
            case "Row":
                return getCellsForRow(cells, i);
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

        List<Integer> allPossibleNumbers = getAllPossibleNumbers(cells);
        List<Integer> uniqueNumbers = allPossibleNumbers.stream().distinct().collect(Collectors.toList());

        boolean foundSomething = false;

        for (Integer integer : uniqueNumbers) {
            // System.out.println("integer: "+ integer +" | frequency: "+ Collections.frequency(allPossibleNumbers, integer));
            if (Collections.frequency(allPossibleNumbers, integer) == 1) {
                System.out.println("- - - - only 1 place for the number: " + integer);
                // Todo: find cell that belongs to this number that only occures once
                Cell cell = cells.stream().filter(c -> c.getPossibleValues().contains(integer)).findFirst().get();
                System.out.println(cell.getDescription());

                cell.setValue(integer);
                // as soon as 1 value is set all the other values must be reevaluated.....!!!

                // TODO!
                List<Cell> cellsFromBox = getCellsForType(grid, "Box", cell.getBox());
                List<Cell> cellsFromColumn = getCellsForType(grid, "Column", cell.getColumn());                
                List<Cell> cellsFromRow = getCellsForType(grid, "Row", cell.getRow());
                List<Cell> combinedCellList = new ArrayList<Cell>();
                combinedCellList.addAll(cellsFromBox);
                combinedCellList.addAll(cellsFromColumn);
                combinedCellList.addAll(cellsFromRow);
                List<Cell> cellsToCheck = getEmptyCells(combinedCellList);
                removeNumberAsPossible(integer, cellsToCheck);

                // keep track of this, but continue to find more
                foundSomething = true;

                System.out.println(cell.getDescription());
            }
        }

        System.out.println("foundSomething: " + foundSomething);

        System.out.println(allPossibleNumbers);

        return foundSomething;
    }

    private static List<Integer> getAllPossibleNumbers(List<Cell> cells) {
        List<Integer> allPossibleNumbers = new ArrayList<Integer>();
        for (Cell cell : cells) {
            if (cell.getPossibleValues() != null) {
                allPossibleNumbers.addAll(cell.getPossibleValues());
            }
        }
        return allPossibleNumbers;
    }

    private static void removePossibleValues(List<Integer> possibleValues, List<Cell> cells) {
        List<Integer> numbers = cells.stream().map(Cell::getValue).collect(Collectors.toList());
        possibleValues.removeAll(numbers);
        // System.out.print("numbers to remove:" + numbers);
    }
}
