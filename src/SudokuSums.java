import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SudokuSums {
    public static void main(String[] args) {

        int[] sudokuNumbers = new int[] {1,2,3,4,5,6,7,8,9};

        HashMap<Integer,List<String>> sumsMapTwo = new HashMap<>();

        for (int i : sudokuNumbers) {
            for (int j : sudokuNumbers) {
                if (i != j) {
                    int sum = i+j;
                    // String calculation = i +" + "+ j;
                    String calculation = i < j ? i +" + "+ j : j +" + "+ i;
                    if ( sumsMapTwo.get(sum) == null ){
                        sumsMapTwo.put(sum, new ArrayList<String>());
                    }
                    sumsMapTwo.get(sum).add(calculation);
                }
            }
        }

        for (Map.Entry<Integer,List<String>> entry : sumsMapTwo.entrySet()) {
            Integer key = entry.getKey();
            System.out.println("Key: "+ key);
            List<String> list = entry.getValue();
            for (String string : list) {
                System.out.println(string);
            }
        }

        // Todo: Filter out double combinations, that are just reverse
    }
}
