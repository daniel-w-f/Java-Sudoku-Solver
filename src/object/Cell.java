package object;
import java.util.ArrayList;

public class Cell {

    public Cell(int row, int column, Integer value, int box) {
        this.row = row;
        this.column = column;
        this.value = value;
        this.box = box;
    }

    public int row;
    public int column;
    public Integer value;
    public int box;
    public ArrayList<Integer> possibleValues;

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

    public ArrayList<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setPossibleValues(ArrayList<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }

    public String toString() {
        return value != null ? value.toString() : ".";
    }

}