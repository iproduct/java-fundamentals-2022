package course.java.algorithms.exhaustion;

import java.util.ArrayList;
import java.util.List;

public class PrintPrimes {
    public static List<Integer> findPrimes(int n){
        List<Integer> results = new ArrayList<>();
        outer:
        for(int i = 2; i <= n; i++ ){
            var limit = (int) Math.sqrt(i);
            for(int j = 0; j < results.size(); j++){
                if(i % results.get(j) == 0) {
                    continue outer;
                }
            }
            results.add(i);
        }
        return results;
    }

    public static void main(String[] args) {
        System.out.println(findPrimes(1000));
    }
}
