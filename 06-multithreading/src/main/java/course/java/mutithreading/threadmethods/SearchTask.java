package course.java.mutithreading.threadmethods;

import java.util.Random;
import java.util.concurrent.Callable;

public class SearchTask implements Callable<String> {
        private String keywords;
        public SearchTask(String keywords) {
            this.keywords = keywords;
        }
        @Override
        public String call() throws Exception {
            Thread.sleep(new Random().nextInt(10000));
            return String.format("Search search for '%s' ...%n", keywords);
        }
    }
