package com.example;

import ligualeo.LinguaLeoService;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {


    public static void main(String[] args) {
        LinguaLeoService linguaLeoService = new LinguaLeoService();


        withTimeMetric("Pot word", () -> {
            String[] words = "hello world it is my first code with java nine".split(" ");
            ExecutorService executorService = Executors.newFixedThreadPool(words.length);

            Arrays.stream(words)
                    .map(word -> (Callable<String>)(() -> linguaLeoService.getTranslations(word).get(0)))
                    .map(executorService::submit)
                    .map(f -> {
                        try {
                            return f.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);


            executorService.shutdown();
        });



    }





















    public static void withTimeMetric(String name, Runnable metric) {
        long start = System.currentTimeMillis();
        metric.run();
        System.err.println(name + ": " + (System.currentTimeMillis() - start) + "ms");
    }
}


