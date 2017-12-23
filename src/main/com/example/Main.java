package com.example;

import ligualeo.LinguaLeoService;

import java.util.concurrent.*;

public class Main {


    public static void main(String[] args) {
        LinguaLeoService linguaLeoService = new LinguaLeoService();


        withTimeMetric("Pot word", () -> {
            String[] words = "hello world it is my first code with java nine".split(" ");
            FutureTask<String>[] futures = new FutureTask[words.length];

            Executor executorService = Executors.newFixedThreadPool(words.length);

            for (int i = 0; i < futures.length; i++) {
                int n = i;
                futures[i] = new FutureTask<>(() -> linguaLeoService.getTranslations(words[n]).get(0));
                executorService.execute(futures[i]);
            }

            for (FutureTask<String> future : futures) {
                try {
                    System.out.println(future.get());
                } catch (Exception e) { /* As a client, I believe that LinguaLeo and mclout will never fail */ }
            }
        });



    }





















    public static void withTimeMetric(String name, Runnable metric) {
        long start = System.currentTimeMillis();
        metric.run();
        System.err.println(name + ": " + (System.currentTimeMillis() - start) + "ms");
    }
}


