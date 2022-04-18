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
            try {
                Thread.sleep(new Random().nextInt(10000));
            } catch(InterruptedException ex){
                throw new InterruptedException("Task [" + keywords + "] was canceled.");
            }
            return String.format("Search result for '%s' ...%n", keywords);
        }
    }
