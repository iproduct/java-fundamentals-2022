package course.java.algorithms.exhaustion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class MayanWords {
    public static Map<String, Integer> findPossibleAnagrams(String word, String text) {
        var results = new HashMap<String, Integer>();
        var textChars = text.toCharArray();
//        var wordCharCounts = word.codePoints().boxed()
//                .collect(Collectors
//                        .<Integer, Integer, Long, Long>groupingBy(
//                                Function.<Integer>identity(),
//                                (Collector<Integer, Long, Long>) Collectors.<Integer>counting())
//                );
        var wordChars = word.toCharArray();
        var wordCharCounts = new int[256];
        for(int i = 0; i < wordChars.length; i++) {
            wordCharCounts[wordChars[i]]++;
        }
//        var diffCounts = Arrays.copyOf(wordCharCounts.values().toArray(new Long[0]), wordCharCounts.size());
        var diffCounts = Arrays.copyOf(wordCharCounts, wordCharCounts.length);
        System.out.println(Arrays.toString(diffCounts));

        // sliding window
        var sliceCharLen = 0;
        var totalDiffs = wordChars.length;
        for(int i=0; i < textChars.length; i++) { // O(n)
            var ch = textChars[i];
            if(wordCharCounts[ch] == 0) {
                copyCharCounts(diffCounts, wordCharCounts);
                sliceCharLen = 0;
                totalDiffs = wordChars.length;
            } else {
                diffCounts[ch]--;
                totalDiffs --;
                sliceCharLen++;
                if(sliceCharLen > wordChars.length) {
                    diffCounts[i - wordChars.length]++;
                    totalDiffs ++;
                    sliceCharLen--;
                }
                if(totalDiffs == 0) {
                    var anagram = String.copyValueOf(textChars, i + 1 - wordChars.length, wordChars.length);
                    results.put(anagram, results.getOrDefault(anagram, 0) + 1);
                }
            }
        }
        return results;
    }

    private static void copyCharCounts(int[] diffCounts, int[] wordCharCounts) {
        for(int j = 0; j < 256; j++){
            diffCounts[j] = wordCharCounts[j];
        }
    }

    public static void main(String[] args) {
//        var anagramCounts = findPossibleAnagrams("acAd", "abracAdabRA");
//        var anagramCounts = findPossibleAnagrams("acAda", "abracAdabRA");
        var anagramCounts = findPossibleAnagrams(
                "aaaaaaaaaaaaaaaaaaaaaba",
                "aaaaaaaaaaaaaaaaaaaaabaaaaaaaaaaaaaaaaaaaaaaba");
        System.out.println(anagramCounts);
    }
}
