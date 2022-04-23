package object;

import java.util.List;

public class Cell {

    public Cell(int row, int column, Integer value) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.box = (int) (Math.ceil(row / 3.0) - 1) * 3 + (int) Math.ceil(column / 3.0);

        // System.out.print("row: " + row +" | ");
        // System.out.print("column: " + column +" | ");
        // System.out.print("box: " + box );
        // System.out.println();
    }

    public int row;
    public int column;
    public Integer value;
    public int box;
    public List<Integer> possibleValues;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public List<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(List<Integer> possibleValues) {

        if (possibleValues.size() == 1) {
            this.value = possibleValues.get(0);
        }

        this.possibleValues = possibleValues;
    }

    public String toString() {
        return value != null ? value.toString() : ".";
    }

    public String getDescription() {
        return "Cell box: " + this.box
                + " | column: " + this.column
                + " | row: " + this.row
                + (this.value != null
                        ? " | value: " + this.value
                        : " | possible values: " + this.possibleValues);
    }

}