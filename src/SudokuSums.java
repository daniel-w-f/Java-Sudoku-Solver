import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class SudokuSums {

    static int[] SUDOKU_NUMBERS = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    public static void main(String[] args) {
        calculateUniqueSumsOfTwo();
        calculateUniqueSumsOfThree();
    }

    private static void calculateUniqueSumsOfTwo() {
        HashMap<Integer, TreeSet<String>> sumsMapTwo = new HashMap<>();

        for (int i : SUDOKU_NUMBERS) {
            for (int j : SUDOKU_NUMBERS) {
                if (i != j) {
                    int sum = i + j;
                    // String calculation = i +" + "+ j;
                    String calculation = i < j ? i + " + " + j : j + " + " + i;
                    if (sumsMapTwo.get(sum) == null) {
                        sumsMapTwo.put(sum, new TreeSet<String>());
                    }
                    sumsMapTwo.get(sum).add(calculation);
                }
            }
        }

        printResult(sumsMapTwo);
    }

    private static void calculateUniqueSumsOfThree() {
        HashMap<Integer, TreeSet<String>> sumsMapThree = new HashMap<>();

        for (int i : SUDOKU_NUMBERS) {
            for (int j : SUDOKU_NUMBERS) {
                if (j != i) {
                    for (int k : SUDOKU_NUMBERS) {
                        if (k != j && k != i) {
                            int sum = i + j + k;
                            int[] numbers = { i, j, k };
                            Arrays.sort(numbers);
                            String calculation = numbers[0] + " + " + numbers[1] + " + " + numbers[2];
                            if (sumsMapThree.get(sum) == null) {
                                sumsMapThree.put(sum, new TreeSet<String>());
                            }
                            sumsMapThree.get(sum).add(calculation);
                        }
                    }
                }
            }
        }

        printResult(sumsMapThree);
    }

    private static void printResult(HashMap<Integer, TreeSet<String>> sumsMapThree) {
        for (Map.Entry<Integer, TreeSet<String>> entry : sumsMapThree.entrySet()) {
            Integer key = entry.getKey();
            System.out.println("Key: " + key);
            TreeSet<String> list = entry.getValue();
            for (String string : list) {
                System.out.println(string);
            }
        }
    }
}
