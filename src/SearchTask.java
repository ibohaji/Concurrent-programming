/*
 * 02158 Concurrent Programming
 * Mandatory Assignment 1
 * Version 1.3
 */


import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.io.*;

/**
 * Search task. No need to modify.
 */
class SearchTask implements Callable<List<Integer>> {

    char[] text, pattern;
    int from = 0, to = 0; // Searched string: text[from..(to-1)]

    /**
     * Create a task for searching occurrences of 'pattern' in the substring
     * text[from..(to-1)]
     */

    public SearchTask(char[] text, char[] pattern, int from, int to) {
        this.text = text;
        this.pattern = pattern;
        this.from = from;
        this.to = to;
    }

    public List<Integer> call() {
        final int pl = pattern.length;
        List<Integer> result = new LinkedList<Integer>();

        // VERY naive string matching to consume some CPU-cycles
        for (int i = from; i <= to - pl; i++) {
            boolean eq = true;
            for (int j = 0; j < pl; j++) {
                if (text[i + j] != pattern[j])
                    eq = false; // We really should break here
            }
            if (eq)
                result.add(i);
        }

        return result;
    }
}


enum Mode { SINGLE, CACHED, FIXED };


