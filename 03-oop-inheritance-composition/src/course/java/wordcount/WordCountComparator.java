package course.java.wordcount;

import java.util.Comparator;
import java.util.Map;

public class WordCountComparator implements Comparator<Map .Entry<String, Integer>> {
    @Override
    public int compare(Map.Entry<String, Integer> wc1, Map.Entry<String, Integer> wc2) {
        var countsCompare =  -wc1.getValue().compareTo(wc2.getValue());
        return countsCompare == 0? wc1.getKey().compareToIgnoreCase(wc2.getKey()): countsCompare;
    }
}
