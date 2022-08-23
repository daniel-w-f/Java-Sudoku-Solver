import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SudokuSums {

    static int[] SUDOKU_NUMBERS = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

    static int MIN_SUM = 1;
    static int MAX_SUM = 100;

    public static void main(String[] args) {
        // calculateUniqueSumsOfTwo();
        // calculateUniqueSumsOfThree();
        // calculateUniqueSumsOfFour();
        calculateUniqueSumsOfFive();
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

    private static void recursionCalculation(int levelOfDepth, int currentDepth, List<Integer> usedNumbers,
            HashMap sumMap) {

        if (currentDepth == levelOfDepth) {
            return;
        }

        // System.out.println("levelOfDepth: " + currentDepth);
        currentDepth++;

        for (int i : SUDOKU_NUMBERS) {
            // System.out.println(i + ": " + usedNumbers.contains(i));
            if (!usedNumbers.contains(i)) {
                usedNumbers.add(i);

                if (currentDepth == levelOfDepth) {
                    Integer sum = usedNumbers.stream().reduce(0, Integer::sum);
                    String calculation = usedNumbers.stream().sorted().map(String::valueOf).collect(Collectors.joining(" + "));
                    // System.out.println("sum: " + sum + "\tcalculation: " + calculation);
                    addToMap(sumMap, sum, calculation);
                    usedNumbers.remove((Integer) i);
                } else {
                    recursionCalculation(levelOfDepth, currentDepth, usedNumbers, sumMap);
                }
            }
        }
        if ( usedNumbers.size() > 0 ) {
            usedNumbers.remove(usedNumbers.size()-1);
        }
        currentDepth--;
    }

    private static void calculateUniqueSumsOfFive() {
        HashMap<Integer, TreeSet<String>> sumsMap = new HashMap<>();

        int levelOfDepth = 5;

        recursionCalculation(levelOfDepth, 0, new ArrayList<Integer>(), sumsMap);

        printResult(sumsMap);
    }

    private static void addToMap(HashMap<Integer, TreeSet<String>> map, int sum, String calculation) {
        if (map.get(sum) == null) {
            map.put(sum, new TreeSet<String>());
        }
        map.get(sum).add(calculation);
    }

    private static void printResult(HashMap<Integer, TreeSet<String>> sumsMapThree) {
        SortedSet<Integer> keySet = new TreeSet<>(sumsMapThree.keySet());

        for (Integer key : keySet) {
            if (key >= MIN_SUM && key <= MAX_SUM) {
                System.out.println("Key: " + key);
                TreeSet<String> list = sumsMapThree.get(key);
                for (String string : list) {
                    System.out.print(string + ", ");
                }
                System.out.println();
            }
        }
    }
}
