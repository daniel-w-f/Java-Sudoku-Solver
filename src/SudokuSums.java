import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class SudokuSums {

    static int[] SUDOKU_NUMBERS = new int[] {1,2,3,4,5,6,7,8,9};

    public static void main(String[] args) {

        // HashMap<Integer,List<String>> sumsMapTwo = new HashMap<>();

        // for (int i : SUDOKU_NUMBERS) {
        //     for (int j : SUDOKU_NUMBERS) {
        //         if (i != j) {
        //             int sum = i+j;
        //             // String calculation = i +" + "+ j;
        //             String calculation = i < j ? i +" + "+ j : j +" + "+ i;
        //             if ( sumsMapTwo.get(sum) == null ){
        //                 sumsMapTwo.put(sum, new ArrayList<String>());
        //             }
        //             sumsMapTwo.get(sum).add(calculation);
        //         }
        //     }
        // }

        // for (Map.Entry<Integer,List<String>> entry : sumsMapTwo.entrySet()) {
        //     Integer key = entry.getKey();
        //     System.out.println("Key: "+ key);
        //     List<String> list = entry.getValue();
        //     for (String string : list) {
        //         System.out.println(string);
        //     }
        // }

        calculateUniqueSumsOfTwo();
    }

    private static void calculateUniqueSumsOfTwo() {
        HashMap<Integer,TreeSet<String>> sumsMapTwo = new HashMap<>();

        for (int i : SUDOKU_NUMBERS) {
            for (int j : SUDOKU_NUMBERS) {
                if (i != j) {
                    int sum = i+j;
                    // String calculation = i +" + "+ j;
                    String calculation = i < j ? i +" + "+ j : j +" + "+ i;
                    if ( sumsMapTwo.get(sum) == null ){
                        sumsMapTwo.put(sum, new TreeSet<String>());
                    }
                    sumsMapTwo.get(sum).add(calculation);
                }
            }
        }

        for (Map.Entry<Integer,TreeSet<String>> entry : sumsMapTwo.entrySet()) {
            Integer key = entry.getKey();
            System.out.println("Key: "+ key);
            TreeSet<String> list = entry.getValue();
            for (String string : list) {
                System.out.println(string);
            }
        }
    }
}
