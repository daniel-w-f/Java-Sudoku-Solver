import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class SudokuSums {

    // static int[] SUDOKU_NUMBERS = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    static int[] SUDOKU_NUMBERS = new int[] { 1, 2, 3, 4, 5, 6, 9 };

    static int MIN_SUM = 15;
    static int MAX_SUM = 15;

    public static void main(String[] args) {
        calculateUniqueSumsOfTwo();
        calculateUniqueSumsOfThree();
        calculateUniqueSumsOfFour();
    }

    private static void calculateUniqueSumsOfTwo() {
        HashMap<Integer, TreeSet<String>> sumsMapTwo = new HashMap<>();

        for (int i : SUDOKU_NUMBERS) {
            for (int j : SUDOKU_NUMBERS) {
                if (i != j) {
                    int sum = i + j;
                    // String calculation = i +" + "+ j;
                    String calculation = i < j ? i + " + " + j : j + " + " + i;
                    addToMap(sumsMapTwo, sum, calculation);
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
                            addToMap(sumsMapThree, sum, calculation);
                        }
                    }
                }
            }
        }

        printResult(sumsMapThree);
    }

    private static void calculateUniqueSumsOfFour() {
        HashMap<Integer, TreeSet<String>> sumsMapThree = new HashMap<>();

        // TODO: Dynamic nesting for e.g. sum of 5 and more...
        for (int i : SUDOKU_NUMBERS) {
            for (int j : SUDOKU_NUMBERS) {
                if (j != i) {
                    for (int k : SUDOKU_NUMBERS) {
                        if (k != i && k != j) {
                            for (int l : SUDOKU_NUMBERS) {
                                if (l != i && l != j && l != k) {
                                    int sum = i + j + k + l;
                                    int[] numbers = { i, j, k, l };
                                    Arrays.sort(numbers);
                                    String calculation = numbers[0] + " + " + numbers[1] + " + " + numbers[2] + " + "
                                            + numbers[3];
                                    addToMap(sumsMapThree, sum, calculation);
                                }
                            }
                        }
                    }
                }
            }
        }

        printResult(sumsMapThree);
    }

    private static void addToMap(HashMap<Integer, TreeSet<String>> map, int sum, String calculation) {
        if (map.get(sum) == null) {
            map.put(sum, new TreeSet<String>());
        }
        map.get(sum).add(calculation);
    }

    private static void printResult(HashMap<Integer, TreeSet<String>> sumsMapThree) {
        for (Map.Entry<Integer, TreeSet<String>> entry : sumsMapThree.entrySet()) {
            Integer key = entry.getKey();
            if (key >= MIN_SUM && key <= MAX_SUM) {
                System.out.println("Key: " + key);
                TreeSet<String> list = entry.getValue();
                for (String string : list) {
                    // System.out.println(string);
                    System.out.print(string + ", ");
                }
                System.out.println();
            }
        }
    }
}
